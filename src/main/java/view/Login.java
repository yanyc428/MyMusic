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

	private JPasswordField passwordField;
	private JTextField textField;
	
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mention.setText("请先登录");
			}
		});
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
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mention.setText("请先登录");
			}
		});
		btnNewButton_1.setForeground(new Color(105, 105, 105));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setPreferredSize(new Dimension(160, 30));
		btnNewButton_1.setMinimumSize(new Dimension(160, 30));
		btnNewButton_1.setMaximumSize(new Dimension(160, 30));
		btnNewButton_1.setIcon(new ImageIcon(dir + "list.png"));
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("检索历史");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mention.setText("请先登录");
			}
		});
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
		panel_10.setPreferredSize(new Dimension(400, 400));
		panel_10.setSize(new Dimension(400, 400));
		panel_7.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(255, 255, 255));
		panel_11.setPreferredSize(new Dimension(400, 200));
		panel_10.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("登录以获取更多功能");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel_3.setForeground(new Color(105, 105, 105));
		lblNewLabel_3.setBackground(Color.WHITE);
		panel_11.add(lblNewLabel_3);
		
		JPanel panel_8 = new JPanel();
		panel_10.add(panel_8);
		panel_8.setBackground(new Color(255, 255, 255));
		panel_8.setMaximumSize(new Dimension(300, 90));
		panel_8.setPreferredSize(new Dimension(350, 120));
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setForeground(new Color(105, 105, 105));
		lblNewLabel.setPreferredSize(new Dimension(50, 30));
		lblNewLabel.setMinimumSize(new Dimension(50, 30));
		lblNewLabel.setMaximumSize(new Dimension(50, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_8.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(new Color(245, 245, 245));
		textField.setMaximumSize(new Dimension(250, 30));
		textField.setMinimumSize(new Dimension(250, 30));
		textField.setSize(new Dimension(250, 30));
		textField.setLocation(new Point(50, 0));
		textField.setPreferredSize(new Dimension(250, 30));
		panel_8.add(textField);
		textField.setColumns(20);
		
		JLabel lblNewLabel_1 = new JLabel(" 密码");
		lblNewLabel_1.setForeground(new Color(105, 105, 105));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setSize(new Dimension(50, 30));
		lblNewLabel_1.setMaximumSize(new Dimension(50, 30));
		lblNewLabel_1.setMinimumSize(new Dimension(50, 30));
		lblNewLabel_1.setPreferredSize(new Dimension(50, 30));
		panel_8.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setPreferredSize(new Dimension(250, 30));
		panel_8.add(passwordField);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		panel_9.setPreferredSize(new Dimension(350, 30));
		panel_8.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_2_2 = new JLabel("");
		panel_9.add(lblNewLabel_2_2);
		
		JButton btnNewButton_5_1_1_1 = new JButton("登录");
		btnNewButton_5_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = textField.getText();
                String password = new String(passwordField.getPassword());
                
                if (UsersTableActions.checkUser(name, name, password)){
                    frame.getContentPane().setVisible(false);
                    frame.setContentPane(new Index(name, UsersTableActions.getAvatar(name, password), frame).getContentJPanel());
                	UsersTableActions.updateLogin(name, password);
                }else{
                    textField.setText("");
                    passwordField.setText("");
                    mention.setText("用户名/密码错误");
                }
			}
		});
		btnNewButton_5_1_1_1.setPreferredSize(new Dimension(120, 30));
		btnNewButton_5_1_1_1.setMinimumSize(new Dimension(120, 30));
		btnNewButton_5_1_1_1.setMaximumSize(new Dimension(120, 30));
		btnNewButton_5_1_1_1.setForeground(new Color(105, 105, 105));
		btnNewButton_5_1_1_1.setBorderPainted(false);
		panel_9.add(btnNewButton_5_1_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		panel_9.add(lblNewLabel_2_1);
		
		JButton btnNewButton_5_1_1 = new JButton("清空");
		btnNewButton_5_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
				mention.setText("");
			}
		});
		btnNewButton_5_1_1.setPreferredSize(new Dimension(120, 30));
		btnNewButton_5_1_1.setMinimumSize(new Dimension(120, 30));
		btnNewButton_5_1_1.setMaximumSize(new Dimension(120, 30));
		btnNewButton_5_1_1.setForeground(new Color(105, 105, 105));
		btnNewButton_5_1_1.setBorderPainted(false);
		panel_9.add(btnNewButton_5_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel_9.add(lblNewLabel_2);
		
		mention = new JLabel("");
		mention.setForeground(Color.RED);
		panel_10.add(mention);
		
	}
	

}
