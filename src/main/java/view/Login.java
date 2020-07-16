package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.database.SingersTableActions;
import dao.database.UsersTableActions;
import utils.Log;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Component;
import javax.swing.JToolBar;
import java.awt.Font;

public class Login {

	private JPasswordField pwdField;
	private JTextField usrNameField;
	
	private JPanel contentJPanel;
	
	private JLabel mention;
	

	public JPanel getContentJPanel() {
		return contentJPanel;
	}

	public void setContentJPanel(JPanel contentJPanel) {
		this.contentJPanel = contentJPanel;
	}

	/**
	 * Create the application.
	 */
	public Login(JFrame frame) {
		contentJPanel = new JPanel();
		contentJPanel.setPreferredSize(new Dimension(1000, 700));
		contentJPanel.setLayout(new BorderLayout(0,0));
		initialize(frame);
		Log.log("成功加载登录界面");
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
		onlineSingerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
				frame.setContentPane(new OnlineSingerIndex(0, frame).getContentJPanel());
			}
		});
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
		
		JButton btnNewButton_5 = new JButton("注册");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
				frame.setContentPane(new Register(frame).getContentJPanel());
			}
		});
		btnNewButton_5.setPreferredSize(new Dimension(80, 30));
		btnNewButton_5.setMinimumSize(new Dimension(120, 30));
		btnNewButton_5.setMaximumSize(new Dimension(120, 30));
		btnNewButton_5.setForeground(new Color(105, 105, 105));
		btnNewButton_5.setBorderPainted(false);
		mainHeadMenuBarPanel.add(btnNewButton_5);
		
		JPanel mainBodyPanel = new JPanel();
		mainBodyPanel.setMaximumSize(new Dimension(400, 700));
		mainBodyPanel.setMinimumSize(new Dimension(400, 700));
		mainBodyPanel.setPreferredSize(new Dimension(400, 700));
		mainBodyPanel.setBackground(new Color(255, 255, 255));
		mainPanel.add(mainBodyPanel, BorderLayout.CENTER);
		mainBodyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(255, 255, 255));
		loginPanel.setPreferredSize(new Dimension(400, 400));
		loginPanel.setSize(new Dimension(400, 400));
		mainBodyPanel.add(loginPanel);
		
		JPanel loginMentionPanel = new JPanel();
		loginMentionPanel.setBackground(new Color(255, 255, 255));
		loginMentionPanel.setPreferredSize(new Dimension(400, 200));
		loginPanel.add(loginMentionPanel);
		loginMentionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel loginLabel = new JLabel("登录以获取更多功能");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		loginLabel.setForeground(new Color(105, 105, 105));
		loginLabel.setBackground(Color.WHITE);
		loginMentionPanel.add(loginLabel);
		
		JPanel loginFunctionPanel = new JPanel();
		loginPanel.add(loginFunctionPanel);
		loginFunctionPanel.setBackground(new Color(255, 255, 255));
		loginFunctionPanel.setMaximumSize(new Dimension(300, 90));
		loginFunctionPanel.setPreferredSize(new Dimension(350, 120));
		loginFunctionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel usrNameLabel = new JLabel("用户名");
		usrNameLabel.setForeground(new Color(105, 105, 105));
		usrNameLabel.setPreferredSize(new Dimension(50, 30));
		usrNameLabel.setMinimumSize(new Dimension(50, 30));
		usrNameLabel.setMaximumSize(new Dimension(50, 30));
		usrNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		loginFunctionPanel.add(usrNameLabel);
		
		usrNameField = new JTextField();
		usrNameField.setBackground(new Color(245, 245, 245));
		usrNameField.setMaximumSize(new Dimension(250, 30));
		usrNameField.setMinimumSize(new Dimension(250, 30));
		usrNameField.setSize(new Dimension(250, 30));
		usrNameField.setLocation(new Point(50, 0));
		usrNameField.setPreferredSize(new Dimension(250, 30));
		loginFunctionPanel.add(usrNameField);
		usrNameField.setColumns(20);
		
		JLabel pwdLabel = new JLabel(" 密码");
		pwdLabel.setForeground(new Color(105, 105, 105));
		pwdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdLabel.setSize(new Dimension(50, 30));
		pwdLabel.setMaximumSize(new Dimension(50, 30));
		pwdLabel.setMinimumSize(new Dimension(50, 30));
		pwdLabel.setPreferredSize(new Dimension(50, 30));
		loginFunctionPanel.add(pwdLabel);
		
		pwdField = new JPasswordField();
		pwdField.setBackground(new Color(245, 245, 245));
		pwdField.setPreferredSize(new Dimension(250, 30));
		loginFunctionPanel.add(pwdField);
		
		JPanel loginButtonPanel = new JPanel();
		loginButtonPanel.setBackground(new Color(255, 255, 255));
		loginButtonPanel.setPreferredSize(new Dimension(350, 30));
		loginFunctionPanel.add(loginButtonPanel);
		loginButtonPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel emptySpace1 = new JLabel("");
		loginButtonPanel.add(emptySpace1);
		
		JButton loginOKButton = new JButton("登录");
		loginOKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = usrNameField.getText();
                String password = new String(pwdField.getPassword());

                int usrId = UsersTableActions.checkUser(name, name, password);
                
                if (usrId!=0){
                    frame.getContentPane().setVisible(false);
                    frame.setContentPane(new Index(usrId, frame).getContentJPanel());
                	UsersTableActions.updateLogin(usrId);
                }else{
                    usrNameField.setText("");
                    pwdField.setText("");
                    mention.setText("用户名/密码错误");
                }
			}
		});
		loginOKButton.setPreferredSize(new Dimension(120, 30));
		loginOKButton.setMinimumSize(new Dimension(120, 30));
		loginOKButton.setMaximumSize(new Dimension(120, 30));
		loginOKButton.setForeground(new Color(105, 105, 105));
		loginOKButton.setBorderPainted(false);
		loginButtonPanel.add(loginOKButton);
		
		JLabel emptySpace2 = new JLabel("");
		loginButtonPanel.add(emptySpace2);
		
		JButton loginCancelButton = new JButton("清空");
		loginCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usrNameField.setText("");
				pwdField.setText("");
				mention.setText("");
			}
		});
		loginCancelButton.setPreferredSize(new Dimension(120, 30));
		loginCancelButton.setMinimumSize(new Dimension(120, 30));
		loginCancelButton.setMaximumSize(new Dimension(120, 30));
		loginCancelButton.setForeground(new Color(105, 105, 105));
		loginCancelButton.setBorderPainted(false);
		loginButtonPanel.add(loginCancelButton);
		
		JLabel emptySpace3 = new JLabel("");
		loginButtonPanel.add(emptySpace3);
		
		mention = new JLabel("");
		mention.setForeground(Color.RED);
		loginPanel.add(mention);
		
	}
	

}
