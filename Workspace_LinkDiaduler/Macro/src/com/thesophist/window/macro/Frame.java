package com.thesophist.window.macro;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	private static JPanel contentPane;
	private static JPanelHome pnlHome;
	public Frame() {		
		setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	
		pnlHome=new JPanelHome();
		contentPane.add(pnlHome);
		
		new GlobalListener();
	}
	public static void nextPanel() {
		pnlHome.setVisible(false);
		
		contentPane.add(new JPanelAddAction());
	}
	public static void previousPanel() {
		pnlHome.setVisible(true);
    }
}