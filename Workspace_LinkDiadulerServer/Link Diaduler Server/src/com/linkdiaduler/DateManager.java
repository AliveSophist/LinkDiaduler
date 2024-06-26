package com.linkdiaduler;

import java.util.Calendar;

public class DateManager{
	
	private static Calendar switchdate=Calendar.getInstance(); //항시 기준이되는 오늘날짜대입   ex) 1주전 = 오늘날짜 요일로 부터의 1주전.
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
			return (date.get(Calendar.MONTH)+1)+" ／ "+date.get(Calendar.DATE);
		else if(id==5)
			return (date.get(Calendar.YEAR)+String.format("%02d", (date.get(Calendar.MONTH)+1))+String.format("%02d", (date.get(Calendar.DATE))));
        else
        	return date.get(Calendar.YEAR)+"년 "+(date.get(Calendar.MONTH)+1+"월");
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
		return "그딴 요일은 없습니다";
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
	
	public static boolean limitChecker(){//날짜를 변경했는데 1989년 혹은 2090년에 닿을경우  true 반환
			return (switchdate.get(Calendar.YEAR)==limitEnd)||(switchdate.get(Calendar.YEAR)==limitStart)?true:false;
	}
	
	public static void warpDate(int year, int month, int date){
		switchdate.set(year, month-1, date);
	}
	
}