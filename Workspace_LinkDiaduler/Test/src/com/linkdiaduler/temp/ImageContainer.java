package com.linkdiaduler.temp;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageContainer {

	public JFrame frameImageContainer;
	
	private JButton Capture, LoadImage;
	
	private Calendar accessDate;
	
	String url;
	private JTextField txtLoadImageURL;


	public ImageContainer(Calendar accessDate) {
		this.accessDate=accessDate;
		initialize();
	}

	private void initialize() {
		frameImageContainer = new JFrame();
		frameImageContainer.getContentPane().setBackground(Settings.background);
		frameImageContainer.setType(Type.UTILITY);
		frameImageContainer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					frameImageContainer.dispose();
			}
		});
		frameImageContainer.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-150, Toolkit.getDefaultToolkit().getScreenSize().height/2-90, 300, 180);
		frameImageContainer.getContentPane().setLayout(null);
		frameImageContainer.setTitle(DateManager.toString(accessDate, 5)+" - Image Container");
		frameImageContainer.setLocationRelativeTo(null);
		
		Capture = new JButton("Capture");
		Capture.setBackground(Settings.button);
		Capture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.openframeCapture(accessDate);
				frameImageContainer.dispose();
			}
		});
		Capture.setBounds(12, 10, 125, 122);
		frameImageContainer.getContentPane().add(Capture);
		
		LoadImage = new JButton("LoadImage");
		LoadImage.setBackground(Settings.button);
		LoadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Capture.isVisible()){
					Capture.setVisible(false);
					txtLoadImageURL.setVisible(true);
					LoadImage.setBounds(149, 87, 125, 45);
				}
				else{
					FileDialog imageOpen=new FileDialog(frameImageContainer, "파일 열기", FileDialog.LOAD); //열기 속성의 파일 다이얼로그
					imageOpen.setVisible(true);
					String imagePath = imageOpen.getDirectory()+imageOpen.getFile();
					BufferedImage bfImg;
					if(imageOpen.getFile()==null)
						return;
					if(imageOpen.getFile().contains(".jpg")||imageOpen.getFile().contains(".jpeg")||imageOpen.getFile().contains(".bmp")||imageOpen.getFile().contains(".png")||imageOpen.getFile().contains(".img")||imageOpen.getFile().contains(".gif"))
						try {
							bfImg = ImageIO.read(new File(imagePath));
							
							String url =txtLoadImageURL.getText();
							url.replace("|", "");
		        			url.replace(" ", "");
		        			url.replace(":", "");
							if(txtLoadImageURL.getText().equals("URL입력란. default=링크다이쥴러 홈페이지"))
								url=DataManager.defaultURL;
							
							saveImage(bfImg, returnName(), accessDate, url);
							frameImageContainer.dispose();
							Main.weekContentsChanger();
						} catch (IOException e1) {
							// TODO 자동 생성된 catch 블록
							JOptionPane.showMessageDialog(null, "NOT_IMAGE_FILE", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					else
						JOptionPane.showMessageDialog(null, "NOT_IMAGE_FILE", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		LoadImage.setBounds(149, 10, 125, 122);
		frameImageContainer.getContentPane().add(LoadImage);
		
		txtLoadImageURL = new JTextField();
		txtLoadImageURL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtLoadImageURL.setText("http://");
				txtLoadImageURL.setEditable(true);
			}
		});
		txtLoadImageURL.setText("URL입력란. default=링크다이쥴러 홈페이지");
		txtLoadImageURL.setBounds(12, 10, 262, 21);
		frameImageContainer.getContentPane().add(txtLoadImageURL);
		txtLoadImageURL.setColumns(10);
		txtLoadImageURL.setVisible(false);
	}

	public static String returnName(){	//이미지 이름 랜덤발급
		while(true){
			Random random = new Random();
			char code1=(char)(random.nextInt(25)+65);
			char code2=(char)(random.nextInt(25)+65);
			String code3=String.format("%04d",random.nextInt(10000));
			String formatedCode=(code1+""+code2+""+code3);
			if(new File(DataManager.route+"data\\image\\"+formatedCode+".png").exists()==false)
				return formatedCode;
		}
	}
	public static void saveImage(BufferedImage bfImg, String imagename, Calendar accessDate, String url){
		if(DataManager.onlinemode){
			if(DataManager.dayofweekICnum[accessDate.get(Calendar.DAY_OF_WEEK)-1]==8)
				JOptionPane.showMessageDialog(null, "온라인 하루당 저장 이미지 최대 갯수는 8개입니다", "Error!", JOptionPane.ERROR_MESSAGE);
			else{
				DataManager.fixDecodedImage("add", accessDate, imagename, url);
				ImageIcon imageicon = new ImageIcon(bfImg);
				System.out.print(DataManager.decodedImage);
				ClientManager.connect(new CarrierFromClient("ADD", DataManager.serialCode, DataManager.decodedImage, imagename, imageicon));

				if(Macro.nowImgMacro){
					Macro.macroImages(imagename, url);
					Macro.frameMacro.dispose();
				}
			}
		}
		else{
			try{
				File file = new File(DataManager.route+"data\\image\\"+imagename+".png");
				ImageIO.write(bfImg, "png", file);
			}
			catch(Exception x){
				x.printStackTrace();
			}//이미지 저장완료

			DataManager.fixDecodedImage("add", accessDate, imagename, url);
			
			if(Macro.nowImgMacro){
				Macro.macroImages(imagename, url);
				Macro.frameMacro.dispose();
			}
			
			DataManager.saveIndex('i');
		}
	}
}