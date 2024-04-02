package com.linkdiaduler.temp;

import java.io.Serializable;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class CarrierFromClient implements Serializable{
	
	String happening;
	//�۽��ؾ��� ���
	String memberID;
	String memberPW;
	String nickname;
	int acceptSSC;
	String email;
	String serialCode;
	String kindIndex;
	int repeatnum;
	int accessDate;
	String fixIndex;
	String SSCnickname;
	String SSClist;
	String imagename;
	ImageIcon addImage;



	//�۽�(������)
	public CarrierFromClient(String memberID, String memberPW, String nickname, int acceptSSC, String email)
	{//register
		this.happening="REGISTER";
		this.memberID=memberID;
		this.memberPW=memberPW;
		this.nickname=nickname;
		this.acceptSSC=acceptSSC;
		this.email=email;
	}
	public CarrierFromClient(String memberID, String memberPW)
	{//login
		this.happening="LOGIN";
		this.memberID=memberID;
		this.memberPW=memberPW;
	}
	public CarrierFromClient(String serialCode, int accessDate)
	{//download week indexs
		this.happening="DOWNLOAD_WEEK_CONTENTS";
		this.serialCode=serialCode;
		this.accessDate=accessDate;
	}
	public CarrierFromClient(String serialCode, char kindIndex, int repeatnum, String fixIndex)//������ ���� ���뿣 repeatnum�ʼ�
	{//upload text, imagemacro
		this.happening="UPLOAD_INDEX";
		this.serialCode=serialCode;
		if(kindIndex=='t')
			this.kindIndex="texts";
		else if(kindIndex=='i')
			this.kindIndex="images";
		this.repeatnum=repeatnum;
		this.fixIndex=fixIndex;
	}
	public CarrierFromClient(String happening, String serialCode, String fixIndex, String imagename, ImageIcon addImage)//���� �ݺ�����
	{//image ADD
		this.happening=("UPLOAD_IMAGE_"+happening);
		this.serialCode=serialCode;
		this.fixIndex=fixIndex;
		this.kindIndex="images";
		this.repeatnum=1;
		this.imagename=imagename;
		this.addImage=addImage;
	}
	public CarrierFromClient(String happening, String serialCode, String fixIndex)//���� �ݺ�����
	{//image DEL
		this.happening=("UPLOAD_IMAGE_"+happening);
		this.serialCode=serialCode;
		this.fixIndex=fixIndex;
		this.kindIndex="images";
		this.repeatnum=1;
	}
	public CarrierFromClient(String SSCnickname)
	{//download SSC serialcode
		happening="SUBSCRIBE";
		this.SSCnickname=SSCnickname;
	}
	public CarrierFromClient(char happening, String serialCode, String SSClist, String SSCnickname)
	{//add del subscribe
		if(happening=='a')
			this.happening="ADD_SUBSCRIBE";
		else// 'd'
			this.happening="DEL_SUBSCRIBE";
		this.serialCode=serialCode;
		this.SSClist=SSClist;
		this.SSCnickname=SSCnickname;
	}
}