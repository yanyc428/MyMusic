package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock;
import utils.Log;

import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Index {

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
	public Index(String userName, String avatar, final JFrame frame) {
		contentJPanel = new JPanel();
		contentJPanel.setPreferredSize(new Dimension(1000, 700));
		contentJPanel.setLayout(new BorderLayout(0,0));
		initialize(userName, avatar, frame);
		Log.log("成功加载首页");
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String userName, String avatar, final JFrame frame) {
		
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
		
		JLabel usr_label = new JLabel("用户名");
		usr_label.setHorizontalAlignment(SwingConstants.RIGHT);
		usr_label.setMaximumSize(new Dimension(180, 30));
		usr_label.setMinimumSize(new Dimension(120, 30));
		usr_label.setPreferredSize(new Dimension(120, 30));
		usr_label.setForeground(new Color(105, 105, 105));
		ImageIcon icon = new ImageIcon(dir + avatar);
		icon.setImage(icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        usr_label.setIcon(icon);
        usr_label.setText(userName);
		panel_6.add(usr_label);
		
		JButton btnNewButton_5 = new JButton("退出账号");
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
	}

}
