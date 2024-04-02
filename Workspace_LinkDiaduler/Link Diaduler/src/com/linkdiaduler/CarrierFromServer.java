package com.linkdiaduler;

import java.io.Serializable;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class CarrierFromServer implements Serializable{
	
	String happening;
	//서버는 송신 클라는 수신
	String serialCode;
	String nickname;
	String SSCList;
	int registernum;
	String weekText;
	String weekImageContents;
	ImageIcon[][] dayofweekimage;
	ImageIcon[] image;
	int[] imagenum;

	public CarrierFromServer(String happening, String serialcode, String nickname, String SSClist, int registernum)//serialCode & SSClist can null
	{//register, login
		this.happening=happening;
		this.serialCode=serialcode;
		this.nickname=nickname;
		this.SSCList=SSClist;
		this.registernum=registernum;
	}
	public CarrierFromServer(String happening, String weektext, String weekImageContents, ImageIcon[][] imageicon, int[] imagenum)
	{//download week contents
		this.happening=happening;
		this.weekText=weektext;
		this.weekImageContents=weekImageContents;
		this.imagenum=imagenum;
		this.dayofweekimage=imageicon;
	}
	public CarrierFromServer(String happening)
	{//upload image, add del subscribe, error return
		this.happening=happening;
	}
	public CarrierFromServer(String happening, String SSCserialCode)
	{//show SSC
		this.happening=happening;
		this.serialCode=SSCserialCode;
	}
}