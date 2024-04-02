package com.thesophist.window.macro;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.StringTokenizer;

public class ControlRobot {
	private static Robot robot;

	public static String[] MOUSE_BUTTON_SPECIES={"Left","Middle","Right"};
	public static String[] MOUSE_EVENTS_SPECIES={"press&release","press","release"};//move朝 評煎 掘⑷脾
	public static String[] KEY_BUTTON_SPECIES={"backspace","tab","enter","shift","ctrl","alt","pause/break","caps.lock","escape","page.up","page.down","end","home","left.arrow","up.arrow","right.arrow","down.arrow","insert","delete","0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","left.window.key","right.window.key","select.key","numpad.0","numpad.1","numpad.2","numpad.3","numpad.4","numpad.5","numpad.6","numpad.7","numpad.8","numpad.9","multiply","add","subtract","decimal.point","divide","f1","f2","f3","f4","f5","f6","f7","f8","f9","f10","f11","f12","num.lock","scroll.lock","semi-colon","equal.sign","comma","dash","period","forward.slash","grave.accent","open.bracket","back.slash","close.braket","single.quote"};
	public static String[] KEY_EVENTS_SPECIES={"press&release","press","release"};	
	
	public static String packKeyEvent(String event, String key){
		String rt="Did you hacked? 天天;";
		switch(event){
		case "press":	//Key press
			rt="press "+key;
			break;
		case "release":	//Key Release
			rt="release "+key;
			break;
		}
		return rt;
	}
	public static String packMouseEvent(String event, String mouseVariable){
		String rt="Did you hacked? 天天;";
		switch(event){
		case "move":	//Cursor Move
			rt="mouse_move "+mouseVariable;
			break;
		case "press":	//Mouse press
			rt="mouse_press "+mouseVariable;
			break;
		case "release":	//Mouse Release
			rt="mouse_release "+mouseVariable;
			break;
		}
		return rt;
	}
	public static String packDelay(int msecond){
		return "delay "+msecond;
	}	
	public static void unpackEvent(String macro){
		try {
			robot=new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		StringTokenizer st;

		st = new StringTokenizer(macro);

		switch(st.nextToken()){
		case "press":
			robot.keyPress(toKeyCode(st.nextToken()));
			break;
		case "release":
			robot.keyRelease(toKeyCode(st.nextToken()));
			break;
		case "mouse_move":
			int a=Integer.parseInt(st.nextToken());
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			robot.mouseMove(a/10000,a%10000);
			break;
		case "mouse_press":
			robot.mousePress(toMouseButtonCode(st.nextToken()));
			break;
		case "mouse_release":
			robot.mouseRelease(toMouseButtonCode(st.nextToken()));
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			break;
		case "delay":
			robot.delay(Integer.parseInt(st.nextToken()));
		}
	}

	public static int toMouseButtonCode(String button){
		switch(button){
		case "Right":
			return 4;
		case "Middle":
			return 8;
		case "Left":
			return 16;
		}
		return 0;
	}
	public static int toKeyCode(String button){
		switch(button){
		case "backspace":
			return 8;
		case "tab":
			return 9;
		case "enter":
			return 13;
		case "shift":
			return 16;
		case "ctrl":
			return 17;
		case "alt":
			return 18;
		case "pause/break":
			return 19;
		case "caps lock":
			return 20;
		case "escape":
			return 27;
		case "page up":
			return 33;
		case "page down":
			return 34;
		case "end":
			return 35;
		case "home":
			return 36;
		case "left arrow":
			return 37;
		case "up arrow":
			return 38;
		case "right arrow":
			return 39;
		case "down arrow":
			return 40;
		case "insert":
			return 45;
		case "delete":
			return 46;
		case "0":
			return 48;
		case "1":
			return 49;
		case "2":
			return 50;
		case "3":
			return 51;
		case "4":
			return 52;
		case "5":
			return 53;
		case "6":
			return 54;
		case "7":
			return 55;
		case "8":
			return 56;
		case "9":
			return 57;
		case "a":
			return 65;
		case "b":
			return 66;
		case "c":
			return 67;
		case "d":
			return 68;
		case "e":
			return 69;
		case "f":
			return 70;
		case "g":
			return 71;
		case "h":
			return 72;
		case "i":
			return 73;
		case "j":
			return 74;
		case "k":
			return 75;
		case "l":
			return 76;
		case "m":
			return 77;
		case "n":
			return 78;
		case "o":
			return 79;
		case "p":
			return 80;
		case "q":
			return 81;
		case "r":
			return 82;
		case "s":
			return 83;
		case "t":
			return 84;
		case "u":
			return 85;
		case "v":
			return 86;
		case "w":
			return 87;
		case "x":
			return 88;
		case "y":
			return 89;
		case "z":
			return 90;
		case "left window key":
			return 91;
		case "right window key":
			return 92;
		case "select key":
			return 93;
		case "numpad 0":
			return 96;
		case "numpad 1":
			return 97;
		case "numpad 2":
			return 98;
		case "numpad 3":
			return 99;
		case "numpad 4":
			return 100;
		case "numpad 5":
			return 101;
		case "numpad 6":
			return 102;
		case "numpad 7":
			return 103;
		case "numpad 8":
			return 104;
		case "numpad 9":
			return 105;
		case "multiply":
			return 106;
		case "add":
			return 107;
		case "subtract":
			return 109;
		case "decimal point":
			return 110;
		case "divide":
			return 111;
		case "f1":
			return 112;
		case "f2":
			return 113;
		case "f3":
			return 114;
		case "f4":
			return 115;
		case "f5":
			return 116;
		case "f6":
			return 117;
		case "f7":
			return 118;
		case "f8":
			return 119;
		case "f9":
			return 120;
		case "f10":
			return 121;
		case "f11":
			return 122;
		case "f12":
			return 123;
		case "num lock":
			return 144;
		case "scroll lock":
			return 145;
		case "semi-colon":
			return 186;
		case "equal sign":
			return 187;
		case "comma":
			return 188;
		case "dash":
			return 189;
		case "period":
			return 190;
		case "forward slash":
			return 191;
		case "grave accent":
			return 192;
		case "open bracket":
			return 219;
		case "back slash":
			return 220;
		case "close braket":
			return 221;
		case "single quote":
			return 222;
		}
		return 0;
	}
	
	public static String toMouseButtonString(int button){
		switch(button){
		case 8:
			return "Middle";
		case 4:
			return "Right";
		}
		return null;
	}
	public static String toKeyString(int button){
		switch(button){
		case 8:
			return "backspace";
		case 9:
			return "tab";
		case 13:
			return "enter";
//		case 16:
//			return "shift";
//		case 17:
//			return "ctrl";
//		case 18:
//			return "alt";
		case 19:
			return "pause/break";
		case 20:
			return "caps lock";
		case 27:
			return "escape";
		case 33:
			return "page_up";
		case 34:
			return "page_down";
		case 35:
			return "end";
		case 36:
			return "home";
		case 37:
			return "left_arrow";
		case 38:
			return "up_arrow";
		case 39:
			return "right_arrow";
		case 40:
			return "down_arrow";
		case 45:
			return "insert";
		case 46:
			return "delete";
		case 48:
			return "0";
		case 49:
			return "1";
		case 50:
			return "2";
		case 51:
			return "3";
		case 52:
			return "4";
		case 53:
			return "5";
		case 54:
			return "6";
		case 55:
			return "7";
		case 56:
			return "8";
		case 57:
			return "9";
		case 65:
			return "a";
		case 66:
			return "b";
		case 67:
			return "c";
		case 68:
			return "d";
		case 69:
			return "e";
		case 70:
			return "f";
		case 71:
			return "g";
		case 72:
			return "h";
		case 73:
			return "i";
		case 74:
			return "j";
		case 75:
			return "k";
		case 76:
			return "l";
		case 77:
			return "m";
		case 78:
			return "n";
		case 79:
			return "o";
		case 80:
			return "p";
		case 81:
			return "q";
		case 82:
			return "r";
		case 83:
			return "s";
		case 84:
			return "t";
		case 85:
			return "u";
		case 86:
			return "v";
		case 87:
			return "w";
		case 88:
			return "x";
		case 89:
			return "y";
		case 90:
			return "z";
		case 91:
			return "left_window_key";
		case 92:
			return "right_window_key";
		case 93:
			return "select_key";
		case 96:
			return "numpad_0";
		case 97:
			return "numpad_1";
		case 98:
			return "numpad_2";
		case 99:
			return "numpad_3";
		case 100:
			return "numpad_4";
		case 101:
			return "numpad_5";
		case 102:
			return "numpad_6";
		case 103:
			return "numpad_7";
		case 104:
			return "numpad_8";
		case 105:
			return "numpad_9";
		case 106:
			return "multiply";
		case 107:
			return "add";
		case 109:
			return "subtract";
		case 110:
			return "decimal_point";
		case 111:
			return "divide";
		case 112:
			return "f1";
		case 113:
			return "f2";
		case 114:
			return "f3";
		case 115:
			return "f4";
		case 116:
			return "f5";
		case 117:
			return "f6";
		case 118:
			return "f7";
		case 119:
			return "f8";
		case 120:
			return "f9";
		case 121:
			return "f10";
		case 122:
			return "f11";
		case 123:
			return "f12";
		case 144:
			return "num_lock";
		case 145:
			return "scroll_lock";
		case 186:
			return "semi-colon";
		case 187:
			return "equal_sign";
		case 188:
			return "comma";
		case 189:
			return "dash";
		case 190:
			return "period";
		case 191:
			return "forward_slash";
		case 192:
			return "grave_accent";
		case 219:
			return "open_bracket";
		case 220:
			return "back_slash";
		case 221:
			return "close_braket";
		case 222:
			return "single_quote";
		}
		return null;
	}
}