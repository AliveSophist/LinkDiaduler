package com.linkdiaduler.temp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DataManager{
	//필수 세팅.
	public static boolean onlinemode=false;
	public static String user;
	public static String route="";//링크 다이어쥴러 루트
	public static String defaultURL="http://cafe.naver.com/linkdiaduler.cafe";
	//온라인모드전용
	public static String serialCode=null;
	public static String SSCserialCode="test";
	public static String SSClist=null;
	public static int registernum=0;
	public static int updateRepeatnum=0;
	//온라인모드, 구독모드에서 다른 역할함.
	protected static String decodedText; //index를 String으로 통짜 로드 = 모든날짜정보
	protected static String decodedImage;
	
	public static String weekText;
	public static String[] dayofweekText=new String[7]; //decoded 문자열에서 해당 주만 뽑아냄
	
	public static String weekImageContents;
	public static String[] dayofweekContents=new String[7];
	public static int[] dayofweekICnum=new int[7]; //이미지 갯수기억-오프라인 계정엔 이미지 무한대로 저장가능
	public static String[][] dayofweekImagename=new String[7][];//[dayofweek][imagenum]
	public static BufferedImage[][] dayofweekImage=new BufferedImage[7][];
	public static String[][] dayofweekURL=new String[7][];
	
	public static BufferedImage loadimage(String imagename, String extensionname){
		Image image=Toolkit.getDefaultToolkit().getImage(Main.class.getClassLoader().getResource(imagename+"."+extensionname));
		return toBufferedImage(image);
	}
	public static String[] returnUserArray(){
		return (new File(DataManager.route+"data\\user\\").list());
	}
	public static String[] returnSSCArray(){
		return SSClist.split(" ");
	}
	
	public static void loadSettings() {
		try
        {
			if(!new File("settings.ini").exists()){
				BufferedWriter settingsWriter = new BufferedWriter(new FileWriter(route+"settings.ini"));

	            String index="";
	            index+="ldWidth 500\n";
	            index+="ldHeight 870\n";
	            index+="backgroundR 255\n";
	            index+="backgroundG 255\n";
	            index+="backgroundB 153\n";
	            index+="buttonR 255\n";
	            index+="buttonG 255\n";
	            index+="buttonB 204\n";
	            index+="fontR 0\n";
	            index+="fontG 0\n";
	            index+="fontB 0\n";
	            
	            settingsWriter.write(index);
	            settingsWriter.close();
			}
			
			
				BufferedReader br = new BufferedReader(	new FileReader("settings.ini"));
				String line;
				
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//ldWidth
				Settings.LDWidth=Integer.parseInt(line);
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//ldHeight
				Settings.LDHeight=Integer.parseInt(line);
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//backgroundRGB R
				Settings.backgroundRGB[0]=Integer.parseInt(line);
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//backgroundRGB G
				Settings.backgroundRGB[1]=Integer.parseInt(line);
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//backgroundRGB B
				Settings.backgroundRGB[2]=Integer.parseInt(line);
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//buttonRGB R
				Settings.buttonRGB[0]=Integer.parseInt(line);
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//buttonRGB G
				Settings.buttonRGB[1]=Integer.parseInt(line);
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//buttonRGB B
				Settings.buttonRGB[2]=Integer.parseInt(line);
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//fontRGB R
				Settings.fontRGB[0]=Integer.parseInt(line);
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//fontRGB G
				Settings.fontRGB[1]=Integer.parseInt(line);
				line = br.readLine();
				line=line.substring(line.indexOf(" ")+1);//fontRGB B
				Settings.fontRGB[2]=Integer.parseInt(line);
				
				Settings.background = new Color(Settings.backgroundRGB[0], Settings.backgroundRGB[1], Settings.backgroundRGB[2]);
				Settings.button = new Color(Settings.buttonRGB[0], Settings.buttonRGB[1], Settings.buttonRGB[2]);
				Settings.font = new Color(Settings.fontRGB[0], Settings.fontRGB[1], Settings.fontRGB[2]);
				
				br.close();
		
        }
		catch(FileNotFoundException e) {
        	
        }
		catch (IOException e) {
			
		}
    }
	
	public static void saveSettings(int ldwidth, int ldheight, int[] backRGB, int[] buttonRGB, int[] fontRGB) {
		BufferedWriter settingsWriter;
		try {
			settingsWriter = new BufferedWriter(new FileWriter(route+"settings.ini"));

			String index="";
			index+="ldWidth "+ldwidth+"\n";
			index+="ldHeight "+ldheight+"\n";
			index+="backgroundR "+backRGB[0]+"\n";
			index+="backgroundG "+backRGB[1]+"\n";
			index+="backgroundB "+backRGB[2]+"\n";
			index+="buttonR "+buttonRGB[0]+"\n";
			index+="buttonG "+buttonRGB[1]+"\n";
			index+="buttonB "+buttonRGB[2]+"\n";
			index+="fontR "+fontRGB[0]+"\n";
			index+="fontG "+fontRGB[1]+"\n";
			index+="fontB "+fontRGB[2]+"\n";

			settingsWriter.write(index);
			settingsWriter.close();
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	}

	public static void deleteUser(String deleteUser){
		user=deleteUser;
		decodeIndex('i');
		if(!decodedImage.substring(3, 7).contains("||")){
			String deleteImages=decodedImage.substring(4, decodedImage.indexOf("|1989")-1);
			deleteImages.replace("|", "");
			StringTokenizer st=new StringTokenizer(deleteImages, ".");
		
			while(st.hasMoreTokens())
				new File(route+"data\\image\\"+st.nextToken()+".png").delete();
		}
		new File(route+"data\\user\\"+deleteUser+"\\ImageIndex.index").delete();
		new File(route+"data\\user\\"+deleteUser+"\\TextIndex.index").delete();
		new File(route+"data\\user\\"+deleteUser).delete();
	}
	
	public static void makeDataDirectory() {
		if(!new File(route+"data\\user").exists()&&!new File(route+"data\\image").exists()){
			File makeDirectory = new File(route+"data");
			makeDirectory.mkdir();
			makeDirectory = new File(route+"data\\user");
			makeDirectory.mkdir();
			makeDirectory = new File(route+"data\\image");
			makeDirectory.mkdir();
		}
	}
	
	public static void makeUserDirectory(String user) {
		File makeDirectory = new File(route+"data\\user\\"+user);
		try
        {
			makeDirectory.mkdir();
            BufferedWriter imageWriter = new BufferedWriter(new FileWriter(route+"data\\user\\"+user+"\\ImageIndex.index"));
            BufferedWriter textWriter = new BufferedWriter(new FileWriter(route+"data\\user\\"+user+"\\TextIndex.index"));

//			보류중.. 인덱스[달력]생성기			(오래걸리고 많이잡아먹음. 아마 Calendar 클래스의 약3.6만번 반복 때문인듯.
//										ETC_resources클래스에 저장된 출력된 결과물로 인덱스파일을 생성하자.)
//            DateManager.warpDate(1990, 1, 1);
//            
//            index=DateManager.toString(DateManager.getOriginal(),5)+"||";
//        	DateManager.addDate(DateManager.getOriginal(), 1);
//            
//        	
//            
//            for(int i=0; i<36524; i++){
//            	index+=DateManager.toString(DateManager.getOriginal(),5)+"||";
//            	DateManager.addDate(DateManager.getOriginal(), 1);
//            }
            
            String index=new ETC_resources().index;
            
            imageWriter.write(index);
            textWriter.write(index);

            imageWriter.close();
            textWriter.close();
            
            index=null;
        }
		catch(FileNotFoundException e) {
        	e.printStackTrace();
        }
		catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
	
	public static void decodeIndex(char kindofindex){

		String line=null;

		if((new File(route+"data\\user\\"+user)).exists()==true){
			try {
				if(kindofindex=='t'){
					BufferedReader br = new BufferedReader(	new FileReader(route+"data\\user\\"+user+"\\TextIndex.index"));
					decodedText = br.readLine();
					while ((line = br.readLine()) != null)
						decodedText+=("\n"+line);
					br.close();
				}
				if(kindofindex=='i'){
					BufferedReader br = new BufferedReader(	new FileReader(route+"data\\user\\"+user+"\\ImageIndex.index"));
					decodedImage = br.readLine();
					while ((line = br.readLine()) != null)
						decodedImage+=line;
					br.close();
				}	
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	public static void saveIndex(char kindofindex){//텍스트저장시,이미지추가시
		if(onlinemode){
			if(kindofindex=='t')
				ClientManager.connect(new CarrierFromClient(serialCode, kindofindex, updateRepeatnum, decodedText));
			if(kindofindex=='i')
				ClientManager.connect(new CarrierFromClient(serialCode, kindofindex, updateRepeatnum, decodedImage));
		}
		else{
			try {
				if(kindofindex=='t'){
					BufferedWriter textWriter = new BufferedWriter(new FileWriter(route+"data\\user\\"+user+"\\TextIndex.index"));

					textWriter.write(decodedText);

					textWriter.close();
				}
				if(kindofindex=='i'){
					BufferedWriter textWriter = new BufferedWriter(new FileWriter(route+"data\\user\\"+user+"\\ImageIndex.index"));

					textWriter.write(decodedImage);

					textWriter.close();
				}	
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			decodeIndex(kindofindex);//세이브후 반드시 인덱스 리로드
		}
	}



	public static void sortDOWText(){//해당주만 뽑아냄. 텍스트는 주당으로 불러와야하니께.
		if(!DataManager.onlinemode){
			int sunIndex=decodedText.indexOf(DateManager.toString("sunCal",5));
			int nsunIndex=decodedText.indexOf(DateManager.toString("nextSunCal",5));
			weekText=decodedText.substring(sunIndex, nsunIndex);
		}
		
		StringTokenizer st=new StringTokenizer(weekText, "||");
		
		for(int i=0; i<7; i++){
			dayofweekText[i]=st.nextToken();
			
			if(dayofweekText[i].contains(" ")==true)//저장된 텍스트가 있을경우 dayofweekText배열에 로드(공백이 있는지 없는지 확인)
				dayofweekText[i]=dayofweekText[i].substring(dayofweekText[i].indexOf(" ")+1);
			else
				dayofweekText[i]="";
		}
	}
	
	
	public static String fixDecodedText(String text){//날짜 워프하면서 하루치 변경잼
		text=text.replace("|", "");
		text=text.replace("'", "");//불적합 문자 모두 삭제
		
		text=text.substring(text.indexOf("<body>")+11, text.indexOf("</body>")-3);
		
		if(!onlinemode){
			int fixDateIndex=decodedText.indexOf(DateManager.toString(DateManager.getOriginal(),5));
			decodedText=decodedText.replace(decodedText.substring(fixDateIndex, fixDateIndex+decodedText.substring(fixDateIndex).indexOf("||")), DateManager.toString(DateManager.getOriginal(), 5)+". "+text);
			return "";//반환할필요가없음
		}
		else{
			return DateManager.toString(DateManager.getOriginal(),6)+". "+text+"||";
		}
	}
	
	public static void fixDecodedText(int dayofweek, String text, int length){//7일치 변경잼. 하루하루씩 변환-> 토요일 대입시 이벤트발생!!(리소스 최적화)
		text=text.replace("|", "");
		text=text.replace("'", "");//부적합 문자 모두 삭제

		if(length==0)
			dayofweekText[dayofweek]="";
		else
			dayofweekText[dayofweek]=" "+text.substring(text.indexOf("<body>")+11, text.indexOf("</body>")-3);
		//7일치 모두 대입시 decodedText에서 weekText를 (날짜+공백[=구분자]+내용+||+)x7로 개조된 내용으로 변경.
		//~이후 저장까지해야 정보가 인덱스에 기록됨
		if(dayofweek==6)
			if(!onlinemode)
				decodedText=decodedText.replace(weekText,(DateManager.toString("sunCal", 5)+"."+dayofweekText[0]+"||"+DateManager.toString("monCal", 5)+"."+dayofweekText[1]+"||"+DateManager.toString("tueCal", 5)+"."+dayofweekText[2]+"||"+DateManager.toString("wedCal", 5)+"."+dayofweekText[3]+"||"+DateManager.toString("thuCal", 5)+"."+dayofweekText[4]+"||"+DateManager.toString("friCal", 5)+"."+dayofweekText[5]+"||"+DateManager.toString("satCal", 5)+"."+dayofweekText[6])+"||");
			else{
				decodedText=(DateManager.toString("sunCal", 6)+"."+dayofweekText[0]+"||"+DateManager.toString("monCal", 6)+"."+dayofweekText[1]+"||"+DateManager.toString("tueCal", 6)+"."+dayofweekText[2]+"||"+DateManager.toString("wedCal", 6)+"."+dayofweekText[3]+"||"+DateManager.toString("thuCal", 6)+"."+dayofweekText[4]+"||"+DateManager.toString("friCal", 6)+"."+dayofweekText[5]+"||"+DateManager.toString("satCal", 6)+"."+dayofweekText[6]+"||");
				updateRepeatnum=7;
			}
		}


	public static void sortDOWImageContents(){//이미지도 주당으로 불러오되. 오프라인 계정에선 이미지 갯수 제한이 없으므로.. 서버 등록가능이미지는 6개까지되 
		if(!DataManager.onlinemode){
			int sunIndex=decodedImage.indexOf(DateManager.toString("sunCal",5));
			int nsunIndex=decodedImage.indexOf(DateManager.toString("nextSunCal",5));
			weekImageContents=decodedImage.substring(sunIndex, nsunIndex);
		}
		
		StringTokenizer st=new StringTokenizer(weekImageContents, "||");
		String[] dayofweekIC=new String[7];
		
		for(int i=0; i<7; i++)
			dayofweekIC[i]=st.nextToken();
		
		for(int i=0; i<7; i++){
			if(dayofweekIC[i].contains(" ")){//공백은 구분자로서 이미지 유무를 판단함
				if(onlinemode)
					dayofweekContents[i]=dayofweekIC[i].substring(dayofweekIC[i].indexOf(" "));
				st=new StringTokenizer(dayofweekIC[i], " ");
				dayofweekICnum[i]=st.countTokens()-1;
				
				dayofweekImagename[i]=new String[dayofweekICnum[i]];//해당날짜(i)의 이미지컨텐츠 갯수로 배열갯수 초기화.
				dayofweekURL[i]=new String[dayofweekICnum[i]];
				if(!onlinemode)
					dayofweekImage[i]=new BufferedImage[dayofweekICnum[i]];
				st.nextToken();//dayofweekIC [토크니저로 나눈결과(해당요일의 날짜 -공백- 이미지+:::+정보 -공백- 이미지:::정보  -공백- 이미지:::정보..)]
							   //의 첫토큰은 날짜이므로 스킵함.

				int j=0;
				while(j<dayofweekICnum[i]){//이미지 토큰 갯수만큼 반복해줌.
					String devideIC=st.nextToken();
						
					dayofweekImagename[i][j]=devideIC.substring(0, devideIC.indexOf(":"));
					dayofweekURL[i][j]=devideIC.substring(devideIC.lastIndexOf(":::")+3);
					
					if(!onlinemode){
						File source=new File(route+"data\\image\\"+dayofweekImagename[i][j]+".png");
					
						if(!source.exists())
							dayofweekImage[i][j]=LinkDiaduler.noimage;
						else
							try {
								dayofweekImage[i][j]=ImageIO.read(new File(route+"data\\image\\"+dayofweekImagename[i][j]+".png"));
							} catch (IOException e) {
								// TODO 자동 생성된 catch 블록
								e.printStackTrace();
							}
					}
					j++;
				}
				
			}
			else{//내용없을경우 갯수를 0으로 초기화잼
				if(onlinemode)
					dayofweekContents[i]=null;
				dayofweekICnum[i]=0;
			}
		}
	}
	
	public static void fixDecodedImage(String identifier, Calendar accessDate, String imageName, String url){
		//이미지 추가시..
		if(identifier=="add"){
			if(imageName.length()!=0){
				if(onlinemode){
					String replaced=DateManager.toString(accessDate, 6)+". "+imageName+":::"+url;
					decodedImage=replaced;
					if(dayofweekICnum[accessDate.get(Calendar.DAY_OF_WEEK)-1]!=0)
						decodedImage=DateManager.toString(accessDate, 6)+"."+" "+imageName+":::"+url+dayofweekContents[accessDate.get(Calendar.DAY_OF_WEEK)-1];
				}
				else{
					String replaced=DateManager.toString(accessDate, 5)+". "+imageName+":::"+url;
					decodedImage=decodedImage.replace(DateManager.toString(accessDate, 5)+".", replaced);
					decodedImage=decodedImage.replace("0.0", "0.0"+"."+imageName);
				}
			}
		}
		if(identifier=="del"){
			if(onlinemode){
				decodedImage=dayofweekContents[accessDate.get(Calendar.DAY_OF_WEEK)-1].replace(" "+imageName+":::"+url, "");
				decodedImage=DateManager.toString(accessDate, 6)+"."+decodedImage;
				System.out.println(decodedImage);
			}
			else{
				String replacing=(" "+imageName+":::"+url);
				decodedImage=decodedImage.replace(replacing, "");
			
				new File(route+"data\\image\\"+imageName+".png").delete();
			}
		}
	}
	public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage)
        return (BufferedImage)image;

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels
        boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;

            if (hasAlpha == true)
                transparency = Transparency.BITMASK;

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();

            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) { } //No screen

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;

            if (hasAlpha == true) {type = BufferedImage.TYPE_INT_ARGB;}
                bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }
	public static boolean hasAlpha(Image image) {
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage)
            return ((BufferedImage)image).getColorModel().hasAlpha();

        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) { }

        // Get the image's color model
        return pg.getColorModel().hasAlpha();
    }
}
