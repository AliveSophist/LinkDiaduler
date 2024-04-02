package com.linkdiaduler.temp;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class WarpDate {

	public JFrame frameWarpDate;
	private JComboBox<Integer> cbWarpYear, cbWarpMonth, cbWarpDate;

	public WarpDate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameWarpDate = new JFrame();
		frameWarpDate.getContentPane().setBackground(Settings.background);
		frameWarpDate.setResizable(false);
		frameWarpDate.setType(Type.UTILITY);
		frameWarpDate.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-130, Toolkit.getDefaultToolkit().getScreenSize().height/2-60, 230, 120);
		frameWarpDate.getContentPane().setLayout(null);
		
		cbWarpYear = new JComboBox<Integer>();
		cbWarpYear.setForeground(Settings.font);
		cbWarpYear.setBackground(Settings.button);
		for(int year=1990; year<=DateManager.limitEnd; year++)
			cbWarpYear.addItem(year);
		cbWarpYear.setBounds(12, 10, 78, 38);
		frameWarpDate.getContentPane().add(cbWarpYear);
		
		cbWarpMonth = new JComboBox<Integer>();
		cbWarpMonth.setForeground(Settings.font);
		cbWarpMonth.setBackground(Settings.button);
		for(int month=1; month<=12; month++)
			cbWarpMonth.addItem(month);
		cbWarpMonth.setBounds(102, 10, 49, 38);
		frameWarpDate.getContentPane().add(cbWarpMonth);
		
		cbWarpDate = new JComboBox<Integer>();
		cbWarpDate.setForeground(Settings.font);
		cbWarpDate.setBackground(Settings.button);
		for(int date=1; date<=31; date++)
			cbWarpDate.addItem(date);
		cbWarpDate.setBounds(163, 10, 49, 38);
		frameWarpDate.getContentPane().add(cbWarpDate);
		
		JButton btnNewButton = new JButton("W a r p !");
		btnNewButton.setForeground(Settings.font);
		btnNewButton.setBackground(Settings.button);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateManager.warpDate((int)cbWarpYear.getSelectedItem(), (int)cbWarpMonth.getSelectedItem(), (int)cbWarpDate.getSelectedItem());
				frameWarpDate.dispose();
				
				Main.weekContentsChanger();
			}
		});
		btnNewButton.setBounds(12, 58, 200, 28);
		frameWarpDate.getContentPane().add(btnNewButton);
	}
}
