package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import dao.database.UsersTableActions;
import enumItem.Area;
import enumItem.Browser;
import enumItem.Letter;
import org.openqa.selenium.WebDriver;
import service.OpenWebDriver;
import service.spider.QSingerSpider;
import utils.Log;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.ComponentOrientation;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnlineSingerIndex {

	private JPanel contentJPanel;
	
	public JPanel getContentJPanel() {
		return contentJPanel;
	}

	public void setContentJPanel(JPanel contentJPanel) {
		this.contentJPanel = contentJPanel;
	}

	Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * Create the application.
	 */
	public OnlineSingerIndex(int id, JFrame frame) {
		contentJPanel = new JPanel();
		contentJPanel.setPreferredSize(new Dimension(1000, 700));
		contentJPanel.setLayout(new BorderLayout(0,0));
		initialize(id, frame);
		Log.log("成功加载在线检索歌手界面 id=" + id);
	}
	
//	

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
		actionPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel lanPanel = new JPanel();
		lanPanel.setBackground(Color.WHITE);
		FlowLayout fl_lanPanel = (FlowLayout) lanPanel.getLayout();
		fl_lanPanel.setAlignment(FlowLayout.LEFT);
		actionPanel.add(lanPanel);
		
		JLabel lblNewLabel = new JLabel("语种");
		lanPanel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setPreferredSize(new Dimension(50, 40));
		
		final JRadioButton chnButton = new JRadioButton("华语");
		lanPanel.add(chnButton);
		
		final JRadioButton ameButton = new JRadioButton("欧美");
		lanPanel.add(ameButton);
		
		final JRadioButton jpnButton = new JRadioButton("日语");
		lanPanel.add(jpnButton);
		
		final JRadioButton korButton = new JRadioButton("韩语");
		lanPanel.add(korButton);
		
		final JRadioButton othButton = new JRadioButton("其他");
		lanPanel.add(othButton);
		
		ButtonGroup buttonGroup4 = new ButtonGroup();
		buttonGroup4.add(chnButton);
		buttonGroup4.add(ameButton);
		buttonGroup4.add(jpnButton);
		buttonGroup4.add(korButton);
		buttonGroup4.add(othButton);
		
		JPanel genPanel = new JPanel();
		genPanel.setBackground(Color.WHITE);
		FlowLayout fl_genPanel = (FlowLayout) genPanel.getLayout();
		fl_genPanel.setAlignment(FlowLayout.LEFT);
		actionPanel.add(genPanel);
		
		JLabel lblNewLabel_2 = new JLabel("性别");
		lblNewLabel_2.setPreferredSize(new Dimension(50, 40));
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		genPanel.add(lblNewLabel_2);
		
		final JRadioButton maleButton = new JRadioButton("男");
		genPanel.add(maleButton);
		
		final JRadioButton femaleButton = new JRadioButton("女");
		genPanel.add(femaleButton);
		
		final JRadioButton groupButton = new JRadioButton("组合");
		genPanel.add(groupButton);
		
		ButtonGroup buttonGroup3 = new ButtonGroup();
		buttonGroup3.add(maleButton);
		buttonGroup3.add(femaleButton);
		buttonGroup3.add(groupButton);
		
		JPanel letPanel = new JPanel();
		letPanel.setBackground(Color.WHITE);
		letPanel.setPreferredSize(new Dimension(10, 60));
		FlowLayout fl_letPanel = (FlowLayout) letPanel.getLayout();
		fl_letPanel.setAlignment(FlowLayout.LEFT);
		actionPanel.add(letPanel);
		
		JLabel lblNewLabel_2_1 = new JLabel("首字母");
		lblNewLabel_2_1.setPreferredSize(new Dimension(50, 16));
		lblNewLabel_2_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		letPanel.add(lblNewLabel_2_1);
		
		final JRadioButton buttonA = new JRadioButton("A");
		letPanel.add(buttonA);
		
		final JRadioButton buttonB = new JRadioButton("B");
		letPanel.add(buttonB);
		
		final JRadioButton buttonC = new JRadioButton("C");
		letPanel.add(buttonC);
		
		final JRadioButton buttonD = new JRadioButton("D");
		letPanel.add(buttonD);
		
		final JRadioButton buttonE = new JRadioButton("E");
		letPanel.add(buttonE);
		
		final JRadioButton buttonF = new JRadioButton("F");
		letPanel.add(buttonF);
		
		final JRadioButton buttonG = new JRadioButton("G");
		letPanel.add(buttonG);
		
		final JRadioButton buttonH = new JRadioButton("H");
		letPanel.add(buttonH);
		
		final JRadioButton buttonI = new JRadioButton("I");
		letPanel.add(buttonI);
		
		final JRadioButton buttonJ = new JRadioButton("J");
		letPanel.add(buttonJ);
		
		final JRadioButton buttonK = new JRadioButton("K");
		letPanel.add(buttonK);
		
		final JRadioButton buttonL = new JRadioButton("L");
		letPanel.add(buttonL);
		
		final JRadioButton buttonM = new JRadioButton("M");
		letPanel.add(buttonM);
		
		final JRadioButton buttonN = new JRadioButton("N");
		letPanel.add(buttonN);
		
		final JRadioButton buttonO = new JRadioButton("O");
		letPanel.add(buttonO);
		
		final JRadioButton buttonP = new JRadioButton("P");
		letPanel.add(buttonP);
	
		
		final JRadioButton buttonQ = new JRadioButton("Q");
		letPanel.add(buttonQ);
		
		final JRadioButton buttonR = new JRadioButton("R");
		letPanel.add(buttonR);
		
		final JRadioButton buttonS = new JRadioButton("S");
		letPanel.add(buttonS);
		
		final JRadioButton buttonT = new JRadioButton("T");
		letPanel.add(buttonT);
		
		final JRadioButton buttonU = new JRadioButton("U");
		letPanel.add(buttonU);
		
		final JRadioButton buttonV = new JRadioButton("V");
		letPanel.add(buttonV);
		
		final JRadioButton buttonW = new JRadioButton("W");
		letPanel.add(buttonW);
		
		final JRadioButton buttonX = new JRadioButton("X");
		letPanel.add(buttonX);
		
		final JRadioButton buttonY = new JRadioButton("Y");
		letPanel.add(buttonY);
		
		final JRadioButton buttonZ = new JRadioButton("Z");
		letPanel.add(buttonZ);
		
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(buttonA);
		buttonGroup2.add(buttonB);
		buttonGroup2.add(buttonC);
		buttonGroup2.add(buttonD);
		buttonGroup2.add(buttonE);
		buttonGroup2.add(buttonF);
		buttonGroup2.add(buttonG);
		buttonGroup2.add(buttonH);
		buttonGroup2.add(buttonI);
		buttonGroup2.add(buttonJ);
		buttonGroup2.add(buttonK);
		buttonGroup2.add(buttonL);
		buttonGroup2.add(buttonM);
		buttonGroup2.add(buttonN);
		buttonGroup2.add(buttonO);
		buttonGroup2.add(buttonP);
		buttonGroup2.add(buttonQ);
		buttonGroup2.add(buttonR);
		buttonGroup2.add(buttonS);
		buttonGroup2.add(buttonT);
		buttonGroup2.add(buttonU);
		buttonGroup2.add(buttonV);
		buttonGroup2.add(buttonW);
		buttonGroup2.add(buttonX);
		buttonGroup2.add(buttonY);
		buttonGroup2.add(buttonZ);
		
		
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
		plaPanel.add(qButton);
		qButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonGroup.add(qButton);
		
		JRadioButton wButton = new JRadioButton("虾米音乐");
		plaPanel.add(wButton);
		buttonGroup.add(wButton);
		
		JRadioButton xButton = new JRadioButton("网易云音乐");
		plaPanel.add(xButton);
		buttonGroup.add(xButton);
		
		JPanel retrunPanel = new JPanel();
		retrunPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		retrunPanel.setBackground(Color.WHITE);
		singerIndexPanel.add(retrunPanel, BorderLayout.CENTER);
		retrunPanel.setLayout(new CardLayout(0, 0));

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<HashMap<String, String>> mapList= new ArrayList<HashMap<String,String>>();

				if(buttonA.isSelected()) map.put("letter", Letter.A);
				if(buttonB.isSelected()) map.put("letter", Letter.B);
				if(buttonC.isSelected()) map.put("letter", Letter.C);
				if(buttonD.isSelected()) map.put("letter", Letter.D);
				if(buttonE.isSelected()) map.put("letter", Letter.E);
				if(buttonF.isSelected()) map.put("letter", Letter.F);
				if(buttonG.isSelected()) map.put("letter", Letter.G);
				if(buttonH.isSelected()) map.put("letter", Letter.H);
				if(buttonI.isSelected()) map.put("letter", Letter.I);
				if(buttonJ.isSelected()) map.put("letter", Letter.J);
				if(buttonK.isSelected()) map.put("letter", Letter.K);
				if(buttonL.isSelected()) map.put("letter", Letter.L);
				if(buttonM.isSelected()) map.put("letter", Letter.M);
				if(buttonN.isSelected()) map.put("letter", Letter.N);
				if(buttonO.isSelected()) map.put("letter", Letter.O);
				if(buttonP.isSelected()) map.put("letter", Letter.P);
				if(buttonQ.isSelected()) map.put("letter", Letter.Q);
				if(buttonR.isSelected()) map.put("letter", Letter.R);
				if(buttonS.isSelected()) map.put("letter", Letter.S);
				if(buttonT.isSelected()) map.put("letter", Letter.T);
				if(buttonU.isSelected()) map.put("letter", Letter.U);
				if(buttonV.isSelected()) map.put("letter", Letter.V);
				if(buttonW.isSelected()) map.put("letter", Letter.W);
				if(buttonX.isSelected()) map.put("letter", Letter.X);
				if(buttonY.isSelected()) map.put("letter", Letter.Y);
				if(buttonZ.isSelected()) map.put("letter", Letter.Z);

				if (maleButton.isSelected()){
					if(chnButton.isSelected()) map.put("type", Area.CHN_M);
					if(ameButton.isSelected()) map.put("type", Area.AME_M);
					if(jpnButton.isSelected()) map.put("type", Area.JPN_M);
					if(korButton.isSelected()) map.put("type", Area.KOR_M);
					if(othButton.isSelected()) map.put("type", Area.OTH_M);
				}

				if (femaleButton.isSelected()){
					if(chnButton.isSelected()) map.put("type", Area.CHN_F);
					if(ameButton.isSelected()) map.put("type", Area.AME_F);
					if(jpnButton.isSelected()) map.put("type", Area.JPN_F);
					if(korButton.isSelected()) map.put("type", Area.KOR_F);
					if(othButton.isSelected()) map.put("type", Area.OTH_F);
				}

				if (groupButton.isSelected()){
					if(chnButton.isSelected()) map.put("type", Area.CHN_G);
					if(ameButton.isSelected()) map.put("type", Area.AME_G);
					if(jpnButton.isSelected()) map.put("type", Area.JPN_G);
					if(korButton.isSelected()) map.put("type", Area.KOR_G);
					if(othButton.isSelected()) map.put("type", Area.OTH_G);
				}

				WebDriver driver = new OpenWebDriver(Browser.CHROME, false).getDriver();
				mapList = new QSingerSpider(driver).spider((Area)map.get("type"), (Letter)map.get("letter"));

				for (HashMap<String, String> item: mapList) {
					System.out.println(item);
				}

				driver.quit();


			}
		});
		
	}

}
