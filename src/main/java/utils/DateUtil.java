package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.NewsAddress;

public class DateUtil {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    /**
     * 生成当前时间
     * @return 时间字符串
     */
    public static String now(){
        return df.format(new Date()) +" ";
    }
    public static String logName() {
    	SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
    	return dFormat.format(new Date());
    }
}
