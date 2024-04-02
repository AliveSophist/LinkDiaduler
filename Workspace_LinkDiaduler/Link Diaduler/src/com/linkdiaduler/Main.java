package com.linkdiaduler;

import javax.swing.JFrame;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Calendar;

class Main{
	public static LinkDiaduler linkdiaduler;//�� ���� ��� ���� �޼ҵ� ��� ����.
	public static Capture capture;
	
	public String mode;
	
	static void openframeHome(){
		try {
			linkdiaduler = new LinkDiaduler();
		} 
		catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	static void weekContentsChanger(){//�� ������
		linkdiaduler.weekContentsChanger();
	}
	
	
	static void openframeImageContainer(Calendar accessDate){
		try {
			ImageContainer window = new ImageContainer(accessDate);
			window.frameImageContainer.setVisible(true);
		} 
		catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	static void openframeWarpDate(){
		try {
			WarpDate window = new WarpDate();
			window.frameWarpDate.setVisible(true);
		} 
		catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	static void openframeSettings(){
		try {
			Settings window = new Settings();
			window.frameSettings.setVisible(true);
		} 
		catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	static void openframeCapture(Calendar accessDate){
		// 'GraphicsDevice'�� ������â�� �����Ѵ�!!!!!!!!!!!!!!!!!!!!!!!!!!!
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        JFrame.setDefaultLookAndFeelDecorated(true);
    	try{
    		capture = new Capture(accessDate);
    		
    		capture.frameCapture.setVisible(true);
    	               
    		// Set the window to 55% opaque (55% translucent).
    		capture.frameCapture.setOpacity(0.55f);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	static void openframeMacro(){
		try {
			Macro window = new Macro();
			window.frameMacro.setVisible(true);
		} 
		catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//TODO Auto generated method stub
		DataManager.makeDataDirectory();
		DataManager.loadSettings();
		try {
			UserManager usermanager = new UserManager();
			usermanager.frameManager.setVisible(true);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}	
}