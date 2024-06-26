package com.linkdiaduler;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.Color;

public class Macro {

	public static JFrame frameMacro;
	
	private JToggleButton tglbtnWeekMacro, tglbtnMonthMacro, tglbtnYearMacro;
	private JLabel lblStartDate, lblEndDate;
	private JCheckBox cbRepeateNum, cbDirectDate;
	private JComboBox<Integer> choiceRepeateNum, choiceSDYear, choiceSDMonth, choiceSDDate, choiceEDYear, choiceEDMonth, choiceEDDate;

	private JEditorPane textArea;
	private JTextField tfLinkingText, tfLink;
	private JButton btnAddText, btnAddImage, btnAddTextMacro, btnAddLinkedText;
	private JPanel pnlMacroSetting;
	private JPanel pnlMacroText;
	
	private int[] startdate=new int[3];
	private int[] enddate=new int[3];
	private static int repeatnum;
	private static int repeatGap=0;
	
	public static boolean nowImgMacro=false;
	
	public Macro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameMacro = new JFrame();
		frameMacro.setResizable(false);
		frameMacro.setAlwaysOnTop(true);
		frameMacro.setType(Type.UTILITY);
		frameMacro.setTitle("Macro");
		frameMacro.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-170, Toolkit.getDefaultToolkit().getScreenSize().height/2-145, 340, 290);
		frameMacro.getContentPane().setLayout(null);
		
		pnlMacroSetting = new JPanel();
		pnlMacroSetting.setBackground(Settings.background);
		pnlMacroSetting.setBounds(0, 0, 332, 264);
		frameMacro.getContentPane().add(pnlMacroSetting);
		pnlMacroSetting.setLayout(null);
		
			tglbtnWeekMacro = new JToggleButton("주간");
			tglbtnWeekMacro.setForeground(Settings.font);
			tglbtnWeekMacro.setBackground(Settings.button);
			tglbtnWeekMacro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tglbtnMonthMacro.setSelected(false);
					tglbtnYearMacro.setSelected(false);
					repeatGap=0;
					
					if(cbDirectDate.isSelected()){
						choiceEDYear.setEnabled(true);
						choiceEDMonth.setEnabled(true);
						choiceEDDate.setEnabled(true);
					}
					else{
						choiceEDYear.setEnabled(false);
						choiceEDMonth.setEnabled(false);
						choiceEDDate.setEnabled(false);
					}
				}
			});
			tglbtnWeekMacro.setBounds(35, 12, 80, 23);
			pnlMacroSetting.add(tglbtnWeekMacro);
			
			tglbtnMonthMacro = new JToggleButton("월간");
			tglbtnMonthMacro.setForeground(Settings.font);
			tglbtnMonthMacro.setBackground(Settings.button);
			tglbtnMonthMacro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tglbtnWeekMacro.setSelected(false);
					tglbtnYearMacro.setSelected(false);
					repeatGap=1;
					
					if(cbDirectDate.isSelected()){
						choiceEDYear.setEnabled(true);
						choiceEDMonth.setEnabled(true);
						choiceEDDate.setEnabled(false);
					}
					else{
						choiceEDYear.setEnabled(false);
						choiceEDMonth.setEnabled(false);
						choiceEDDate.setEnabled(false);
					}
				}
			});
			tglbtnMonthMacro.setBounds(121, 12, 80, 23);
			pnlMacroSetting.add(tglbtnMonthMacro);
			
			tglbtnYearMacro = new JToggleButton("년간");
			tglbtnYearMacro.setForeground(Settings.font);
			tglbtnYearMacro.setBackground(Settings.button);
			tglbtnYearMacro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tglbtnWeekMacro.setSelected(false);
					tglbtnMonthMacro.setSelected(false);
					repeatGap=2;
					
					if(cbDirectDate.isSelected()){
						choiceEDYear.setEnabled(true);
						choiceEDMonth.setEnabled(false);
						choiceEDDate.setEnabled(false);
					}
					else{
						choiceEDYear.setEnabled(false);
						choiceEDMonth.setEnabled(false);
						choiceEDDate.setEnabled(false);
					}
				}
			});
			tglbtnYearMacro.setBounds(207, 12, 80, 23);
			pnlMacroSetting.add(tglbtnYearMacro);
			
			lblStartDate = new JLabel("Start Date");
			lblStartDate.setForeground(Settings.font);
			lblStartDate.setBounds(12, 60, 80, 15);
			pnlMacroSetting.add(lblStartDate);
			
			choiceSDYear = new JComboBox<Integer>();
			choiceSDYear.setForeground(Settings.font);
			choiceSDYear.setBackground(Settings.button);
			for(int year=1990; year<=DateManager.limitEnd; year++)
				choiceSDYear.addItem(year);
			choiceSDYear.setBounds(12, 81, 55, 21);
			pnlMacroSetting.add(choiceSDYear);
			choiceSDYear.setSelectedItem(Integer.parseInt(DateManager.toString(DateManager.getOriginal(), 4)));
			
			choiceSDMonth = new JComboBox<Integer>();
			choiceSDMonth.setForeground(Settings.font);
			choiceSDMonth.setBackground(Settings.button);
			for(int month=1; month<=12; month++)
				choiceSDMonth.addItem(month);
			choiceSDMonth.setBounds(73, 81, 40, 21);
			pnlMacroSetting.add(choiceSDMonth);
			choiceSDMonth.setSelectedItem(Integer.parseInt(DateManager.toString(DateManager.getOriginal(), 1)));
			
			choiceSDDate = new JComboBox<Integer>();
			choiceSDDate.setForeground(Settings.font);
			choiceSDDate.setBackground(Settings.button);
			for(int date=1; date<=31; date++)
				choiceSDDate.addItem(date);
			choiceSDDate.setBounds(119, 81, 40, 21);
			pnlMacroSetting.add(choiceSDDate);
			choiceSDDate.setSelectedItem(Integer.parseInt(DateManager.toString(DateManager.getOriginal(), 0)));
			
			lblEndDate = new JLabel("Limit");
			lblEndDate.setBounds(12, 111, 80, 15);
			pnlMacroSetting.add(lblEndDate);
			
			cbRepeateNum = new JCheckBox("적용 횟수 입력");
			cbRepeateNum.setForeground(Settings.font);
			cbRepeateNum.setToolTipText("\uC2DC\uC791 \uB0A0\uC9DC \uD3EC\uD568 \uD69F\uC218 \uB9CC\uD07C \uBC18\uBCF5\uD569\uB2C8\uB2E4.");
			cbRepeateNum.setBackground(Settings.background);
			cbRepeateNum.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cbDirectDate.setSelected(false);
					choiceEDYear.setEnabled(false);
					choiceEDMonth.setEnabled(false);
					choiceEDDate.setEnabled(false);
					
					choiceRepeateNum.setEnabled(true);
				}
			});
			cbRepeateNum.setBounds(12, 132, 115, 23);
			pnlMacroSetting.add(cbRepeateNum);
			
			choiceRepeateNum = new JComboBox<Integer>();
			choiceRepeateNum.setForeground(Settings.font);
			choiceRepeateNum.setBackground(Settings.button);
			for(int repeatenum=1; repeatenum<=50; repeatenum++)
				choiceRepeateNum.addItem(repeatenum);
			choiceRepeateNum.setBounds(32, 161, 40, 21);
			pnlMacroSetting.add(choiceRepeateNum);
			
			cbDirectDate = new JCheckBox("End Date 입력");
			cbDirectDate.setForeground(Settings.font);
			cbDirectDate.setToolTipText("\uD574\uB2F9 \uB0A0\uC9DC\uAE4C\uC9C0 \uBC18\uBCF5\uD569\uB2C8\uB2E4.");
			cbDirectDate.setBackground(Settings.background);
			cbDirectDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cbRepeateNum.setSelected(false);
					choiceRepeateNum.setEnabled(false);
					
					if(tglbtnWeekMacro.isSelected()){
						choiceEDYear.setEnabled(true);
						choiceEDMonth.setEnabled(true);
						choiceEDDate.setEnabled(true);
					}
					else if(tglbtnMonthMacro.isSelected()){
						choiceEDYear.setEnabled(true);
						choiceEDMonth.setEnabled(true);
						choiceEDDate.setEnabled(false);
					}
					else{//tglbtnYearMacro.isSelected()
						choiceEDYear.setEnabled(true);
						choiceEDMonth.setEnabled(false);
						choiceEDDate.setEnabled(false);
					}
				}
			});
			cbDirectDate.setBounds(146, 132, 115, 23);
			pnlMacroSetting.add(cbDirectDate);
			
			choiceEDYear = new JComboBox<Integer>();
			choiceEDYear.setForeground(Settings.font);
			choiceEDYear.setBackground(Settings.button);
			for(int year=1990; year<=DateManager.limitEnd; year++)
				choiceEDYear.addItem(year);
			choiceEDYear.setBounds(168, 161, 55, 21);
			pnlMacroSetting.add(choiceEDYear);
			choiceEDYear.setSelectedItem(Integer.parseInt(DateManager.toString(DateManager.getOriginal(), 4)));
			
			choiceEDMonth = new JComboBox<Integer>();
			choiceEDMonth.setForeground(Settings.font);
			choiceEDMonth.setBackground(Settings.button);
			for(int month=1; month<=12; month++)
				choiceEDMonth.addItem(month);
			choiceEDMonth.setBounds(229, 161, 40, 21);
			pnlMacroSetting.add(choiceEDMonth);
			choiceEDMonth.setSelectedItem(Integer.parseInt(DateManager.toString(DateManager.getOriginal(), 1)));
			
			choiceEDDate = new JComboBox<Integer>();
			choiceEDDate.setForeground(Settings.font);
			choiceEDDate.setBackground(Settings.button);
			for(int date=1; date<=31; date++)
				choiceEDDate.addItem(date);
			choiceEDDate.setBounds(275, 161, 40, 21);
			pnlMacroSetting.add(choiceEDDate);
			choiceEDDate.setSelectedItem(Integer.parseInt(DateManager.toString(DateManager.getOriginal(), 0)));
			
			btnAddText = new JButton("텍스트 추가하기");
			btnAddText.setForeground(Settings.font);
			btnAddText.setBackground(Settings.button);
			btnAddText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if((int)choiceSDYear.getSelectedItem()>(int)choiceEDYear.getSelectedItem())
						return;
					DateManager.saveTemporaryDate();
					
					if((tglbtnWeekMacro.isSelected()|tglbtnMonthMacro.isSelected()|tglbtnYearMacro.isSelected())&&(cbRepeateNum.isSelected()|cbDirectDate.isSelected())){
						if(cbRepeateNum.isSelected()){
							saveSDEDRN();
							
							pnlMacroSetting.setVisible(false);
							pnlMacroText.setVisible(true);
						}
						else if(cbDirectDate.isSelected()){
							if(tglbtnWeekMacro.isSelected()){
								saveSDEDRN();
								repeatnum=DateManager.getDifferenceDate(startdate, enddate)/7+1;
								
								pnlMacroSetting.setVisible(false);
								pnlMacroText.setVisible(true);
							}
							else if(tglbtnMonthMacro.isSelected()){
								saveSDEDRN();
								enddate[2]=startdate[2];
								repeatnum=DateManager.getDifferenceDate(startdate, enddate)/30+1;
								
								pnlMacroSetting.setVisible(false);
								pnlMacroText.setVisible(true);
							}
							else if(tglbtnYearMacro.isSelected()){
								saveSDEDRN();
								enddate[1]=startdate[1];
								enddate[2]=startdate[2];
								repeatnum=DateManager.getDifferenceDate(startdate, enddate)/30/12+1;
								
								pnlMacroSetting.setVisible(false);
								pnlMacroText.setVisible(true);
							}
						}
					}
				}
			});
			btnAddText.setBounds(12, 210, 147, 36);
			pnlMacroSetting.add(btnAddText);
			
			btnAddImage = new JButton("이미지 추가하기");
			btnAddImage.setForeground(Settings.font);
			btnAddImage.setBackground(Settings.button);
			btnAddImage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if((int)choiceSDYear.getSelectedItem()>(int)choiceEDYear.getSelectedItem())
						return;
					DateManager.saveTemporaryDate();
					
					if((tglbtnWeekMacro.isSelected()|tglbtnMonthMacro.isSelected()|tglbtnYearMacro.isSelected())&&(cbRepeateNum.isSelected()|cbDirectDate.isSelected())){
						if(cbRepeateNum.isSelected()){
							frameMacro.setVisible(false);
							
							saveSDEDRN();
							DateManager.warpDate(startdate[0], startdate[1], startdate[2]);
							Main.openframeImageContainer(DateManager.getOriginal());
							
							nowImgMacro=true;
						}
						else if(cbDirectDate.isSelected()){
							if(tglbtnWeekMacro.isSelected()){
								frameMacro.setVisible(false);
								
								saveSDEDRN();
								repeatnum=DateManager.getDifferenceDate(startdate, enddate)/7+1;
								DateManager.warpDate(startdate[0], startdate[1], startdate[2]);
								Main.openframeImageContainer(DateManager.getOriginal());
								
								nowImgMacro=true;
							}
							else if(tglbtnMonthMacro.isSelected()){
								frameMacro.setVisible(false);
								
								saveSDEDRN();
								enddate[2]=startdate[2];
								repeatnum=DateManager.getDifferenceDate(startdate, enddate)/30+1;
								DateManager.warpDate(startdate[0], startdate[1], startdate[2]);
								Main.openframeImageContainer(DateManager.getOriginal());

								nowImgMacro=true;
							}
							else if(tglbtnYearMacro.isSelected()){
								frameMacro.setVisible(false);
								
								saveSDEDRN();
								enddate[1]=startdate[1];
								enddate[2]=startdate[2];
								repeatnum=DateManager.getDifferenceDate(startdate, enddate)/30/12+1;
								DateManager.warpDate(startdate[0], startdate[1], startdate[2]);
								Main.openframeImageContainer(DateManager.getOriginal());

								nowImgMacro=true;
							}
						}
					}
				}
			});
			btnAddImage.setBounds(168, 210, 147, 36);
			pnlMacroSetting.add(btnAddImage);
			
			choiceRepeateNum.setEnabled(false);
			choiceEDYear.setEnabled(false);
			choiceEDMonth.setEnabled(false);
			choiceEDDate.setEnabled(false);
		
		
		
		
		
		
		pnlMacroText = new JPanel();
		pnlMacroText.setBackground(Settings.background);
		pnlMacroText.setBounds(0, 0, 332, 264);
		frameMacro.getContentPane().add(pnlMacroText);
		pnlMacroText.setLayout(null);
		pnlMacroText.setVisible(false);
		
			textArea = new JEditorPane("text/html", null);
			textArea.setBounds(12, 10, 308, 182);
			pnlMacroText.add(textArea);
			final Document doc = textArea.getDocument();
			final EditorKit kit=textArea.getEditorKit();
			
			tfLinkingText = new JTextField("  text입력");
			tfLinkingText.setBackground(Settings.button);
			tfLinkingText.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					tfLinkingText.setText("");
					tfLinkingText.setEditable(true);
				}
			});
			tfLinkingText.setEditable(false);
			tfLinkingText.setBounds(12, 202, 65, 21);
			pnlMacroText.add(tfLinkingText);
			tfLinkingText.setColumns(10);
			
			tfLink = new JTextField("  link입력");
			tfLink.setBackground(Settings.button);
			tfLink.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					tfLink.setText("http://");
					tfLink.setEditable(true);
				}
			});
			tfLink.setEditable(false);
			tfLink.setBounds(84, 202, 65, 21);
			pnlMacroText.add(tfLink);
			tfLink.setColumns(10);
			
			btnAddLinkedText = new JButton("Add Link");
			btnAddLinkedText.setBackground(Settings.button);
			btnAddLinkedText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String href=new String(" <a href='"+tfLink.getText()+"'>"+tfLinkingText.getText()+"</a> ");
					try {
						((HTMLEditorKit)kit).insertHTML((HTMLDocument)doc, doc.getEndPosition().getOffset()-1, href, 1, 0, null);
					} catch (IOException | BadLocationException e) {
						// TODO 자동 생성된 catch 블록
						e.printStackTrace();
					}
				}
			});
			btnAddLinkedText.setBounds(12, 233, 137, 21);
			pnlMacroText.add(btnAddLinkedText);
			
			btnAddTextMacro = new JButton("OK");
			btnAddTextMacro.setBackground(Settings.button);
			btnAddTextMacro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String text=textArea.getText();
					int textLength = 0;
					try {
						textLength = textArea.getDocument().getText(0, textArea.getDocument().getLength()).length();
					} catch (BadLocationException e) {
						// TODO 자동 생성된 catch 블록
						e.printStackTrace();
					}
					if(textLength>60){
						JOptionPane.showMessageDialog(null, "60자를 초과하였습니다", "Error!", JOptionPane.ERROR_MESSAGE);
						return;//아래의 버튼 원상복귀를 스킵함
					}
					if((text.indexOf("<body>")+9) == (text.indexOf("</body>")))
						return;
					else{//내용이 있을경우
						int i=0;
						DateManager.warpDate(startdate[0], startdate[1], startdate[2]);

						if(DataManager.onlinemode){//온라인모드
							String fixindex = "";
							while(i<repeatnum){
								fixindex+=DataManager.fixDecodedText(text);
								if(repeatGap==0)
									DateManager.addWeek(1);
								else if(repeatGap==1)
									DateManager.addMonth(1);
								else if(repeatGap==2)
									DateManager.addYear(1);
								i++;
							}
							DataManager.decodedText=fixindex;
							DataManager.updateRepeatnum=repeatnum;
							System.out.println(fixindex);
							DataManager.saveIndex('t');
						}
						else{//오프라인모드
							while(i<repeatnum){
								DataManager.fixDecodedText(text);
								if(repeatGap==0)
									DateManager.addWeek(1);
								else if(repeatGap==1)
									DateManager.addMonth(1);
								else if(repeatGap==2)
									DateManager.addYear(1);
								i++;
							}
							DataManager.saveIndex('t');
							DataManager.decodeIndex('t');
						}

						DateManager.loadTemporaryDate();
						frameMacro.dispose();
						Main.weekContentsChanger();
					}
				}
			});
			btnAddTextMacro.setBounds(211, 202, 109, 52);
			pnlMacroText.add(btnAddTextMacro);
	}
	private void saveSDEDRN(){
		startdate[0]=(int)(choiceSDYear.getSelectedItem());
		startdate[1]=(int)(choiceSDMonth.getSelectedItem());
		startdate[2]=(int)(choiceSDDate.getSelectedItem());
		enddate[0]=(int)(choiceEDYear.getSelectedItem());
		enddate[1]=(int) (choiceEDMonth.getSelectedItem());
		enddate[2]=(int)(choiceEDDate.getSelectedItem());

		repeatnum=(int)(choiceRepeateNum.getSelectedItem());
	}
	public static void macroImages(String name, String url){
		int i=1;
		while(i<repeatnum){
			if(repeatGap==0)
				DateManager.addWeek(1);
			else if(repeatGap==1)
				DateManager.addMonth(1);
			else if(repeatGap==2)
				DateManager.addYear(1);
			DataManager.fixDecodedImage("add", DateManager.getOriginal(), name, url);
			if(DataManager.onlinemode){
				ClientManager.connect(new CarrierFromClient(DataManager.serialCode, 'i', 1, DataManager.decodedImage));
			}
			i++;
		}
		nowImgMacro=false;
		
		DateManager.loadTemporaryDate();
	}
}
