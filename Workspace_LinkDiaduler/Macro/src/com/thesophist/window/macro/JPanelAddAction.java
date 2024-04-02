package com.thesophist.window.macro;

import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Choice;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.Label;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class JPanelAddAction extends JPanel {
	private static Choice chMouseButton, chMouseEvent, chOneKeyButton, chOneKeyEvent;
	private JTextField tfDelayTime;
	private Button btnAddMouseEvent, btnAddOneKeyEvent, btnAddDelay;
	
	public JPanelAddAction() {
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_A){
					Point p = MouseInfo.getPointerInfo().getLocation();
					JPanelHome.list.addElement(ControlRobot.packMouseEvent("move",p.x*10000+p.y+""));
					Frame.previousPanel();
				}
			}
			public void keyReleased(KeyEvent e) {}			
		});
		setFocusable(true);
		requestFocusInWindow();
		setLayout(null);
		
		JLabel lblCursorMove = new JLabel("\uCEE4\uC11C\uC774\uB3D9");
		lblCursorMove.setFont(new Font("����", Font.PLAIN, 20));
		lblCursorMove.setBounds(12, 10, 137, 25);
		add(lblCursorMove);
		
		Label lblCursorExplanation = new Label("\uC774\uB3D9\uD560 \uC704\uCE58\uC5D0 \uCEE4\uC11C\uB97C \uB193\uACE0 A\uB97C \uB204\uB974\uC2ED\uC2DC\uC624.");
		lblCursorExplanation.setBounds(10, 41, 235, 23);
		add(lblCursorExplanation);
		
	////////////////////////////////////////////////////////////////////
		
		JLabel lblMouseEvent = new JLabel("\uB9C8\uC6B0\uC2A4 \uC774\uBCA4\uD2B8");
		lblMouseEvent.setFont(new Font("����", Font.PLAIN, 20));
		lblMouseEvent.setBounds(10, 104, 137, 25);
		add(lblMouseEvent);
		
		chMouseButton = new Choice();
		for(int i=0; i<ControlRobot.MOUSE_BUTTON_SPECIES.length; i++)
			chMouseButton.addItem(ControlRobot.MOUSE_BUTTON_SPECIES[i]);
		chMouseButton.setBounds(10, 135, 100, 21);
		add(chMouseButton);
		
		chMouseEvent = new Choice();
		for(int i=0; i<ControlRobot.MOUSE_EVENTS_SPECIES.length; i++)
			chMouseEvent.addItem(ControlRobot.MOUSE_EVENTS_SPECIES[i]);
		chMouseEvent.setBounds(116, 135, 100, 21);
		add(chMouseEvent);
		
		btnAddMouseEvent = new Button("\uC0DD\uC131");
		btnAddMouseEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chMouseEvent.getSelectedItem()=="press&release"){
					JPanelHome.list.addElement(ControlRobot.packMouseEvent("press",chMouseButton.getSelectedItem()));
					JPanelHome.list.addElement(ControlRobot.packMouseEvent("release",chMouseButton.getSelectedItem()));
				}
				else
					ControlRobot.packMouseEvent(chMouseEvent.getSelectedItem(),chMouseButton.getSelectedItem());
				Frame.previousPanel();
			}
		});
		btnAddMouseEvent.setBounds(314, 135, 76, 50);
		add(btnAddMouseEvent);
		
	////////////////////////////////////////////////////////////////////
		
		JLabel lblKeyEvent = new JLabel("\uD0A4\uBCF4\uB4DC \uC774\uBCA4\uD2B8");
		lblKeyEvent.setFont(new Font("����", Font.PLAIN, 20));
		lblKeyEvent.setBounds(10, 195, 137, 25);
		add(lblKeyEvent);
		
		chOneKeyButton = new Choice();
		for(int i=0; i<ControlRobot.KEY_BUTTON_SPECIES.length; i++)
			chOneKeyButton.addItem(ControlRobot.KEY_BUTTON_SPECIES[i]);
		chOneKeyButton.setBounds(10, 226, 100, 21);
		add(chOneKeyButton);
		
		chOneKeyEvent = new Choice();
		for(int i=0; i<ControlRobot.KEY_EVENTS_SPECIES.length; i++)
			chOneKeyEvent.addItem(ControlRobot.KEY_EVENTS_SPECIES[i]);
		chOneKeyEvent.setBounds(116, 226, 100, 21);
		add(chOneKeyEvent);
		
		btnAddOneKeyEvent = new Button("\uC0DD\uC131");
		btnAddOneKeyEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chOneKeyEvent.getSelectedItem()=="press&release"){
					JPanelHome.list.addElement(ControlRobot.packKeyEvent("press",chOneKeyButton.getSelectedItem()));
					JPanelHome.list.addElement(ControlRobot.packKeyEvent("release",chOneKeyButton.getSelectedItem()));
				}
				else
					JPanelHome.list.addElement(ControlRobot.packKeyEvent(chOneKeyEvent.getSelectedItem(),chOneKeyButton.getSelectedItem()));
				Frame.previousPanel();
			}
		});
		btnAddOneKeyEvent.setBounds(314, 226, 76, 50);
		add(btnAddOneKeyEvent);
		
		JLabel lblDelay = new JLabel("\uC9C0\uC5F0");
		lblDelay.setFont(new Font("����", Font.PLAIN, 20));
		lblDelay.setBounds(12, 285, 137, 25);
		add(lblDelay);
		
		tfDelayTime = new JTextField();
		tfDelayTime.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDelayTime.setBounds(12, 320, 116, 21);
		add(tfDelayTime);
		tfDelayTime.setColumns(10);
		
		btnAddDelay = new Button("\uC0DD\uC131");
		btnAddDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanelHome.list.addElement(ControlRobot.packDelay(Integer.parseInt(tfDelayTime.getText())));
				Frame.previousPanel();
			}
		});
		btnAddDelay.setBounds(314, 320, 76, 50);
		add(btnAddDelay);
	}
}