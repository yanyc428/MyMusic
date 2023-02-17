package service.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import utils.Log;
import utils.RandomString;

public class EmailSender {

	public static String headString = "mymusic音乐 - 热爱你的音乐\n";
	private static String sendBoxString = "mymusic_admin@163.com";
	private static String passwordString = "passwordstr";
	private static String hostString = "smtp.163.com";

    /**
     * 发送验证码
     * @param to 要发送的邮箱
     * @return 生成的验证码
     */
	public static String valid(String to) {
		
		String validDataString = RandomString.getString(6);
		 // 配置参数
        Properties prop = new Properties();
        // 发件人的邮箱的SMTP 服务器地址（不同的邮箱，服务器地址不同，如139和qq的邮箱服务器地址不同）
        prop.setProperty("mail.host", hostString);
        // 使用的协议（JavaMail规范要求）
        prop.setProperty("mail.transport.protocol", "smtp");
        // 需要请求认证
        prop.setProperty("mail.smtp.auth", "true");
        
     // 使用JavaMail发送邮件的5个步骤
        // 1、创建session
        Session session = Session.getInstance(prop);
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        Transport ts = null;
        try {
            // 2、通过session得到transport对象
            ts = session.getTransport();
            // 3、使用邮箱的用户名和密码连接邮件服务器（不同类型的邮箱不一样，网易邮箱输入的是用户名和密码，这里我用的qq邮箱，输入的是邮箱用户名和smtp授权码，smtp授权码可登陆邮箱，进入设置启动smtp服务后获取）
            // 发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            ts.connect(hostString, sendBoxString, passwordString);
            // 4、创建邮件
            Message message = createSimpleMail(session, to, validDataString);
            // 5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            Log.log("成功向" + to + "发送验证邮件");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }finally {
            try {
                // 关闭transport对象
                ts.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return validDataString;
	}

    /**
     * 创建一封只包含文本的邮件
     *
     * @param session
     * @return
     * @throws MessagingException
     */
    public static MimeMessage createSimpleMail(Session session, String to, String contentString)
            throws MessagingException {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明发件人
        message.setFrom(new InternetAddress(sendBoxString));
        // 指明收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 邮件的标题
        message.setSubject("[请勿回复]imusic-热爱你的音乐 用户注册验证码");
        // 邮件的文本内容
        message.setContent(headString + "\n用户注册验证码:" +  contentString , "text/html;charset=UTF-8");
        return message;
    }

}
