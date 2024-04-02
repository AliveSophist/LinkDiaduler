package com.linkdiaduler.temp;

import java.util.Calendar;

public class DateManager{
	
	private static Calendar switchdate=Calendar.getInstance(); //항시 기준이되는 오늘날짜대입   ex) 1주전 = 오늘날짜 요일로 부터의 1주전.
	public static int limitStart=1989;
	public static int limitEnd=2089;
	
	private static int[] storedate=new int[3];//Year, Month-1, Day
	
	public static Calendar getOriginal(){
		return switchdate;
	}

	public static Calendar getSun(){
		int searchSun=switchdate.get(Calendar.DAY_OF_WEEK);
		Calendar sundate=Calendar.getInstance();
		sundate.set(switchdate.get(Calendar.YEAR), switchdate.get(Calendar.MONTH), switchdate.get(Calendar.DATE));
		switch(searchSun){
		case 1:
			break;
		case 2:
			sundate.add(Calendar.DATE,-1);
			break;
		case 3:
			sundate.add(Calendar.DATE,-2);
			break;
		case 4:
			sundate.add(Calendar.DATE,-3);
			break;
		case 5:
			sundate.add(Calendar.DATE,-4);
			break;
		case 6:
			sundate.add(Calendar.DATE,-5);
			break;
		case 7:
			sundate.add(Calendar.DATE,-6);
			break;
		}
	return sundate;
}
	
	public static Calendar getSat(){
			int searchSat=switchdate.get(Calendar.DAY_OF_WEEK);
			Calendar satdate=Calendar.getInstance();
			satdate.set(switchdate.get(Calendar.YEAR), switchdate.get(Calendar.MONTH), switchdate.get(Calendar.DATE));
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
		switch(id){
		case 0:;
        	return Integer.toString(date.get(Calendar.DATE));
		case 1:;
			return Integer.toString(date.get(Calendar.MONTH)+1);
		case 2:;
		return (date.get(Calendar.MONTH)+1)+"／"+date.get(Calendar.DATE);
		case 3:;
        	return getSat().get(Calendar.YEAR)+"년 "+(getSat().get(Calendar.MONTH)+1+"월");
		case 4:;
			return Integer.toString(date.get(Calendar.YEAR));
		case 5:;
			return date.get(Calendar.YEAR)+"."+(date.get(Calendar.MONTH)+1)+"."+date.get(Calendar.DATE);
		case 6:;//서버통신용
		return (date.get(Calendar.YEAR)+String.format("%02d", (date.get(Calendar.MONTH)+1))+String.format("%02d", (date.get(Calendar.DATE))));
		}
		return null;
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
		case "nextSunCal":
			return toString(DateManager.addDate(DateManager.getSat(),1),id);
		}
		return "그딴 요일은 없습니다";
	}
	
	public static Calendar dayofweekCalendar(String date){
		switch(date){
		case "sunCal":
			return DateManager.addDate(DateManager.getSat(),-6);
		case "monCal":
			return DateManager.addDate(DateManager.getSat(),-5);
		case "tueCal":
			return DateManager.addDate(DateManager.getSat(),-4);
		case "wedCal":
			return DateManager.addDate(DateManager.getSat(),-3);
		case "thuCal":
			return DateManager.addDate(DateManager.getSat(),-2);
		case "friCal":
			return DateManager.addDate(DateManager.getSat(),-1);
		case "satCal":
			return DateManager.getSat();
		case "nextSunCal":
			return DateManager.addDate(DateManager.getSat(),1);
		}
		//return "그딴 요일은 없습니다";
		return null;
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
	
	public static int getDifferenceDate(int[] beginDate, int[] endDate){
		Calendar beginCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		beginCalendar.set(beginDate[0], beginDate[1], beginDate[2]);
		endCalendar.set(endDate[0], endDate[1], endDate[2]);
		
		long diffDay=(endCalendar.getTimeInMillis()-beginCalendar.getTimeInMillis())/1000/(60*60*24);
		
		return (int)diffDay;
	}
	
	public static void saveTemporaryDate(){
		storedate[0]=switchdate.get(Calendar.YEAR);
		storedate[1]=switchdate.get(Calendar.MONTH);
		storedate[2]=switchdate.get(Calendar.DATE);
	}
	
	public static void loadTemporaryDate(){
		switchdate.set(storedate[0], storedate[1], storedate[2]);
	}
}