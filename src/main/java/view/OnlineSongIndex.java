package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import dao.database.SongsTableActions;
import org.openqa.selenium.WebDriver;

import dao.database.UsersTableActions;
import enumItem.Browser;
import enumItem.Platform;
import service.LoveAMusic;
import service.OpenWebDriver;
import service.spider.kugoumusic.KSongSpider;
import service.spider.qqmusic.QSongSpider;
import service.spider.SongSpider;
import service.spider.wangyiyunmusic.WSongSpider;
import utils.Log;
import javax.swing.JTextField;
import java.awt.Insets;

public class OnlineSongIndex {

private JPanel contentJPanel;
	
	public JPanel getContentJPanel() {
		return contentJPanel;
	}

	public void setContentJPanel(JPanel contentJPanel) {
		this.contentJPanel = contentJPanel;
	}

	Map<String, Object> map = new HashMap<String, Object>();
	private JTextField textField;
	
	/**
	 * Create the application.
	 */
	public OnlineSongIndex(int id, JFrame frame) {
		contentJPanel = new JPanel();
		contentJPanel.setPreferredSize(new Dimension(1000, 700));
		contentJPanel.setLayout(new BorderLayout(0,0));
		initialize(id, frame);
		Log.log("成功加载在线检索歌曲界面 id=" + id);
	}
	
//	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int id, final JFrame frame) {
		final String dir = System.getProperty("user.dir") + "/resources/view/";
		
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
		mymusicPanel.setMinimumSize(new Dimension(200, 120));
		mymusicPanel.setMaximumSize(new Dimension(200, 120));
		mymusicPanel.setPreferredSize(new Dimension(200, 120));
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
				frame.getContentPane().setVisible(false);
				frame.setContentPane(new LoveView(id, frame).getContentJPanel());
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
		onlineSongButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
				frame.setContentPane(new OnlineSongIndex(id, frame).getContentJPanel());
			}
		});
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
		offlineSingerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
				frame.setContentPane(new OfflineSingerIndex(id, frame).getContentJPanel());
			}
		});
		offlineSingerButton.setHorizontalAlignment(SwingConstants.LEFT);
		offlineSingerButton.setForeground(new Color(105, 105, 105));
		offlineSingerButton.setPreferredSize(new Dimension(160, 30));
		offlineSingerButton.setMinimumSize(new Dimension(160, 30));
		offlineSingerButton.setMaximumSize(new Dimension(160, 30));
		offlineSingerButton.setBorderPainted(false);
		offlineSingerButton.setIcon(new ImageIcon(dir + "singer.png"));
		offlinePanel.add(offlineSingerButton);
		
		JButton offlineSongButton = new JButton("歌曲");
		offlineSongButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
				frame.setContentPane(new OfflineSongIndex(id, frame).getContentJPanel());
			}
		});
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
		
		if (id!=0) {
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
		}
		
		
		JButton backToIndexButton = new JButton("返回首页");
		backToIndexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setVisible(false);
				if(id != 0 ) {
					frame.setContentPane(new Index(id, frame).getContentJPanel());
				}
				else {
					frame.setContentPane(new Login(frame).getContentJPanel());
				}
                
			}
		});
		
		final ButtonGroup buttonGroup = new ButtonGroup();
		
		JButton searchButton = new JButton("检索");
		searchButton.setPreferredSize(new Dimension(120, 30));
		searchButton.setMinimumSize(new Dimension(120, 30));
		searchButton.setMaximumSize(new Dimension(120, 30));
		searchButton.setForeground(new Color(105, 105, 105));
		searchButton.setBorderPainted(false);
		mainHeadMenuBarPanel.add(searchButton);
		
		backToIndexButton.setPreferredSize(new Dimension(120, 30));
		backToIndexButton.setMinimumSize(new Dimension(120, 30));
		backToIndexButton.setMaximumSize(new Dimension(120, 30));
		backToIndexButton.setForeground(new Color(105, 105, 105));
		backToIndexButton.setBorderPainted(false);
		mainHeadMenuBarPanel.add(backToIndexButton);
		
		JPanel singerIndexPanel = new JPanel();
		singerIndexPanel.setBackground(Color.WHITE);
		mainPanel.add(singerIndexPanel, BorderLayout.CENTER);
		singerIndexPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel actionPanel = new JPanel();
		actionPanel.setBackground(Color.WHITE);
		singerIndexPanel.add(actionPanel, BorderLayout.NORTH);
		actionPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel lanPanel = new JPanel();
		lanPanel.setBackground(Color.WHITE);
		FlowLayout fl_lanPanel = (FlowLayout) lanPanel.getLayout();
		fl_lanPanel.setAlignment(FlowLayout.LEFT);
		actionPanel.add(lanPanel);
		
		JLabel lblNewLabel = new JLabel("检索词");
		lanPanel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setPreferredSize(new Dimension(50, 40));
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(11, 30));
		textField.setBackground(new Color(245, 245, 245));
		lanPanel.add(textField);
		textField.setColumns(25);

		JPanel plaPanel = new JPanel();
		plaPanel.setBackground(Color.WHITE);
		FlowLayout fl_plaPanel = (FlowLayout) plaPanel.getLayout();
		fl_plaPanel.setAlignment(FlowLayout.LEFT);
		actionPanel.add(plaPanel);
		
		JLabel lblNewLabel_2_2 = new JLabel("平台");
		lblNewLabel_2_2.setPreferredSize(new Dimension(50, 40));
		lblNewLabel_2_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		plaPanel.add(lblNewLabel_2_2);
		
		final JRadioButton qButton = new JRadioButton("QQ音乐");
		qButton.setSelected(true);
		plaPanel.add(qButton);
		qButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			}
		});
		buttonGroup.add(qButton);
		
		final JRadioButton wButton = new JRadioButton("网易云音乐");
		wButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		plaPanel.add(wButton);
		buttonGroup.add(wButton);
		
		final JRadioButton kButton = new JRadioButton("酷狗音乐");
		kButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		plaPanel.add(kButton);
		buttonGroup.add(kButton);
		
		final JLabel mentionLabel = new JLabel();
		mentionLabel.setForeground(Color.RED);
		mentionLabel.setPreferredSize(new Dimension(300, 40));
		mentionLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		mentionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		plaPanel.add(mentionLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setMinimumSize(new Dimension(700, 1000));
		scrollPane.setMaximumSize(new Dimension(1200, 32767));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		singerIndexPanel.add(scrollPane, BorderLayout.CENTER);
		
		final JPanel setPanel = new JPanel();
		setPanel.setPreferredSize(new Dimension(800, 500));
		setPanel.setMinimumSize(new Dimension(800, 500));
		setPanel.setMaximumSize(new Dimension(1200, 32767));
		setPanel.setBackground(Color.WHITE);
		scrollPane.setViewportView(setPanel);
		setPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();
				
				if(qButton.isSelected()) map.put("platform", Platform.QQMuisc);
				if(wButton.isSelected()) map.put("platform", Platform.WangYiYunMusic);
				if(kButton.isSelected()) map.put("platform", Platform.KuGouMusic);
				
				WebDriver driver = new OpenWebDriver(Browser.CHROME, false).getDriver();
				SongSpider songSpider = null;
				
				if (map.get("platform").equals(Platform.QQMuisc)) songSpider = new QSongSpider(driver);
				if (map.get("platform").equals(Platform.WangYiYunMusic)) songSpider = new WSongSpider(driver);
				if (map.get("platform").equals(Platform.KuGouMusic)) songSpider = new KSongSpider(driver);

				mapList =songSpider.spiderByRetrieval(textField.getText());
				
				songSpider.quit();

				setPanel.removeAll();
				setPanel.setPreferredSize(new Dimension(800,mapList.size()*50));
				
				int number = 1;

				for (final HashMap<String, String> item: mapList) {
					JPanel resultPanel = new JPanel();
					resultPanel.setBackground(new Color(255, 255, 255));
					FlowLayout fl_resultPanel = (FlowLayout) resultPanel.getLayout();
					fl_resultPanel.setAlignment(FlowLayout.LEFT);
					resultPanel.setMaximumSize(new Dimension(32767, 40));
					resultPanel.setMinimumSize(new Dimension(10, 30));
					resultPanel.setPreferredSize(new Dimension(800, 40));
					setPanel.add(resultPanel);
					
					final JLabel loveSelectLabel = new JLabel("");
					if (id!=0){
						final ImageIcon iconLove = new ImageIcon(dir + "love_selected.png");
						final ImageIcon iconNotLove = new ImageIcon(dir + "love_not_selected.png");
						iconLove.setImage(iconLove.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
						iconNotLove.setImage(iconNotLove.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
						final int songId = SongsTableActions.getIdByNameURLAlbumURL(item.get("songName"),item.get("songUrl"),item.get("albumUrl") );
						if (LoveAMusic.check(id, songId)){
							loveSelectLabel.setIcon(iconLove);
						}else{
							loveSelectLabel.setIcon(iconNotLove);
						}

						loveSelectLabel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (LoveAMusic.love(id, songId)){
									loveSelectLabel.setIcon(iconLove);
								}else{
									loveSelectLabel.setIcon(iconNotLove);
								}
							}
						});
					}
					loveSelectLabel.setPreferredSize(new Dimension(50, 30));
					resultPanel.add(loveSelectLabel);
					
					JLabel numberLabel = new JLabel(""+number);
					numberLabel.setHorizontalAlignment(SwingConstants.LEFT);
					numberLabel.setPreferredSize(new Dimension(30, 30));
					resultPanel.add(numberLabel);
					
					JButton nameButton = new JButton(item.get("songName"));
					nameButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new OpenWebDriver(Browser.CHROME, true).getURL(item.get("songUrl"));
						}
					});
					nameButton.setMargin(new Insets(0, 0, 0, 2));
					nameButton.setHorizontalAlignment(SwingConstants.LEFT);
					nameButton.setPreferredSize(new Dimension(250, 30));
					nameButton.setMinimumSize(new Dimension(200, 30));
					nameButton.setMaximumSize(new Dimension(120, 30));
					nameButton.setForeground(new Color(105, 105, 105));
					nameButton.setBorderPainted(false);
					resultPanel.add(nameButton);
					
					JButton singerButton = new JButton(item.get("singerName"));
					singerButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new OpenWebDriver(Browser.CHROME, true).getURL(item.get("singerUrl"));
						}
					});
					singerButton.setPreferredSize(new Dimension(150, 30));
					singerButton.setMinimumSize(new Dimension(120, 30));
					singerButton.setMaximumSize(new Dimension(120, 30));
					singerButton.setMargin(new Insets(0, 0, 0, 2));
					singerButton.setHorizontalAlignment(SwingConstants.LEFT);
					singerButton.setForeground(new Color(105, 105, 105));
					singerButton.setBorderPainted(false);
					resultPanel.add(singerButton);
					
					JButton albumButton = new JButton(item.get("albumName"));
					albumButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new OpenWebDriver(Browser.CHROME, true).getURL(item.get("albumUrl"));
						}
					});
					albumButton.setMargin(new Insets(0, 0, 0, 2));
					albumButton.setPreferredSize(new Dimension(200, 30));
					albumButton.setMinimumSize(new Dimension(120, 30));
					albumButton.setMaximumSize(new Dimension(120, 30));
					albumButton.setHorizontalAlignment(SwingConstants.LEFT);
					albumButton.setForeground(new Color(105, 105, 105));
					albumButton.setBorderPainted(false);
					resultPanel.add(albumButton);
					
					
					setPanel.add(resultPanel);
					setPanel.revalidate();
					number += 1;
				}

				
				mentionLabel.setText("检索完毕");
				

			}
		});
		
	}

}
