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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	private JLabel lblNewLabel_2;
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

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 500));
		panel.setMinimumSize(new Dimension(200, 500));
		panel.setMaximumSize(new Dimension(200, 500));
		panel.setBackground(new Color(245, 245, 245));
		panel.setForeground(new Color(255, 255, 255));
		contentJPanel.add(panel, BorderLayout.WEST);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(245, 245, 245));
		panel_1.setBackground(new Color(245, 245, 245));
		panel_1.setMaximumSize(new Dimension(200, 120));
		panel_1.setMinimumSize(new Dimension(200, 120));
		panel_1.setPreferredSize(new Dimension(200, 120));
		panel.add(panel_1);
		
		JLabel lable1 = new JLabel("");
		lable1.setIcon(new ImageIcon(dir + "logo.png"));
		panel_1.add(lable1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 245, 245));
		panel_2.setMinimumSize(new Dimension(200, 160));
		panel_2.setMaximumSize(new Dimension(200, 160));
		panel_2.setPreferredSize(new Dimension(200, 160));
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label2 = new JLabel("我的音乐");
		label2.setForeground(new Color(192, 192, 192));
		label2.setMaximumSize(new Dimension(160, 30));
		label2.setMinimumSize(new Dimension(160, 30));
		label2.setPreferredSize(new Dimension(160, 30));
		panel_2.add(label2);
		
		JButton btnNewButton = new JButton("我喜欢");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(245, 245, 245));
		btnNewButton.setForeground(new Color(105, 105, 105));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setMaximumSize(new Dimension(160, 30));
		btnNewButton.setMinimumSize(new Dimension(160, 30));
		btnNewButton.setPreferredSize(new Dimension(160, 30));
		btnNewButton.setIcon(new ImageIcon(dir + "love.png"));
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("我的歌单");
		btnNewButton_1.setForeground(new Color(105, 105, 105));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setPreferredSize(new Dimension(160, 30));
		btnNewButton_1.setMinimumSize(new Dimension(160, 30));
		btnNewButton_1.setMaximumSize(new Dimension(160, 30));
		btnNewButton_1.setIcon(new ImageIcon(dir + "list.png"));
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("检索历史");
		btnNewButton_2.setForeground(new Color(105, 105, 105));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setMaximumSize(new Dimension(160, 30));
		btnNewButton_2.setMinimumSize(new Dimension(160, 30));
		btnNewButton_2.setPreferredSize(new Dimension(160, 30));
		btnNewButton_2.setIcon(new ImageIcon(dir + "history.png"));
		panel_2.add(btnNewButton_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(245, 245, 245));
		panel_3.setForeground(new Color(245, 245, 245));
		panel_3.setMaximumSize(new Dimension(200, 120));
		panel_3.setMinimumSize(new Dimension(200, 120));
		panel_3.setPreferredSize(new Dimension(200, 120));
		panel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label3 = new JLabel("在线音乐");
		label3.setForeground(new Color(192, 192, 192));
		label3.setBackground(new Color(192, 192, 192));
		label3.setPreferredSize(new Dimension(160, 30));
		label3.setMinimumSize(new Dimension(160, 30));
		label3.setMaximumSize(new Dimension(160, 30));
		panel_3.add(label3);
		
		JButton btnNewButton_3 = new JButton("歌手");
		btnNewButton_3.setForeground(new Color(105, 105, 105));
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setPreferredSize(new Dimension(160, 30));
		btnNewButton_3.setMinimumSize(new Dimension(160, 30));
		btnNewButton_3.setMaximumSize(new Dimension(160, 30));
		btnNewButton_3.setIcon(new ImageIcon(dir + "singer.png"));
		panel_3.add(btnNewButton_3);
		
		JButton btnNewButton_2_1 = new JButton("歌曲");
		btnNewButton_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2_1.setForeground(new Color(105, 105, 105));
		btnNewButton_2_1.setBorderPainted(false);
		btnNewButton_2_1.setPreferredSize(new Dimension(160, 30));
		btnNewButton_2_1.setMinimumSize(new Dimension(160, 30));
		btnNewButton_2_1.setMaximumSize(new Dimension(160, 30));
		btnNewButton_2_1.setIcon(new ImageIcon(dir + "music.png"));
		panel_3.add(btnNewButton_2_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(new Color(245, 245, 245));
		panel_4.setBackground(new Color(245, 245, 245));
		panel_4.setMaximumSize(new Dimension(200, 120));
		panel_4.setMinimumSize(new Dimension(200, 120));
		panel_4.setPreferredSize(new Dimension(200, 120));
		panel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label4 = new JLabel("离线音乐");
		label4.setForeground(new Color(192, 192, 192));
		label4.setPreferredSize(new Dimension(160, 30));
		label4.setMinimumSize(new Dimension(160, 30));
		label4.setMaximumSize(new Dimension(160, 30));
		panel_4.add(label4);
		
		JButton btnNewButton_3_1 = new JButton("歌手");
		btnNewButton_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3_1.setForeground(new Color(105, 105, 105));
		btnNewButton_3_1.setPreferredSize(new Dimension(160, 30));
		btnNewButton_3_1.setMinimumSize(new Dimension(160, 30));
		btnNewButton_3_1.setMaximumSize(new Dimension(160, 30));
		btnNewButton_3_1.setBorderPainted(false);
		btnNewButton_3_1.setIcon(new ImageIcon(dir + "singer.png"));
		panel_4.add(btnNewButton_3_1);
		
		JButton btnNewButton_2_1_1 = new JButton("歌曲");
		btnNewButton_2_1_1.setForeground(new Color(105, 105, 105));
		btnNewButton_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2_1_1.setPreferredSize(new Dimension(160, 30));
		btnNewButton_2_1_1.setMinimumSize(new Dimension(160, 30));
		btnNewButton_2_1_1.setMaximumSize(new Dimension(160, 30));
		btnNewButton_2_1_1.setBorderPainted(false);
		btnNewButton_2_1_1.setIcon(new ImageIcon(dir + "music.png"));
		panel_4.add(btnNewButton_2_1_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		contentJPanel.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_6.setBackground(new Color(255, 255, 255));
		panel_5.add(panel_6, BorderLayout.NORTH);
		
		JButton btnNewButton_5 = new JButton("返回登录");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
                frame.setContentPane(new Login(frame).getContentJPanel());
			}
		});
		btnNewButton_5.setPreferredSize(new Dimension(120, 30));
		btnNewButton_5.setMinimumSize(new Dimension(120, 30));
		btnNewButton_5.setMaximumSize(new Dimension(120, 30));
		btnNewButton_5.setForeground(new Color(105, 105, 105));
		btnNewButton_5.setBorderPainted(false);
		panel_6.add(btnNewButton_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setMaximumSize(new Dimension(400, 700));
		panel_7.setMinimumSize(new Dimension(400, 700));
		panel_7.setPreferredSize(new Dimension(400, 700));
		panel_7.setBackground(new Color(255, 255, 255));
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(255, 255, 255));
		panel_10.setPreferredSize(new Dimension(400, 600));
		panel_10.setSize(new Dimension(150, 400));
		panel_7.add(panel_10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_8.setPreferredSize(new Dimension(400, 30));
		panel_10.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("注册用户");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(105, 105, 105));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setPreferredSize(new Dimension(350, 25));
		panel_8.add(lblNewLabel);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		panel_9.setPreferredSize(new Dimension(400, 600));
		panel_10.add(panel_9);
		panel_9.setLayout(new GridLayout(10, 0, 0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(255, 255, 255));
		panel_12.setPreferredSize(new Dimension(400, 30));
		panel_9.add(panel_12);
		
		JLabel lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setPreferredSize(new Dimension(39, 30));
		panel_12.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(11, 30));
		textField.setBackground(new Color(245, 245, 245));
		panel_12.add(textField);
		textField.setColumns(25);
		
		JPanel panel_12_1_3 = new JPanel();
		panel_12_1_3.setPreferredSize(new Dimension(400, 30));
		panel_12_1_3.setBackground(Color.WHITE);
		panel_9.add(panel_12_1_3);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("性别");
		lblNewLabel_1_1_3.setPreferredSize(new Dimension(39, 30));
		lblNewLabel_1_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		final ButtonGroup group = new ButtonGroup();
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("男");
		
		final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("女");
		
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(200, 0));
		panel_12_1_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_12_1_3.add(lblNewLabel_1_1_3);
		panel_12_1_3.add(rdbtnNewRadioButton);
		panel_12_1_3.add(rdbtnNewRadioButton_1);
		panel_12_1_3.add(horizontalStrut);
		
		JPanel panel_12_1_1 = new JPanel();
		panel_12_1_1.setPreferredSize(new Dimension(400, 30));
		panel_12_1_1.setBackground(Color.WHITE);
		panel_9.add(panel_12_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("邮箱");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setPreferredSize(new Dimension(39, 30));
		panel_12_1_1.add(lblNewLabel_1_1_1);
		
		textField_2 = new JTextField();
		textField_2.setPreferredSize(new Dimension(11, 30));
		textField_2.setColumns(25);
		textField_2.setBackground(new Color(245, 245, 245));
		panel_12_1_1.add(textField_2);
		
		JPanel panel_12_1 = new JPanel();
		panel_12_1.setPreferredSize(new Dimension(400, 30));
		panel_12_1.setBackground(Color.WHITE);
		panel_9.add(panel_12_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("验证码");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setPreferredSize(new Dimension(39, 30));
		panel_12_1.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setPreferredSize(new Dimension(11, 30));
		textField_1.setColumns(15);
		textField_1.setBackground(new Color(245, 245, 245));
		panel_12_1.add(textField_1);
		
		final JButton btnNewButton_4 = new JButton("获取验证码");
		btnNewButton_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (CheckEmail.check(textField_2.getText())) {
					validString = EmailSender.valid(textField_2.getText());
					if(!validString.equals("")) {
						btnNewButton_4.setEnabled(false);
					}
				}else {
					lblNewLabel_2.setText("邮箱格式有误");
				}
				
				
			}
		});
		btnNewButton_4.setBorderPainted(false);
		panel_12_1.add(btnNewButton_4);
		
		JPanel panel_12_1_2 = new JPanel();
		panel_12_1_2.setPreferredSize(new Dimension(400, 30));
		panel_12_1_2.setBackground(Color.WHITE);
		panel_9.add(panel_12_1_2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("头像");
		lblNewLabel_1_1_2.setPreferredSize(new Dimension(50, 30));
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_12_1_2.add(lblNewLabel_1_1_2);
		
		textField_4 = new JTextField();
		textField_4.setPreferredSize(new Dimension(11, 30));
		textField_4.setColumns(17);
		textField_4.setBackground(new Color(245, 245, 245));
		panel_12_1_2.add(textField_4);
		
		JButton btnNewButton_4_1 = new JButton("上传文件");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(chooser);
				
				File file = chooser.getSelectedFile();
				path = file.getPath();
				textField_4.setText(path);
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
		btnNewButton_4_1.setBorderPainted(false);
		panel_12_1_2.add(btnNewButton_4_1);
		
		
		JPanel panel_12_1_1_1_1 = new JPanel();
		panel_12_1_1_1_1.setPreferredSize(new Dimension(400, 30));
		panel_12_1_1_1_1.setBackground(Color.WHITE);
		panel_9.add(panel_12_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("密码");
		lblNewLabel_1_1_1_1_1.setPreferredSize(new Dimension(39, 30));
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_12_1_1_1_1.add(lblNewLabel_1_1_1_1_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(25);
		passwordField_1.setBackground(new Color(245, 245, 245));
		panel_12_1_1_1_1.add(passwordField_1);
		
		JPanel panel_12_1_1_1 = new JPanel();
		panel_12_1_1_1.setPreferredSize(new Dimension(400, 30));
		panel_12_1_1_1.setBackground(Color.WHITE);
		panel_9.add(panel_12_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("确认密码");
		lblNewLabel_1_1_1_1.setSize(new Dimension(60, 30));
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_12_1_1_1.add(lblNewLabel_1_1_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setColumns(25);
		panel_12_1_1_1.add(passwordField);
		
		JPanel panel_12_1_1_1_2 = new JPanel();
		panel_12_1_1_1_2.setPreferredSize(new Dimension(400, 30));
		panel_12_1_1_1_2.setBackground(Color.WHITE);
		panel_9.add(panel_12_1_1_1_2);
		
		JButton btnNewButton_5_1 = new JButton("提交");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setText("");
				if (textField.getText().equals("")) {
					lblNewLabel_2.setText("未填写用户名");
					return;
				}
				if (textField.getText().length() > 15) {
					lblNewLabel_2.setText("用户名不要长度不要大于15");
					return;
				}else {
					userName = textField.getText();
				}
				if (!rdbtnNewRadioButton.isSelected() && !rdbtnNewRadioButton_1.isSelected()) {
					lblNewLabel_2.setText("未选择性别");
					return;
				}else if (rdbtnNewRadioButton_1.isSelected()) {
					gender = 1;
				}
				if (CheckEmail.check(textField_2.getText())) {
					mailString = textField_2.getText();
				}else {
					lblNewLabel_2.setText("邮箱格式有误");
					return;
				}
				
				if (textField_1.getText().equals("")) {
					lblNewLabel_2.setText("未填写验证码");
					return;
				}
				if (!textField_1.getText().equals(validString)) {
					lblNewLabel_2.setText("验证码错误");
					return;
				}
				if (textField_4.getText().equals("")) {
					lblNewLabel_2.setText("未上传头像");
					return;
				}
				if (new String(passwordField.getPassword()).equals("")) {
					lblNewLabel_2.setText("未填写密码");
					return;
				}
				if (new String(passwordField_1.getPassword()).equals("")) {
					lblNewLabel_2.setText("未填写密码");
					return;
				}
				if(!new String(passwordField.getPassword()).equals(new String(passwordField_1.getPassword()))) {
					lblNewLabel_2.setText("前后密码不一致");
					return;
				}else {
					passwordString = new String(passwordField_1.getPassword());
				}
				
				UsersTableActions.addUser(userName, mailString, passwordString, gender, fileName);
				
				frame.getContentPane().setVisible(false);
                frame.setContentPane(new Login(frame).getContentJPanel());
			}
		});
		btnNewButton_5_1.setPreferredSize(new Dimension(80, 30));
		btnNewButton_5_1.setMinimumSize(new Dimension(120, 30));
		btnNewButton_5_1.setMaximumSize(new Dimension(120, 30));
		btnNewButton_5_1.setForeground(new Color(105, 105, 105));
		btnNewButton_5_1.setBorderPainted(false);
		panel_12_1_1_1_2.add(btnNewButton_5_1);
		
		JButton btnNewButton_5_2 = new JButton("清空");
		btnNewButton_5_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_4.setText("");
				passwordField.setText("");
				passwordField_1.setText("");
				lblNewLabel_2.setText("");
				btnNewButton_4.setEnabled(true);
			}
		});
		btnNewButton_5_2.setPreferredSize(new Dimension(80, 30));
		btnNewButton_5_2.setMinimumSize(new Dimension(120, 30));
		btnNewButton_5_2.setMaximumSize(new Dimension(120, 30));
		btnNewButton_5_2.setForeground(new Color(105, 105, 105));
		btnNewButton_5_2.setBorderPainted(false);
		panel_12_1_1_1_2.add(btnNewButton_5_2);
		
		JPanel panel_12_1_1_1_2_1 = new JPanel();
		panel_12_1_1_1_2_1.setPreferredSize(new Dimension(400, 30));
		panel_12_1_1_1_2_1.setBackground(Color.WHITE);
		panel_9.add(panel_12_1_1_1_2_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setVerifyInputWhenFocusTarget(false);
		panel_12_1_1_1_2_1.add(lblNewLabel_2);
	}

}
