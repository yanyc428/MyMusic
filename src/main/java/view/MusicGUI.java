package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import net.bytebuddy.asm.Advice.This;

public class MusicGUI {


	public static void display(){
		JFrame frame = new JFrame("iMusic音乐- 热爱你的音乐");
		frame.setContentPane(new Login(frame).getContentJPanel());
		frame.setPreferredSize(new Dimension(1000, 700));
		frame.setMinimumSize(new Dimension(1000, 700));
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
