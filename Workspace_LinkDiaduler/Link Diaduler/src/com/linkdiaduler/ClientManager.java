package com.linkdiaduler;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ClientManager {
	private static Socket socket;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static CarrierFromServer CFS;
	
	public static boolean connect(CarrierFromClient CFC){
		try {
			socket=new Socket("192.168.1.13", 4567);
			oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(CFC);
			oos.flush();
			
			ois=new ObjectInputStream(socket.getInputStream());
			CFS=(CarrierFromServer)ois.readObject();//객체받고 CFC로 캐스팅!
			if(!CFS.happening.equals("success")){
				JOptionPane.showMessageDialog(null, CFS.happening, "Error!", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			System.out.println(CFS.happening);
			//success
			switch(CFC.happening){
			case "REGISTER":
				DataManager.serialCode=CFS.serialCode;
				DataManager.user=CFS.nickname;
				DataManager.SSClist="";
				DataManager.registernum=CFS.registernum;
				break;
			case "LOGIN":
				DataManager.serialCode=CFS.serialCode;
				DataManager.user=CFS.nickname;
				DataManager.SSClist=CFS.SSCList;
				DataManager.registernum=CFS.registernum;
				break;
			case "DOWNLOAD_WEEK_CONTENTS":
				DataManager.weekText=CFS.weekText.replace("null", "");
				DataManager.weekImageContents=CFS.weekImageContents;
				DataManager.dayofweekICnum=CFS.imagenum;
				for(int i=0; i<7; i++){
					DataManager.dayofweekImage[i]=new BufferedImage[DataManager.dayofweekICnum[i]];
					for(int j=0; j<DataManager.dayofweekICnum[i]; j++){
						Image image = CFS.dayofweekimage[i][j].getImage();  
						BufferedImage buffered = new BufferedImage(  
								CFS.dayofweekimage[i][j].getIconWidth(),  
								CFS.dayofweekimage[i][j].getIconHeight(),  
								BufferedImage.TYPE_INT_RGB);

						Graphics2D g = buffered.createGraphics();  
						g.drawImage(image, 0, 0, null);  
						g.dispose();  

						DataManager.dayofweekImage[i][j]=buffered;
					}
				}
				break;
			case "UPLOAD_INDEX":
				break;
			case "UPLOAD_IMAGE_ADD":
				break;
			case "UPLOAD_IMAGE-DEL":
				break;
			case "SUBSCRIBE":
				DataManager.SSCserialCode=CFS.serialCode;
				break;
			case "ADD_SUBSCRIBE":
				DataManager.SSClist=(CFC.SSClist+" "+CFC.SSCnickname);
				break;
			case "DEL_SUBSCRIBE":
				DataManager.SSClist=CFC.SSClist.replace(" "+CFC.SSCnickname, "");
				break;
			}
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			
			try {
				ois.close();
				oos.close();
				socket.close();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		return false;
	}
}
