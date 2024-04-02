package com.linkdiaduler;

import java.util.Calendar;

public class DateManager{
	
	private static Calendar switchdate=Calendar.getInstance(); //�׽� �����̵Ǵ� ���ó�¥����   ex) 1���� = ���ó�¥ ���Ϸ� ������ 1����.
	public static int limitStart=1989;
	public static int limitEnd=2090;
	
	public static Calendar getOriginal(){
		return switchdate;
	}

	public static Calendar getSat(){
			int searchSat=switchdate.get(Calendar.DAY_OF_WEEK);
			Calendar satdate=switchdate;
			switch(searchSat){
			case 1:
				satdate.add(Calendar.DATE,6);
				break;
			case 2:
				satdate.add(Calendar.DATE,5);
				break;
			case 3:
				satdate.add(Calendar.DATE,4);
				break;
			case 4:
				satdate.add(Calendar.DATE,3);
				break;
			case 5:
				satdate.add(Calendar.DATE,2);
				break;
			case 6:
				satdate.add(Calendar.DATE,1);
				break;
			case 7:
				break;
			}
		return satdate;
	}
	

	public static String toString(Calendar date,int id) {
		if(id==0)
        	return date.get(Calendar.DATE)+"";
		else if(id==1)
			return date.get(Calendar.MONTH)+"";
		else if(id==2)
			return (date.get(Calendar.MONTH)+1)+" �� "+date.get(Calendar.DATE);
		else if(id==5)
			return (date.get(Calendar.YEAR)+String.format("%02d", (date.get(Calendar.MONTH)+1))+String.format("%02d", (date.get(Calendar.DATE))));
        else
        	return date.get(Calendar.YEAR)+"�� "+(date.get(Calendar.MONTH)+1+"��");
	}
	
	public static String toString(String date,int id){
		switch(date){
		case "sunCal":
			return toString(DateManager.addDate(DateManager.getSat(),-6),id);
		case "monCal":
			return toString(DateManager.addDate(DateManager.getSat(),-5),id);
		case "tueCal":
			return toString(DateManager.addDate(DateManager.getSat(),-4),id);
		case "wedCal":
			return toString(DateManager.addDate(DateManager.getSat(),-3),id);
		case "thuCal":
			return toString(DateManager.addDate(DateManager.getSat(),-2),id);
		case "friCal":
			return toString(DateManager.addDate(DateManager.getSat(),-1),id);
		case "satCal":
			return toString(DateManager.getSat(),id);
		}
		return "�׵� ������ �����ϴ�";
	}
	
	
	
	
	
	public static Calendar addDate(Calendar date,int gap){
		date.add(Calendar.DATE, gap);
		return date;
	}
	
	public static void addWeek(int gap){
		switch(gap){
		case -1:
			switchdate.add(Calendar.DATE, -7);
			if(limitChecker())
				switchdate.add(Calendar.DATE, 7);
			break;
		case 1:
			switchdate.add(Calendar.DATE, 7);
			if(limitChecker())
				switchdate.add(Calendar.DATE, -7);
			break;
		}	
	}
	
	public static void addMonth(int gap){
		switch(gap){
		case -1:
			switchdate.add(Calendar.MONTH, -1);
			if(limitChecker())
				switchdate.add(Calendar.MONTH, 1);
			break;
		case 1:
			switchdate.add(Calendar.MONTH, 1);
			if(limitChecker())
				switchdate.add(Calendar.MONTH, -1);
			break;
		}
	}
	
	public static void addYear(int gap){
		switch(gap){
		case -1:
			switchdate.add(Calendar.YEAR, -1);
			if(limitChecker())
				switchdate.add(Calendar.YEAR, 1);
			break;
		case 1:
			switchdate.add(Calendar.YEAR, 1);
			if(limitChecker())
				switchdate.add(Calendar.YEAR, -1);
			break;
		}
	}
	
	public static boolean limitChecker(){//��¥�� �����ߴµ� 1989�� Ȥ�� 2090�⿡ �������  true ��ȯ
			return (switchdate.get(Calendar.YEAR)==limitEnd)||(switchdate.get(Calendar.YEAR)==limitStart)?true:false;
	}
	
	public static void warpDate(int year, int month, int date){
		switchdate.set(year, month-1, date);
	}
	
}