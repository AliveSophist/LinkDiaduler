package com.linkdiaduler;

import java.awt.*;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Capture {
	
	public JFrame frameCapture;
	
	private JButton saveImage;
	private Robot robot;
	private BufferedImage bfImg;
	
	private Calendar accessDate;
	
	public String imagename=ImageContainer.returnName();
	public String url;
	
	private JTextField txtCaptureURL;
	
	public Capture(Calendar accessDate){
		this.accessDate=accessDate;
		initialize();
	}
	
	private void initialize(){
		Main.linkdiaduler.frameLinkDiaduler.setVisible(false);
		
		frameCapture = new JFrame();
		frameCapture.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.linkdiaduler.frameLinkDiaduler.setVisible(true);
				JFrame.setDefaultLookAndFeelDecorated(false);
			}
		});
		frameCapture.setAlwaysOnTop(true);
		frameCapture.setType(Type.UTILITY);
		frameCapture.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					frameCapture.dispose();
			}
		});
        frameCapture.setSize(240,200);
		frameCapture.setTitle(DateManager.toString(accessDate, 5)+" - Capture");
        frameCapture.setLocationRelativeTo(null);
        
        saveImage = new JButton();
        saveImage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        frameCapture.getContentPane().add(saveImage);
        
        txtCaptureURL = new JTextField();
        txtCaptureURL.setText("URL�Է¶�. default=��ũ�����췯 Ȩ������");
        txtCaptureURL.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		txtCaptureURL.setText("http://");
        	}
        });
   
        saveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if(frameCapture.getTitle()!="Click image to Save"){//������ �ٷ� Ŭ���� ĸ��+ĸ�ĵ� �̹��� �����ϰԶ��
        			try{
        				robot = new Robot();
	        			bfImg = new BufferedImage(frameCapture.getContentPane().getWidth(), frameCapture.getContentPane().getHeight(), BufferedImage.TYPE_INT_RGB);
	        		
	        			frameCapture.setOpacity(0f);
	        			bfImg = robot.createScreenCapture(new Rectangle(frameCapture.getX()+5, frameCapture.getY()+frameCapture.getContentPane().getY()+5, frameCapture.getContentPane().getWidth(), frameCapture.getContentPane().getHeight()));
	        			frameCapture.setOpacity(1f);

	        			ImageIcon icon = new ImageIcon(bfImg);
	        			saveImage.setIcon(icon);
	        			saveImage.setVerticalTextPosition(SwingConstants.TOP);
	        			frameCapture.setTitle("Click image to Save");
	        			frameCapture.setSize(frameCapture.getWidth(), frameCapture.getHeight()+22);
	        			frameCapture.getContentPane().add(txtCaptureURL, BorderLayout.SOUTH);
        			}
    	        	catch(Exception x){
	        			x.printStackTrace();
	        		}
        		}
        		
        		else{ //ĸ���� Ŭ���� ĸ�ĵ� �̹����� �̸��� ���� �߱޹޾Ҵ��ɷ� �����ϰ�,  �ش����� �̹����ε����� �̹����̸��� url�� �߰���
        			url=txtCaptureURL.getText();
        			url.replace("|", "");
        			url.replace(" ", "");
        			url.replace(":", "");
        			if(txtCaptureURL.getText().equals("URL�Է¶�. default=��ũ�����췯 Ȩ������"))
    					url=DataManager.defaultURL;
        			
        			ImageContainer.saveImage(bfImg, imagename, accessDate, url);
        			
        			frameCapture.dispose();

        			Main.weekContentsChanger();

        			Main.linkdiaduler.frameLinkDiaduler.setVisible(true);
        			JFrame.setDefaultLookAndFeelDecorated(false);
        		}
        	}
        });// end of ���콺 ������
    }// end of initialize()
}