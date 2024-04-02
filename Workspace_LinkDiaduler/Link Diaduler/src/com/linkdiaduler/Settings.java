package com.linkdiaduler;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Settings implements ChangeListener{
	
	public static String savedID, savedPW;
	public static int LDWidth, LDHeight;
	public static int[] backgroundRGB=new int[3];
	public static int[] buttonRGB=new int[3];
	public static int[] fontRGB=new int[3];
	public static Color background;
	public static Color button;
	public static Color font;
	int r,g,b;
	
	public JFrame frameSettings;

	private JPanel pnlSettings;
	private JLabel lblDefaultWidth, lblDefaultHeight, lblBackgroundcolor, lblButtoncolor, lblFontcolor;
	private JTextField txtLDWidth, txtLDHeight;
	private JButton btnResetBackgroundColor, btnResetButtonColor, btnResetFontColor, btnApply;
	
	private JPanel pnlRGBSettings, pnlRGBControler, pnlRed, pnlBlue, pnlGreen;
	private JSlider sdRed,sdGreen,sdBlue;
	private JLabel lblRed,lblGreen,lblBlue;
	private JButton btnGetRGB;
	private JPanel pnlRGBShower;

	public Settings() {
		initialize();
	}

	
	private void initialize() {
		frameSettings = new JFrame();
		frameSettings.setTitle("Settings");
		frameSettings.setResizable(false);
		frameSettings.setType(Type.UTILITY);
		frameSettings.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-150, Toolkit.getDefaultToolkit().getScreenSize().height/2-200, 300, 400);
		frameSettings.getContentPane().setLayout(null);
		
		pnlSettings = new JPanel();
		pnlSettings.setBounds(0, 0, 294, 376);
		frameSettings.getContentPane().add(pnlSettings);
		pnlSettings.setLayout(null);
		
		btnApply = new JButton("\uC801\uC6A9");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] backgroundRGB=new int[]{background.getRed(), background.getGreen(), background.getBlue()};
				int[] buttonRGB=new int[]{button.getRed(), button.getGreen(), button.getBlue()};
				int[] fontRGB=new int[]{font.getRed(), font.getGreen(), font.getBlue()};
				
				if(StringToNumCheck(txtLDWidth.getText()) && StringToNumCheck(txtLDHeight.getText())){
					LDWidth=Integer.parseInt(txtLDWidth.getText());
					LDHeight=Integer.parseInt(txtLDHeight.getText());
					DataManager.saveSettings(LDWidth, LDHeight, backgroundRGB, buttonRGB, fontRGB);
				}
				else
					DataManager.saveSettings(430, 870, backgroundRGB, buttonRGB, fontRGB);
				frameSettings.dispose();
				Main.linkdiaduler.frameLinkDiaduler.dispose();
				Main.openframeHome();
			}
		});
		btnApply.setBounds(206, 330, 76, 36);
		pnlSettings.add(btnApply);
		
		lblDefaultWidth = new JLabel("Default Width");
		lblDefaultWidth.setBounds(60, 24, 92, 15);
		pnlSettings.add(lblDefaultWidth);
		
		lblDefaultHeight = new JLabel("Default Height");
		lblDefaultHeight.setBounds(60, 52, 92, 15);
		pnlSettings.add(lblDefaultHeight);
		
		txtLDWidth = new JTextField();
		txtLDWidth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtLDWidth.setEditable(true);
				txtLDWidth.setText("");
			}
		});
		txtLDWidth.setEditable(false);
		txtLDWidth.setHorizontalAlignment(SwingConstants.CENTER);
		txtLDWidth.setText("max 1920");
		txtLDWidth.setBounds(152, 21, 69, 21);
		pnlSettings.add(txtLDWidth);
		txtLDWidth.setColumns(10);
		
		txtLDHeight = new JTextField();
		txtLDHeight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtLDHeight.setEditable(true);
				txtLDHeight.setText("");
			}
		});
		txtLDHeight.setEditable(false);
		txtLDHeight.setHorizontalAlignment(SwingConstants.CENTER);
		txtLDHeight.setText("max 1080");
		txtLDHeight.setBounds(152, 49, 69, 21);
		pnlSettings.add(txtLDHeight);
		txtLDHeight.setColumns(10);
		
		lblBackgroundcolor = new JLabel("Background Color");
		lblBackgroundcolor.setBounds(60, 96, 120, 15);
		pnlSettings.add(lblBackgroundcolor);
		
		lblButtoncolor = new JLabel("Button Color");
		lblButtoncolor.setBounds(60, 137, 120, 15);
		pnlSettings.add(lblButtoncolor);
		
		lblFontcolor = new JLabel("Font Color");
		lblFontcolor.setBounds(60, 178, 120, 15);
		pnlSettings.add(lblFontcolor);
		
		btnResetBackgroundColor = new JButton();
		btnResetBackgroundColor.setBackground(background);
		btnResetBackgroundColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameSettings.setTitle("Set Background Color");
				
				pnlSettings.setVisible(false);
				pnlRGBSettings.setVisible(true);
			}
		});
		btnResetBackgroundColor.setBounds(179, 86, 42, 36);
		pnlSettings.add(btnResetBackgroundColor);
		
		btnResetButtonColor = new JButton();
		btnResetButtonColor.setBackground(button);
		btnResetButtonColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameSettings.setTitle("Set Button Color");
				
				pnlSettings.setVisible(false);
				pnlRGBSettings.setVisible(true);
			}
		});
		btnResetButtonColor.setBounds(179, 127, 42, 36);
		pnlSettings.add(btnResetButtonColor);
		
		btnResetFontColor = new JButton();
		btnResetFontColor.setBackground(font);
		btnResetFontColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameSettings.setTitle("Set Font Color");
				
				pnlSettings.setVisible(false);
				pnlRGBSettings.setVisible(true);
			}
		});
		btnResetFontColor.setBounds(179, 168, 42, 36);
		pnlSettings.add(btnResetFontColor);
		
		
		
		pnlRGBSettings = new JPanel();
		pnlRGBSettings.setBounds(0, 0, 294, 376);
		frameSettings.getContentPane().add(pnlRGBSettings);
		pnlRGBSettings.setVisible(false);
		
		pnlRed=new JPanel();
		pnlBlue=new JPanel();
		pnlGreen=new JPanel();
		pnlRGBControler=new JPanel(new GridLayout(3,1));

		pnlRGBSettings.setLayout(new GridLayout(2,1));

		sdRed=new JSlider(JSlider.HORIZONTAL,0,255,0);
		lblRed=new JLabel("0",JLabel.CENTER);
		pnlRed.add(new JLabel("Red"));
		pnlRed.add(sdRed);
		pnlRed.add(lblRed);
		pnlRGBControler.add(pnlRed);

		sdGreen=new JSlider(JSlider.HORIZONTAL,0,255,0);
		lblGreen=new JLabel("0",JLabel.CENTER);
		pnlBlue.add(new JLabel("Green"));
		pnlBlue.add(sdGreen);
		pnlBlue.add(lblGreen);
		pnlRGBControler.add(pnlBlue);

		sdBlue=new JSlider(JSlider.HORIZONTAL,0,255,0);
		lblBlue=new JLabel("0",JLabel.CENTER);
		pnlGreen.add(new JLabel("Blue"));
		pnlGreen.add(sdBlue);
		pnlGreen.add(lblBlue);
		pnlRGBControler.add(pnlGreen);
		
		pnlRGBSettings.add("North",pnlRGBControler);
		
		pnlRGBShower=new JPanel();
		pnlRGBShower.setBackground(Color.BLACK);
		pnlRGBSettings.add(pnlRGBShower);
		pnlRGBShower.setLayout(null);
		
		btnGetRGB = new JButton("Get Color");
		btnGetRGB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlRGBSettings.setVisible(false);
				pnlSettings.setVisible(true);
				
				if(frameSettings.getTitle().equals("Set Background Color")){
					background = new Color(r, g, b);
					btnResetBackgroundColor.setBackground(background);
				}
				if(frameSettings.getTitle().equals("Set Button Color")){
					button = new Color(r, g, b);
					btnResetButtonColor.setBackground(button);
				}
				if(frameSettings.getTitle().equals("Set Font Color")){
					font = new Color(r, g, b);
					btnResetFontColor.setBackground(font);
				}
				
				frameSettings.setTitle("Settings");
			}
		});
		btnGetRGB.setBackground(Color.WHITE);
		btnGetRGB.setBounds(12, 143, 107, 35);
		pnlRGBShower.add(btnGetRGB);

		sdRed.addChangeListener(this);
		sdGreen.addChangeListener(this);
		sdBlue.addChangeListener(this);
	}
	public void stateChanged(ChangeEvent e)
	{
		Object ob=e.getSource();

		if(ob==sdRed)
			r=sdRed.getValue();
		else if(ob==sdGreen)
			g=sdGreen.getValue();
		else if(ob==sdBlue)
			b=sdBlue.getValue();

		lblRed.setText(String.valueOf(r));
		lblBlue.setText(String.valueOf(b));
		lblGreen.setText(String.valueOf(g));

		pnlRGBShower.setBackground(new Color(r,g,b));
	}
	private boolean StringToNumCheck(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
