package com.thesophist.window.macro;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class JPanelHome extends JPanel {

	public static DragDropList list;
	
	JButton btnPlay, btnMacroRecoder, btnAddAction, btnDelAction, btnSaveMacro;
	
	public JPanelHome() {
		
		setLayout(null);
		
		list = new DragDropList();
		JScrollPane spList = new JScrollPane(list);
		spList.setBounds(10, 67, 269, 373);
		add(spList);
		
		btnPlay = new JButton("\u25B6");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < JPanelHome.list.getModel().getSize(); i++){
					ControlRobot.unpackEvent(JPanelHome.list.getModel().getElementAt(i));
					try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		btnPlay.setBounds(229, 7, 50, 50);
		add(btnPlay);

		btnMacroRecoder = new JButton("Record");
		btnMacroRecoder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnMacroRecoder.getText()=="Record"){
					btnMacroRecoder.setText("Stop");
					try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
					GlobalListener.recordOn();
				}
				else if(btnMacroRecoder.getText()=="Stop"){
					btnMacroRecoder.setText("Record");
					GlobalListener.recordOff();
				}
			}
		});
		btnMacroRecoder.setBackground(new Color(250, 250, 210));
		btnMacroRecoder.setForeground(Color.RED);
		btnMacroRecoder.setFont(new Font("굴림", Font.BOLD, 16));
		btnMacroRecoder.setBounds(291, 67, 97, 60);
		add(btnMacroRecoder);
		
		btnAddAction = new JButton("Add Action");
		btnAddAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Frame.nextPanel();
			}
		});
		btnAddAction.setBounds(291, 164, 97, 34);
		add(btnAddAction);
		btnDelAction = new JButton("Del Action");
		btnDelAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list.deleteElement(list.getSelectedIndex());
			}
		});
		btnDelAction.setBounds(291, 208, 97, 34);
		add(btnDelAction);
		
		btnSaveMacro = new JButton("Save");
		btnSaveMacro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileInOutput.fileOutput();
			}
		});
		btnSaveMacro.setBounds(291, 393, 97, 47);
		add(btnSaveMacro);
		
		JComboBox<?> cbChoose = new JComboBox<Object>();
		cbChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		cbChoose.setBounds(10, 7, 207, 50);
		add(cbChoose);
	}
	public void refreshJList(){
		//list에 할당되었던 드래그드롭 삭제
		list = null;
		System.gc();
		//list에 할당되었던 드래그드롭 삭제
		
		//이미 macrolist는 수정했으므로 JList 초기화만 해주면됨.
		list = new DragDropList();
		list.setBounds(10, 67, 269, 373);
		Main.frameRefresh();
	}
	public void getLists(){
		FileInOutput.macrolist=null;
		FileInOutput.macrolist=new String[list.getModel().getSize()];
		for (int i = 0; i < list.getModel().getSize(); i++) {
            FileInOutput.macrolist[i] = list.getModel().getElementAt(i).toString();
            System.out.println("Item = " +FileInOutput.macrolist[i]);
        }
	}
	
	void addKeyListener(){
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}			
		});
		setFocusable(true);
		requestFocusInWindow();
	}
}
