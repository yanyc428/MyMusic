package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.database.UsersTableActions;
import service.setting.BufferCleaner;
import utils.Log;

public class Settings {

	private JPanel contentJPanel;
	public JPanel getContentJPanel() {
		return contentJPanel;
	}

	public void setContentJPanel(JPanel contentJPanel) {
		this.contentJPanel = contentJPanel;
	}



	/**
	 * Create the application.
	 */
	public Settings(int id, final JFrame frame) {
		contentJPanel = new JPanel();
		contentJPanel.setPreferredSize(new Dimension(1000, 700));
		contentJPanel.setLayout(new BorderLayout(0,0));
		initialize(id, frame);
		Log.log("成功加载首页");
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
		
		
		JButton backIndexButton = new JButton("返回首页");
		backIndexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
				frame.setContentPane(new Index(id, frame).getContentJPanel());
			}
		});
		backIndexButton.setPreferredSize(new Dimension(120, 30));
		backIndexButton.setMinimumSize(new Dimension(120, 30));
		backIndexButton.setMaximumSize(new Dimension(120, 30));
		backIndexButton.setForeground(new Color(105, 105, 105));
		backIndexButton.setBorderPainted(false);
		mainHeadMenuBarPanel.add(backIndexButton);
		
		JButton exitButton = new JButton("退出账号");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
                frame.setContentPane(new Login(frame).getContentJPanel());
			}
		});
		exitButton.setPreferredSize(new Dimension(120, 30));
		exitButton.setMinimumSize(new Dimension(120, 30));
		exitButton.setMaximumSize(new Dimension(120, 30));
		exitButton.setForeground(new Color(105, 105, 105));
		exitButton.setBorderPainted(false);
		mainHeadMenuBarPanel.add(exitButton);
		
		JPanel mainBodyPanel = new JPanel();
		mainBodyPanel.setMaximumSize(new Dimension(400, 700));
		mainBodyPanel.setMinimumSize(new Dimension(400, 700));
		mainBodyPanel.setPreferredSize(new Dimension(400, 700));
		mainBodyPanel.setBackground(new Color(255, 255, 255));
		mainPanel.add(mainBodyPanel, BorderLayout.CENTER);
		mainBodyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel settingPanel = new JPanel();
		settingPanel.setBackground(new Color(255, 255, 255));
		settingPanel.setPreferredSize(new Dimension(400, 600));
		settingPanel.setSize(new Dimension(150, 400));
		mainBodyPanel.add(settingPanel);
		
		JPanel settingMentionPanel = new JPanel();
		settingMentionPanel.setBackground(Color.WHITE);
		settingMentionPanel.setPreferredSize(new Dimension(400, 30));
		settingPanel.add(settingMentionPanel);
		settingMentionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel settingMentionLabel = new JLabel("设置");
		settingMentionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		settingMentionLabel.setForeground(new Color(105, 105, 105));
		settingMentionLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		settingMentionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		settingMentionLabel.setPreferredSize(new Dimension(350, 25));
		settingMentionPanel.add(settingMentionLabel);
		
		JPanel settingFunctionPanel = new JPanel();
		settingFunctionPanel.setBackground(new Color(255, 255, 255));
		settingFunctionPanel.setPreferredSize(new Dimension(400, 600));
		settingPanel.add(settingFunctionPanel);
		settingFunctionPanel.setLayout(new GridLayout(10, 0, 0, 0));
		
		JPanel downloadPanel = new JPanel();
		downloadPanel.setBackground(new Color(255, 255, 255));
		downloadPanel.setPreferredSize(new Dimension(400, 30));
		settingFunctionPanel.add(downloadPanel);
		downloadPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton downloadButton = new JButton("资源下载");
		downloadButton.setHorizontalAlignment(SwingConstants.LEFT);
		downloadButton.setPreferredSize(new Dimension(120, 30));
		downloadButton.setMinimumSize(new Dimension(120, 30));
		downloadButton.setMaximumSize(new Dimension(120, 30));
		downloadButton.setForeground(new Color(105, 105, 105));
		downloadButton.setBorderPainted(false);
		downloadPanel.add(downloadButton);
		
		JPanel logPanel = new JPanel();
		logPanel.setBackground(new Color(255, 255, 255));
		logPanel.setPreferredSize(new Dimension(400, 30));
		settingFunctionPanel.add(logPanel);
		logPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton logButton = new JButton("查看日志");
		logButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
				frame.setContentPane(new Logview(id, frame).getContentJPanel());
			}
		});
		logButton.setPreferredSize(new Dimension(120, 30));
		logButton.setMinimumSize(new Dimension(120, 30));
		logButton.setMaximumSize(new Dimension(120, 30));
		logButton.setHorizontalAlignment(SwingConstants.LEFT);
		logButton.setForeground(new Color(105, 105, 105));
		logButton.setBorderPainted(false);
		logPanel.add(logButton);
		
		JPanel bufferPanel = new JPanel();
		bufferPanel.setPreferredSize(new Dimension(400, 30));
		bufferPanel.setBackground(Color.WHITE);
		settingFunctionPanel.add(bufferPanel);
		bufferPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		final JLabel bufferLabel = new JLabel("");
		JButton bufferButton = new JButton("清除缓存");
		bufferButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferCleaner.clean();
				bufferLabel.setText("缓冲清理完毕！");
			}
		});
		bufferButton.setPreferredSize(new Dimension(120, 30));
		bufferButton.setMinimumSize(new Dimension(120, 30));
		bufferButton.setMaximumSize(new Dimension(120, 30));
		bufferButton.setHorizontalAlignment(SwingConstants.LEFT);
		bufferButton.setForeground(new Color(105, 105, 105));
		bufferButton.setBorderPainted(false);
		bufferPanel.add(bufferButton);
		
		
		bufferLabel.setPreferredSize(new Dimension(120, 30));
		bufferLabel.setMinimumSize(new Dimension(120, 30));
		bufferLabel.setMaximumSize(new Dimension(180, 30));
		bufferLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bufferLabel.setForeground(Color.RED);
		bufferPanel.add(bufferLabel);
	}


}
