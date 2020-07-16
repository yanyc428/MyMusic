package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.database.UsersTableActions;
import utils.Log;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.DropMode;

public class Logview {

	private JPanel contentJPanel;
	private JTextPane logPane;
	
	public JPanel getContentJPanel() {
		return contentJPanel;
	}

	public void setContentJPanel(JPanel contentJPanel) {
		this.contentJPanel = contentJPanel;
	}
	
	/**
	 * Create the application.
	 */
	public Logview(int id, JFrame frame) {
		contentJPanel = new JPanel();
		contentJPanel.setPreferredSize(new Dimension(1000, 700));
		contentJPanel.setLayout(new BorderLayout(0,0));
		initialize(id, frame);
		Log.log("成功加载登录界面");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int id, final JFrame frame) {
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
		mymusicListButton.setForeground(new Color(105, 105, 105));
		mymusicListButton.setHorizontalAlignment(SwingConstants.LEFT);
		mymusicListButton.setBorderPainted(false);
		mymusicListButton.setPreferredSize(new Dimension(160, 30));
		mymusicListButton.setMinimumSize(new Dimension(160, 30));
		mymusicListButton.setMaximumSize(new Dimension(160, 30));
		mymusicListButton.setIcon(new ImageIcon(dir + "list.png"));
		mymusicPanel.add(mymusicListButton);
		
		JButton mymusicHistoryButton = new JButton("检索历史");
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
				frame.setContentPane(new OnlineSingerIndex(id, frame).getContentJPanel());
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
		
		JLabel usr_label = new JLabel("用户名");
		usr_label.setHorizontalAlignment(SwingConstants.RIGHT);
		usr_label.setMaximumSize(new Dimension(180, 30));
		usr_label.setMinimumSize(new Dimension(120, 30));
		usr_label.setPreferredSize(new Dimension(120, 30));
		usr_label.setForeground(new Color(105, 105, 105));
		ImageIcon icon = new ImageIcon(dir + UsersTableActions.getAvatar(id));
		icon.setImage(icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        usr_label.setIcon(icon);
        usr_label.setText(UsersTableActions.getName(id));
		mainHeadMenuBarPanel.add(usr_label);
		
		
		
		JButton errorButton = new JButton("错误日志");
		errorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileReader reader;
					reader = new FileReader(System.getProperty("user.dir") + "/log/" + Log.exception);
					BufferedReader bReader = new BufferedReader(reader);
					StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
			        String s = "";
			        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
			            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
			        }
			        bReader.close();
			        String str = sb.toString();
			        logPane.setText(str);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		errorButton.setPreferredSize(new Dimension(120, 30));
		errorButton.setMinimumSize(new Dimension(120, 30));
		errorButton.setMaximumSize(new Dimension(120, 30));
		errorButton.setForeground(new Color(105, 105, 105));
		errorButton.setBorderPainted(false);
		mainHeadMenuBarPanel.add(errorButton);
		
		JButton logButton = new JButton("运行日志");
		logButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileReader reader;
					reader = new FileReader(System.getProperty("user.dir") + "/log/" + Log.fileName);
					BufferedReader bReader = new BufferedReader(reader);
					StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
			        String s = "";
			        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
			            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
			        }
			        bReader.close();
			        String str = sb.toString();
			        logPane.setText(str);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		logButton.setPreferredSize(new Dimension(120, 30));
		logButton.setMinimumSize(new Dimension(120, 30));
		logButton.setMaximumSize(new Dimension(120, 30));
		logButton.setForeground(new Color(105, 105, 105));
		logButton.setBorderPainted(false);
		mainHeadMenuBarPanel.add(logButton);
		
		JButton backToIndexButton = new JButton("返回首页");
		backToIndexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
                frame.setContentPane(new Index(id, frame).getContentJPanel());
			}
		});
		backToIndexButton.setPreferredSize(new Dimension(120, 30));
		backToIndexButton.setMinimumSize(new Dimension(120, 30));
		backToIndexButton.setMaximumSize(new Dimension(120, 30));
		backToIndexButton.setForeground(new Color(105, 105, 105));
		backToIndexButton.setBorderPainted(false);
		mainHeadMenuBarPanel.add(backToIndexButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		
		logPane = new JTextPane();
		logPane.setEnabled(false);
		logPane.setMargin(new Insets(20, 20, 20, 20));
		logPane.setText("选取日志类型……");
		scrollPane.setViewportView(logPane);
		
		
		
	}

}
