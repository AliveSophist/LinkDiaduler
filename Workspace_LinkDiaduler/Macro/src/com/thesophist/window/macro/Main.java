package com.thesophist.window.macro;

import java.io.IOException;

import java.awt.EventQueue;

public class Main {

	static Frame frame;
	
	public static void main(String[] args) {
			
		try {
			FileInOutput.fileInput();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void frameRefresh(){
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
}
