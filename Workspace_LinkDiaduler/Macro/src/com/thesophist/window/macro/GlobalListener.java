package com.thesophist.window.macro;

import java.awt.MouseInfo;
import java.awt.Point;

import de.ksquared.system.keyboard.GlobalKeyListener;
import de.ksquared.system.keyboard.KeyAdapter;
import de.ksquared.system.keyboard.KeyEvent;
import de.ksquared.system.mouse.GlobalMouseListener;
import de.ksquared.system.mouse.MouseAdapter;
import de.ksquared.system.mouse.MouseEvent;

public class GlobalListener {
	private static boolean recordOnOff=false;
	
	
	public static void recordOn(){
		recordOnOff=true;
	}
	public static void recordOff(){
		recordOnOff=false;
	}
	public GlobalListener(){
		new GlobalKeyListener().addKeyListener(new KeyAdapter() {
			@Override public void keyPressed(KeyEvent event) {
				int libKeyCode=event.getVirtualKeyCode();
				String key=libKeyCodeException(libKeyCode);
			
				if(key=="f11"){//F11 = Start
					recordOnOff=true;
					return ;
				}
				if(key=="f12")//F12 = End
					recordOnOff=false;
				
				if(!recordOnOff)
					return ;
				
				JPanelHome.list.addElement(ControlRobot.packKeyEvent("press", key));
			}
			@Override public void keyReleased(KeyEvent event) {
				int libKeyCode=event.getVirtualKeyCode();
				String key=libKeyCodeException(libKeyCode);
			
				if(key=="f11")//F11 = Start, Not include f11 release
					return ;
				
				if(!recordOnOff)
					return ;
				
				JPanelHome.list.addElement(ControlRobot.packKeyEvent("release", key));
			}
		});
		
		new GlobalMouseListener().addMouseListener(new MouseAdapter() {
			@Override public void mousePressed(MouseEvent event)  {
				int libMouseCode=event.getButton();
				String mousebutton=libMouseCodeException(libMouseCode);
				
				if(!recordOnOff)
					return ;
				
				Point p = MouseInfo.getPointerInfo().getLocation();
				JPanelHome.list.addElement(ControlRobot.packMouseEvent("move",p.x*10000+p.y+""));
				JPanelHome.list.addElement(ControlRobot.packMouseEvent("press",mousebutton));
			}
			@Override public void mouseReleased(MouseEvent event)  {
				int libMouseCode=event.getButton();
				String mousebutton=libMouseCodeException(libMouseCode);
				
				if(!recordOnOff)
					return ;
				
				Point p = MouseInfo.getPointerInfo().getLocation();
				JPanelHome.list.addElement(ControlRobot.packMouseEvent("move",p.x*10000+p.y+""));
				JPanelHome.list.addElement(ControlRobot.packMouseEvent("release",mousebutton));
			}
//			@Override public void mouseMoved(MouseEvent event) {
//				Point p = MouseInfo.getPointerInfo().getLocation();
//				JPanelHome.list.addElement(ControlRobot.packMouseEvent("move",p.x*10000+p.y+""));
//			}
		});
	}
	
	private String libKeyCodeException(int keyCode){
		switch(keyCode){
		case 160:
			return "shift";
		case 162:
			return "ctrl";
		case 164:
			return "alt";
		}
		return ControlRobot.toKeyString(keyCode);
	}
	private String libMouseCodeException(int mouseCode){
		switch(mouseCode){
		case 2:
			return "Left";
		}
		return ControlRobot.toMouseButtonString(mouseCode);
	}
}