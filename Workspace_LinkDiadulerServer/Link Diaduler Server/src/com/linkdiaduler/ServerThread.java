package com.linkdiaduler;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * ���� Ŭ���̾�Ʈ�� �޼����� �ְ� �޴� ������
 * 
 * 1. extends Thread
 * 2. run()
 *
 */
public class ServerThread extends Thread{
	//��������� ����
	private Socket socket;	//Ŭ���̾�Ʈ�� �޼����� �ְ� �޴� ����Ͻ�(Socket)
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String userIP;
	private CarrierFromServer CFS;

	ServerThread(Socket s){//���ϰ�����
		this.socket = s;
		this.userIP = s.getInetAddress().getHostAddress();
	}
	
	//�������̵��� ��� throw �Ұ�. 
	public void run(){
		try{
			service();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				closeAll();
				System.out.println("  **"+userIP+"�� ��������.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void service() throws IOException{
		ois=new ObjectInputStream(socket.getInputStream());
		CarrierFromClient CFC = null;//�ӽ��ʱ�ȭ
		try {
			CFC = (CarrierFromClient)ois.readObject();//��ü�ް� CFC�� ĳ����!
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("  ****"+userIP+"="+CFC.happening);
		String memberip=socket.getInetAddress().getHostAddress();
		
		switch(CFC.happening){
		case "REGISTER"://�����Ǵ� ���� happening, memberID, memberPW, nickname, acceptSSC, email
			if(!(SQLWords.returnIPOverlapnum(memberip)<4)){//ip 3�ߺ� �ʰ� üũ
				CFS=new CarrierFromServer("ERROR_REGISTERED_MORE_THAN_THREE");
				System.out.println(userIP+"="+CFS.happening);
				break;
			}
			if(SQLWords.returnIDOverlap(CFC.memberID)){//id �ߺ� üũ
				CFS=new CarrierFromServer("ERROR_ID_OVERLAP");
				System.out.println(userIP+"="+CFS.happening);
				break;
			}
			if(SQLWords.returnNicknameOverlap(CFC.nickname)){//nickname �ߺ� üũ
				CFS=new CarrierFromServer("ERROR_Nickname_OVERLAP");
				System.out.println(userIP+"="+CFS.happening);
				break;
			}
			if(!isValidEmail(CFC.email)){//email ��ȿ�� Ȯ��
				CFS=new CarrierFromServer("ERROR_NOT_VALID_EMAIL");
				System.out.println(userIP+"="+CFS.happening);
				break;
			}
			else{
			//success
				String serialcode=SQLWords.returnSerialCode();
				File makeDirectory = new File(Main.route+serialcode);
				makeDirectory.mkdir();
				SQLWords.createUserTable(serialcode);
				SQLWords.insertUserAccount(CFC.memberID, CFC.memberPW, CFC.nickname, memberip, serialcode, CFC.acceptSSC, CFC.email);
				CFS=new CarrierFromServer("success", serialcode, CFC.nickname, null, SQLWords.returnIPOverlapnum(memberip));
				break;
				}
		case "LOGIN"://�����Ǵ� ���� happening, memberID, memberPW, nickname, acceptSSC, email
			if(!SQLWords.checkUserInfo("memberid", CFC.memberID, "memberid", CFC.memberID)){//���̵� ���翩��
				System.out.println(SQLWords.checkUserInfo("memberid", CFC.memberID, "memberid", CFC.memberID));
				CFS=new CarrierFromServer("ERROR_ID_NOT_EXIST");
				System.out.println(userIP+"="+CFS.happening);
				break;
			}
			if(!SQLWords.checkUserInfo("memberpw", CFC.memberPW, "memberid", CFC.memberID)){//�н����� Ȯ��
				System.out.println(SQLWords.checkUserInfo("memberpw", CFC.memberPW, "memberid", CFC.memberID));
				CFS=new CarrierFromServer("ERROR_PW_NOT_CORRECT");
				System.out.println(userIP+"="+CFS.happening);
				break;
			}
			//success
			else{
				CFS=new CarrierFromServer("success", SQLWords.returnUserInfo("serialcode", "memberpw", CFC.memberPW), SQLWords.returnUserInfo("nickname", "memberpw", CFC.memberPW), SQLWords.returnUserInfo("ssclist", "memberpw", CFC.memberPW), SQLWords.returnIPOverlapnum(memberip));
				break;
			}
		case "DOWNLOAD_WEEK_CONTENTS":
			//success
			String[] dayofweekIC=new String[7];
			int[] dayofweekICnum=new int[7];
			ImageIcon[][] dayofweekImage=new ImageIcon[7][];
			StringTokenizer st=new StringTokenizer(SQLWords.returnUserWeekIndex(CFC.serialCode, "images", CFC.accessDate), "||");
			for(int i=0; i<7; i++)
				dayofweekIC[i]=st.nextToken();
			String[][] dayofweekImagename=new String[7][];
			for(int i=0; i<7; i++){
				if(dayofweekIC[i].contains(" ")){//������ �����ڷμ� �̹��� ������ �Ǵ���
					st=new StringTokenizer(dayofweekIC[i], " ");
					dayofweekICnum[i]=st.countTokens()-1;
					dayofweekImagename[i]=new String[dayofweekICnum[i]];//�ش糯¥(i)�� �̹��������� ������ �迭���� �ʱ�ȭ.
					dayofweekImage[i]=new ImageIcon[dayofweekICnum[i]];
					st.nextToken();//dayofweekIC [��ũ������ �������(�ش������ ��¥ -����- �̹���+:::+���� -����- �̹���:::����  -����- �̹���:::����..)]
					
					int j=0;
					while(j<dayofweekICnum[i]){//�̹��� ��ū ������ŭ �ݺ�����.
						String devideIC=st.nextToken();

						dayofweekImagename[i][j]=devideIC.substring(0, devideIC.indexOf(":"));
						dayofweekImage[i][j]=new ImageIcon(Main.route+CFC.serialCode+"\\"+dayofweekImagename[i][j]+".png");
						System.out.println("  ****"+i+","+j+"�� "+Main.route+CFC.serialCode+"\\"+dayofweekImagename[i][j]+".png �ε�. ����/ũ��"+dayofweekImage[i][j].getIconWidth()+"/"+dayofweekImage[i][j].getIconHeight());
						j++;
					}
				}
				else{//���������� �ƹ��͵� ����
				}
			}			
			CFS=new CarrierFromServer("success", SQLWords.returnUserWeekIndex(CFC.serialCode, "texts", CFC.accessDate), SQLWords.returnUserWeekIndex(CFC.serialCode, "images", CFC.accessDate), dayofweekImage, dayofweekICnum);
			break;
		case "UPLOAD_INDEX":
			System.out.println("  ****"+CFC.fixIndex);
			SQLWords.updateUserTexts(CFC.serialCode, CFC.kindIndex, CFC.repeatnum, CFC.fixIndex);
			CFS=new CarrierFromServer("success");
			break;
		case "UPLOAD_IMAGE_ADD":
			System.out.println("  ****"+CFC.fixIndex);
			SQLWords.updateUserImages(CFC.serialCode, CFC.repeatnum, CFC.fixIndex);
			
			Image image = CFC.addImage.getImage();  
			RenderedImage rendered = null;  
			if (image instanceof RenderedImage)  
			{  
			    rendered = (RenderedImage)image;  
			}  
			else  
			{  
			    BufferedImage buffered = new BufferedImage(  
			    	CFC.addImage.getIconWidth(),  
			    	CFC.addImage.getIconHeight(),  
			        BufferedImage.TYPE_INT_RGB  
			    );  
			    Graphics2D g = buffered.createGraphics();  
			    g.drawImage(image, 0, 0, null);  
			    g.dispose();  
			    rendered = buffered;  
			}
			ImageIO.write(rendered, "png", new File(Main.route+CFC.serialCode+"\\"+CFC.imagename+".png"));
			
			CFS=new CarrierFromServer("success");
			break;
		case "UPLOAD_IMAGE_DEL":
			System.out.println("  ****"+CFC.fixIndex);
			new File(Main.route+CFC.serialCode+"\\"+CFC.imagename+".png").delete();
			SQLWords.updateUserTexts(CFC.serialCode, CFC.kindIndex, CFC.repeatnum, CFC.fixIndex);
			CFS=new CarrierFromServer("success");
			break;
		case "SUBSCRIBE":
			if(!SQLWords.checkUserInfo("sscaccept", "1", "nickname", CFC.SSCnickname)){
				CFS=new CarrierFromServer("THIS_ACCOUNT_NOT_ACCEPT_SUBSCRIPTION");
				break;
			}
			CFS=new CarrierFromServer("success", SQLWords.returnUserInfo("serialcode", "nickname", CFC.SSCnickname));
			break;
		case "ADD_SUBSCRIBE":
			if(!SQLWords.checkUserInfo("sscaccept", "1", "nickname", CFC.SSCnickname)){
				CFS=new CarrierFromServer("ACCOUNT_NOT_EXIST_OR_NOT_ACCEPT_SUBSCRIPTION");
				break;
			}
			if(CFC.SSClist==null)
				SQLWords.updateSSCList(CFC.serialCode, CFC.SSCnickname);
			else
				SQLWords.updateSSCList(CFC.serialCode, CFC.SSClist+" "+CFC.SSCnickname);
			CFS=new CarrierFromServer("success");
			break;
		case "DEL_SUBSCRIBE":
			CFC.SSClist=CFC.SSClist.replace(" "+CFC.SSCnickname, "");
			SQLWords.updateSSCList(CFC.serialCode, CFC.SSClist);
			CFS=new CarrierFromServer("success");
			break;
		case "SECESSION":
			
			break;
		}
		oos=new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(CFS);
		oos.flush();
	}
	public void closeAll()throws IOException{
		if (oos != null)
			oos.close();
		if (ois != null)
			ois.close();
		if (socket != null)
			socket.close();
	}
	public static boolean isValidEmail(String email) {
		boolean err = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";   
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if(m.matches()) {
			err = true; 
		}
		return err;
	}
}