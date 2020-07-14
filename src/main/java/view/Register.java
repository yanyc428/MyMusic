package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.FileChooserUI;
import javax.xml.validation.Validator;

import dao.database.UsersTableActions;
import service.email.EmailSender;
import utils.CheckEmail;
import utils.Log;
import utils.RandomString;

import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Register {

	private JPanel contentJPanel;
	private JTextField userNameField;
	private JTextField validField;
	private JTextField mailField;
	private JTextField avatarField;
	private JPasswordField pwd2Field;
	private JPasswordField pwd1Field;

	private JLabel mention;
	private String path = "";
	private String userName = "";
	private String passwordString ="";
	private String avatarString = "";
	private String mailString = "";
	private int gender=0;
	private String fileName = "";
	private String validString;
	

	public JPanel getContentJPanel() {
		return contentJPanel;
	}

	public void setContentJPanel(JPanel contentJPanel) {
		this.contentJPanel = contentJPanel;
	}

	/**
	 * Create the application.
	 */
	public Register(final JFrame frame) {
		contentJPanel = new JPanel();
		contentJPanel.setPreferredSize(new Dimension(1000, 700));
		contentJPanel.setLayout(new BorderLayout(0,0));
		initialize(frame);
		Log.log("成功加载注册界面");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final JFrame frame) {
		String dir = System.getProperty("user.dir") + "/resources/view/";

		JPanel leftBarMenuPanel = new JPanel();
		leftBarMenuPanel.setPreferredSize(new Dimension(200, 500));
		leftBarMenuPanel.setMinimumSize(new Dimension(200, 500));
		leftBarMenuPanel.setMaximumSize(new Dimension(200, 500));
		leftBarMenuPanel.setBackground(new Color(245, 245, 245));
		leftBarMenuPanel.setForeground(new Color(255, 255, 255));
		contentJPanel.add(leftBarMenuPanel, BorderLayout.WEST);
		leftBarMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel logoPanel = new JPanel();
		logoPanel.setForeground(new Color(245, 245, 245));
		logoPanel.setBackground(new Color(245, 245, 245));
		logoPanel.setMaximumSize(new Dimension(200, 120));
		logoPanel.setMinimumSize(new Dimension(200, 120));
		logoPanel.setPreferredSize(new Dimension(200, 120));
		leftBarMenuPanel.add(logoPanel);
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(dir + "logo.png"));
		logoPanel.add(logoLabel);
		
		JPanel mymusicPanel = new JPanel();
		mymusicPanel.setBackground(new Color(245, 245, 245));
		mymusicPanel.setMinimumSize(new Dimension(200, 160));
		mymusicPanel.setMaximumSize(new Dimension(200, 160));
		mymusicPanel.setPreferredSize(new Dimension(200, 160));
		leftBarMenuPanel.add(mymusicPanel);
		mymusicPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel mymusicLabel = new JLabel("我的音乐");
		mymusicLabel.setForeground(new Color(192, 192, 192));
		mymusicLabel.setMaximumSize(new Dimension(160, 30));
		mymusicLabel.setMinimumSize(new Dimension(160, 30));
		mymusicLabel.setPreferredSize(new Dimension(160, 30));
		mymusicPanel.add(mymusicLabel);
		
		JButton mymusicLoveButton = new JButton("我喜欢");
		mymusicLoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mention.setText("请先登录");
			}
		});
		mymusicLoveButton.setBorderPainted(false);
		mymusicLoveButton.setBackground(new Color(245, 245, 245));
		mymusicLoveButton.setForeground(new Color(105, 105, 105));
		mymusicLoveButton.setHorizontalAlignment(SwingConstants.LEFT);
		mymusicLoveButton.setMaximumSize(new Dimension(160, 30));
		mymusicLoveButton.setMinimumSize(new Dimension(160, 30));
		mymusicLoveButton.setPreferredSize(new Dimension(160, 30));
		mymusicLoveButton.setIcon(new ImageIcon(dir + "love.png"));
		mymusicPanel.add(mymusicLoveButton);
		
		JButton mymusicListButton = new JButton("我的歌单");
		mymusicListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mention.setText("请先登录");
			}
		});
		mymusicListButton.setForeground(new Color(105, 105, 105));
		mymusicListButton.setHorizontalAlignment(SwingConstants.LEFT);
		mymusicListButton.setBorderPainted(false);
		mymusicListButton.setPreferredSize(new Dimension(160, 30));
		mymusicListButton.setMinimumSize(new Dimension(160, 30));
		mymusicListButton.setMaximumSize(new Dimension(160, 30));
		mymusicListButton.setIcon(new ImageIcon(dir + "list.png"));
		mymusicPanel.add(mymusicListButton);
		
		JButton mymusicHistoryButton = new JButton("检索历史");
		mymusicHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mention.setText("请先登录");
			}
		});
		mymusicHistoryButton.setForeground(new Color(105, 105, 105));
		mymusicHistoryButton.setHorizontalAlignment(SwingConstants.LEFT);
		mymusicHistoryButton.setBorderPainted(false);
		mymusicHistoryButton.setMaximumSize(new Dimension(160, 30));
		mymusicHistoryButton.setMinimumSize(new Dimension(160, 30));
		mymusicHistoryButton.setPreferredSize(new Dimension(160, 30));
		mymusicHistoryButton.setIcon(new ImageIcon(dir + "history.png"));
		mymusicPanel.add(mymusicHistoryButton);
		
		JPanel onlinePanel = new JPanel();
		onlinePanel.setBackground(new Color(245, 245, 245));
		onlinePanel.setForeground(new Color(245, 245, 245));
		onlinePanel.setMaximumSize(new Dimension(200, 120));
		onlinePanel.setMinimumSize(new Dimension(200, 120));
		onlinePanel.setPreferredSize(new Dimension(200, 120));
		leftBarMenuPanel.add(onlinePanel);
		onlinePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel onlineLabel = new JLabel("在线音乐");
		onlineLabel.setForeground(new Color(192, 192, 192));
		onlineLabel.setBackground(new Color(192, 192, 192));
		onlineLabel.setPreferredSize(new Dimension(160, 30));
		onlineLabel.setMinimumSize(new Dimension(160, 30));
		onlineLabel.setMaximumSize(new Dimension(160, 30));
		onlinePanel.add(onlineLabel);
		
		JButton onlineSingerButton = new JButton("歌手");
		onlineSingerButton.setForeground(new Color(105, 105, 105));
		onlineSingerButton.setHorizontalAlignment(SwingConstants.LEFT);
		onlineSingerButton.setBorderPainted(false);
		onlineSingerButton.setPreferredSize(new Dimension(160, 30));
		onlineSingerButton.setMinimumSize(new Dimension(160, 30));
		onlineSingerButton.setMaximumSize(new Dimension(160, 30));
		onlineSingerButton.setIcon(new ImageIcon(dir + "singer.png"));
		onlinePanel.add(onlineSingerButton);
		
		JButton onlineSongButton = new JButton("歌曲");
		onlineSongButton.setHorizontalAlignment(SwingConstants.LEFT);
		onlineSongButton.setForeground(new Color(105, 105, 105));
		onlineSongButton.setBorderPainted(false);
		onlineSongButton.setPreferredSize(new Dimension(160, 30));
		onlineSongButton.setMinimumSize(new Dimension(160, 30));
		onlineSongButton.setMaximumSize(new Dimension(160, 30));
		onlineSongButton.setIcon(new ImageIcon(dir + "music.png"));
		onlinePanel.add(onlineSongButton);
		
		JPanel offlinePanel = new JPanel();
		offlinePanel.setForeground(new Color(245, 245, 245));
		offlinePanel.setBackground(new Color(245, 245, 245));
		offlinePanel.setMaximumSize(new Dimension(200, 120));
		offlinePanel.setMinimumSize(new Dimension(200, 120));
		offlinePanel.setPreferredSize(new Dimension(200, 120));
		leftBarMenuPanel.add(offlinePanel);
		offlinePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel offlineLabel = new JLabel("离线音乐");
		offlineLabel.setForeground(new Color(192, 192, 192));
		offlineLabel.setPreferredSize(new Dimension(160, 30));
		offlineLabel.setMinimumSize(new Dimension(160, 30));
		offlineLabel.setMaximumSize(new Dimension(160, 30));
		offlinePanel.add(offlineLabel);
		
		JButton offlineSingerButton = new JButton("歌手");
		offlineSingerButton.setHorizontalAlignment(SwingConstants.LEFT);
		offlineSingerButton.setForeground(new Color(105, 105, 105));
		offlineSingerButton.setPreferredSize(new Dimension(160, 30));
		offlineSingerButton.setMinimumSize(new Dimension(160, 30));
		offlineSingerButton.setMaximumSize(new Dimension(160, 30));
		offlineSingerButton.setBorderPainted(false);
		offlineSingerButton.setIcon(new ImageIcon(dir + "singer.png"));
		offlinePanel.add(offlineSingerButton);
		
		JButton offlineSongButton = new JButton("歌曲");
		offlineSongButton.setForeground(new Color(105, 105, 105));
		offlineSongButton.setHorizontalAlignment(SwingConstants.LEFT);
		offlineSongButton.setPreferredSize(new Dimension(160, 30));
		offlineSongButton.setMinimumSize(new Dimension(160, 30));
		offlineSongButton.setMaximumSize(new Dimension(160, 30));
		offlineSongButton.setBorderPainted(false);
		offlineSongButton.setIcon(new ImageIcon(dir + "music.png"));
		offlinePanel.add(offlineSongButton);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		contentJPanel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel mainHeadMenuBarPanel = new JPanel();
		FlowLayout fl_mainHeadMenuBarPanel = (FlowLayout) mainHeadMenuBarPanel.getLayout();
		fl_mainHeadMenuBarPanel.setAlignment(FlowLayout.RIGHT);
		mainHeadMenuBarPanel.setBackground(new Color(255, 255, 255));
		mainPanel.add(mainHeadMenuBarPanel, BorderLayout.NORTH);
		
		JButton backLoginButton = new JButton("返回登录");
		backLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
                frame.setContentPane(new Login(frame).getContentJPanel());
			}
		});
		backLoginButton.setPreferredSize(new Dimension(120, 30));
		backLoginButton.setMinimumSize(new Dimension(120, 30));
		backLoginButton.setMaximumSize(new Dimension(120, 30));
		backLoginButton.setForeground(new Color(105, 105, 105));
		backLoginButton.setBorderPainted(false);
		mainHeadMenuBarPanel.add(backLoginButton);
		
		JPanel mainBodyPanel = new JPanel();
		mainBodyPanel.setMaximumSize(new Dimension(400, 700));
		mainBodyPanel.setMinimumSize(new Dimension(400, 700));
		mainBodyPanel.setPreferredSize(new Dimension(400, 700));
		mainBodyPanel.setBackground(new Color(255, 255, 255));
		mainPanel.add(mainBodyPanel, BorderLayout.CENTER);
		mainBodyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(255, 255, 255));
		registerPanel.setPreferredSize(new Dimension(400, 600));
		registerPanel.setSize(new Dimension(150, 400));
		mainBodyPanel.add(registerPanel);
		
		JPanel registerMentionPanel = new JPanel();
		registerMentionPanel.setBackground(Color.WHITE);
		registerMentionPanel.setPreferredSize(new Dimension(400, 30));
		registerPanel.add(registerMentionPanel);
		registerMentionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel registerMentionLabel = new JLabel("注册用户");
		registerMentionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		registerMentionLabel.setForeground(new Color(105, 105, 105));
		registerMentionLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		registerMentionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		registerMentionLabel.setPreferredSize(new Dimension(350, 25));
		registerMentionPanel.add(registerMentionLabel);
		
		JPanel registerFunctionPanel = new JPanel();
		registerFunctionPanel.setBackground(new Color(255, 255, 255));
		registerFunctionPanel.setPreferredSize(new Dimension(400, 600));
		registerPanel.add(registerFunctionPanel);
		registerFunctionPanel.setLayout(new GridLayout(10, 0, 0, 0));
		
		JPanel userNamePanel = new JPanel();
		userNamePanel.setBackground(new Color(255, 255, 255));
		userNamePanel.setPreferredSize(new Dimension(400, 30));
		registerFunctionPanel.add(userNamePanel);
		
		JLabel userNameLabel = new JLabel("用户名");
		userNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userNameLabel.setPreferredSize(new Dimension(39, 30));
		userNamePanel.add(userNameLabel);
		
		userNameField = new JTextField();
		userNameField.setPreferredSize(new Dimension(11, 30));
		userNameField.setBackground(new Color(245, 245, 245));
		userNamePanel.add(userNameField);
		userNameField.setColumns(25);
		
		JPanel genderPanel = new JPanel();
		genderPanel.setPreferredSize(new Dimension(400, 30));
		genderPanel.setBackground(Color.WHITE);
		registerFunctionPanel.add(genderPanel);
		
		JLabel genderLabel = new JLabel("性别");
		genderLabel.setPreferredSize(new Dimension(39, 30));
		genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		final ButtonGroup group = new ButtonGroup();
		
		final JRadioButton genderMButton = new JRadioButton("男");
		
		final JRadioButton genderFButton = new JRadioButton("女");
		
		group.add(genderMButton);
		group.add(genderFButton);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(200, 0));
		genderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		genderPanel.add(genderLabel);
		genderPanel.add(genderMButton);
		genderPanel.add(genderFButton);
		genderPanel.add(horizontalStrut);
		
		JPanel mailPanel = new JPanel();
		mailPanel.setPreferredSize(new Dimension(400, 30));
		mailPanel.setBackground(Color.WHITE);
		registerFunctionPanel.add(mailPanel);
		
		JLabel mailLabel = new JLabel("邮箱");
		mailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		mailLabel.setPreferredSize(new Dimension(39, 30));
		mailPanel.add(mailLabel);
		
		mailField = new JTextField();
		mailField.setPreferredSize(new Dimension(11, 30));
		mailField.setColumns(25);
		mailField.setBackground(new Color(245, 245, 245));
		mailPanel.add(mailField);
		
		JPanel validPanel = new JPanel();
		validPanel.setPreferredSize(new Dimension(400, 30));
		validPanel.setBackground(Color.WHITE);
		registerFunctionPanel.add(validPanel);
		
		JLabel validLabel = new JLabel("验证码");
		validLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		validLabel.setPreferredSize(new Dimension(39, 30));
		validPanel.add(validLabel);
		
		validField = new JTextField();
		validField.setPreferredSize(new Dimension(11, 30));
		validField.setColumns(15);
		validField.setBackground(new Color(245, 245, 245));
		validPanel.add(validField);
		
		final JButton validButton = new JButton("获取验证码");
		validButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (CheckEmail.check(mailField.getText())) {
					validString = EmailSender.valid(mailField.getText());
					if(!validString.equals("")) {
						validButton.setEnabled(false);
					}
				}else {
					mention.setText("邮箱格式有误");
				}
				
				
			}
		});
		validButton.setBorderPainted(false);
		validPanel.add(validButton);
		
		JPanel avatarPanel = new JPanel();
		avatarPanel.setPreferredSize(new Dimension(400, 30));
		avatarPanel.setBackground(Color.WHITE);
		registerFunctionPanel.add(avatarPanel);
		
		JLabel avatarLabel = new JLabel("头像");
		avatarLabel.setPreferredSize(new Dimension(50, 30));
		avatarLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		avatarPanel.add(avatarLabel);
		
		avatarField = new JTextField();
		avatarField.setEnabled(false);
		avatarField.setPreferredSize(new Dimension(11, 30));
		avatarField.setColumns(17);
		avatarField.setBackground(new Color(245, 245, 245));
		avatarPanel.add(avatarField);
		
		JButton avatarButton = new JButton("上传文件");
		avatarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(chooser);
				
				File file = chooser.getSelectedFile();
				path = file.getPath();
				avatarField.setText(path);
				fileName = "user_photo/" +  RandomString.getString(16) + ".png";
				File newFile = new File(System.getProperty("user.dir") + "/resources/view/" + fileName);
				try {
					Files.copy(file.toPath(),newFile.toPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		avatarButton.setBorderPainted(false);
		avatarPanel.add(avatarButton);
		
		
		JPanel pwd1Panel = new JPanel();
		pwd1Panel.setPreferredSize(new Dimension(400, 30));
		pwd1Panel.setBackground(Color.WHITE);
		registerFunctionPanel.add(pwd1Panel);
		
		JLabel pwd1Label = new JLabel("密码");
		pwd1Label.setPreferredSize(new Dimension(39, 30));
		pwd1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		pwd1Panel.add(pwd1Label);
		
		pwd1Field = new JPasswordField();
		pwd1Field.setColumns(25);
		pwd1Field.setBackground(new Color(245, 245, 245));
		pwd1Panel.add(pwd1Field);
		
		JPanel pwd2Panel = new JPanel();
		pwd2Panel.setPreferredSize(new Dimension(400, 30));
		pwd2Panel.setBackground(Color.WHITE);
		registerFunctionPanel.add(pwd2Panel);
		
		JLabel pwd2Label = new JLabel("确认密码");
		pwd2Label.setSize(new Dimension(60, 30));
		pwd2Label.setHorizontalAlignment(SwingConstants.RIGHT);
		pwd2Panel.add(pwd2Label);
		
		pwd2Field = new JPasswordField();
		pwd2Field.setBackground(new Color(245, 245, 245));
		pwd2Field.setColumns(25);
		pwd2Panel.add(pwd2Field);
		
		JPanel registerButtonPanel = new JPanel();
		registerButtonPanel.setPreferredSize(new Dimension(400, 30));
		registerButtonPanel.setBackground(Color.WHITE);
		registerFunctionPanel.add(registerButtonPanel);
		
		JButton registerOKButton = new JButton("提交");
		registerOKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mention.setText("");
				if (userNameField.getText().equals("")) {
					mention.setText("未填写用户名");
					return;
				}
				if (userNameField.getText().length() > 15) {
					mention.setText("用户名不要长度不要大于15");
					return;
				}else {
					userName = userNameField.getText();
				}
				if (!genderMButton.isSelected() && !genderFButton.isSelected()) {
					mention.setText("未选择性别");
					return;
				}else if (genderFButton.isSelected()) {
					gender = 1;
				}
				if (CheckEmail.check(mailField.getText())) {
					mailString = mailField.getText();
				}else {
					mention.setText("邮箱格式有误");
					return;
				}
				
				if (validField.getText().equals("")) {
					mention.setText("未填写验证码");
					return;
				}
				if (!validField.getText().equals(validString)) {
					mention.setText("验证码错误");
					return;
				}
				if (avatarField.getText().equals("")) {
					mention.setText("未上传头像");
					return;
				}
				if (new String(pwd2Field.getPassword()).equals("")) {
					mention.setText("未填写密码");
					return;
				}
				if (new String(pwd1Field.getPassword()).equals("")) {
					mention.setText("未填写密码");
					return;
				}
				if(!new String(pwd2Field.getPassword()).equals(new String(pwd1Field.getPassword()))) {
					mention.setText("前后密码不一致");
					return;
				}else {
					passwordString = new String(pwd1Field.getPassword());
				}
				
				UsersTableActions.addUser(userName, mailString, passwordString, gender, fileName);
				
				frame.getContentPane().setVisible(false);
                frame.setContentPane(new Login(frame).getContentJPanel());
			}
		});
		registerOKButton.setPreferredSize(new Dimension(80, 30));
		registerOKButton.setMinimumSize(new Dimension(120, 30));
		registerOKButton.setMaximumSize(new Dimension(120, 30));
		registerOKButton.setForeground(new Color(105, 105, 105));
		registerOKButton.setBorderPainted(false);
		registerButtonPanel.add(registerOKButton);
		
		JButton registerCancelButton = new JButton("清空");
		registerCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userNameField.setText("");
				validField.setText("");
				mailField.setText("");
				avatarField.setText("");
				pwd2Field.setText("");
				pwd1Field.setText("");
				mention.setText("");
				validButton.setEnabled(true);
			}
		});
		registerCancelButton.setPreferredSize(new Dimension(80, 30));
		registerCancelButton.setMinimumSize(new Dimension(120, 30));
		registerCancelButton.setMaximumSize(new Dimension(120, 30));
		registerCancelButton.setForeground(new Color(105, 105, 105));
		registerCancelButton.setBorderPainted(false);
		registerButtonPanel.add(registerCancelButton);
		
		JPanel mentionPanel = new JPanel();
		mentionPanel.setPreferredSize(new Dimension(400, 30));
		mentionPanel.setBackground(Color.WHITE);
		registerFunctionPanel.add(mentionPanel);
		
		mention = new JLabel("");
		mention.setForeground(new Color(255, 0, 0));
		mention.setBackground(new Color(255, 255, 255));
		mention.setVerifyInputWhenFocusTarget(false);
		mentionPanel.add(mention);
	}

}
