package com.linkdiaduler.temp;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;


public class LinkDiaduler {

	public JFrame frameLinkDiaduler;
	private JPanel pnlWeekPage;
	//Control
	private JPanel pnlControl, pnlControlTOP, pnlControlBOTTOM, pnlControlCENTER;
	private JButton btnWarpDate, btnLastYear, btnNextYear, btnLastMonth, btnNextMonth, btnLastWeek, btnNextWeek;
	//Manager
	private JPanel pnlManager;
		//OWNMode
		private JPanel pnlOWNMode;
		private JToggleButton tglbtnEdit;
		private JButton btnMacro, btnSettings, btnSSC;
		//SSCMode
		private JPanel pnlSSCMode;
		private JToggleButton btnAddSSC;
		private JScrollPane spSSC;
		private JList listSSC;
		private JButton btnOWN, btnDelSSC, btnSubscribe;
		private JTextField txtAddSSCnickname;
	//ShowDate
	private JPanel pnlShowDate;
	private JToggleButton lblSunDate, lblMonDate, lblTueDate, lblWedDate, lblThuDate, lblFriDate, lblSatDate;
	//ShowContents
		//Default
		private JPanel pnlShowContents, pnlShowContentsDefault, pnlSunContentsDefault, pnlSunImageDefault, pnlSunImageControl, pnlMonContentsDefault, pnlMonImageDefault, pnlMonImageControl, pnlTueContentsDefault, pnlTueImageDefault, pnlTueImageControl, pnlWedContentsDefault, pnlWedImageDefault, pnlWedImageControl, pnlThuContentsDefault, pnlThuImageDefault, pnlThuImageControl, pnlFriContentsDefault, pnlFriImageDefault, pnlFriImageControl, pnlSatContentsDefault, pnlSatImageDefault, pnlSatImageControl;
		private JButton btnSunImageViewDefault, btnSunImageAdd, btnSunImageDel, btnSunImagePass, btnSunImageReturn, btnMonImageViewDefault, btnMonImageAdd, btnMonImageDel, btnMonImagePass, btnMonImageReturn, btnTueImageViewDefault, btnTueImageAdd, btnTueImageDel, btnTueImagePass, btnTueImageReturn, btnWedImageViewDefault, btnWedImageAdd, btnWedImageDel, btnWedImagePass, btnWedImageReturn, btnThuImageViewDefault, btnThuImageAdd, btnThuImageDel, btnThuImagePass, btnThuImageReturn, btnFriImageViewDefault, btnFriImageAdd, btnFriImageDel, btnFriImagePass, btnFriImageReturn, btnSatImageViewDefault, btnSatImageAdd, btnSatImageDel, btnSatImagePass, btnSatImageReturn;
		private JScrollPane scSunContentsDefault, scMonContentsDefault, scTueContentsDefault, scWedContentsDefault, scThuContentsDefault, scFriContentsDefault, scSatContentsDefault;
		private JEditorPane epSunText, epMonText, epTueText, epWedText, epThuText, epFriText, epSatText;
		//ImageMode
		private JPanel pnlShowContentsImageMode, pnlImageModeControl, pnlImageModeControl2;
		private JButton btnImageModeImage1, btnImageModeImage2, btnImageModeImage3, btnImageModeImage4, btnImageModeImage5, btnImageModeImage6, btnImageModeImage7, btnImageModeImage8, btnLast8Images, btnNext8Images;

	boolean isEditing=false;//텍스트 에디트 중일경우 이동불가.
	boolean imagemode=false;//이미지 모드일경우 이동불가.
	boolean isSubscribing=false;
	int imagenum[]=new int[7];
	int dayofweekImageMode=0;
	int imageWave=0;
	
	public static BufferedImage defaultimage, noimage, settings, macro, edit, save, sun, mon, tue, wed, thu, fri, sat;
	private JCheckBox cbSunDate;
	private JCheckBox cbMonDate;
	private JCheckBox cbTueDate;
	private JCheckBox cbWedDate;
	private JCheckBox cbThuDate;
	private JCheckBox cbFriDate;
	private JCheckBox cbSatDate;

	
	public LinkDiaduler() {
		defaultimage=DataManager.loadimage("defaultimage", "gif");
		noimage=DataManager.loadimage("noimage", "jpg");
		settings=DataManager.loadimage("settings", "png");
		macro=DataManager.loadimage("macro", "png");
		edit=DataManager.loadimage("edit", "png");
		save=DataManager.loadimage("save", "png");
		sun=DataManager.loadimage("sun", "png");
		mon=DataManager.loadimage("mon", "png");
		tue=DataManager.loadimage("tue", "png");
		wed=DataManager.loadimage("wed", "png");
		thu=DataManager.loadimage("thu", "png");
		fri=DataManager.loadimage("fri", "png");
		sat=DataManager.loadimage("sat", "png");
		
		initialize();
	}

	private void initialize() {
		
		frameLinkDiaduler = new JFrame();
		frameLinkDiaduler.getContentPane().setBackground(Settings.background);
		frameLinkDiaduler.setTitle("LinkDiaduler");
		frameLinkDiaduler.setIconImage(UserManager.lindaicon);
		frameLinkDiaduler.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width/2)-(Settings.LDWidth/2), (Toolkit.getDefaultToolkit().getScreenSize().height/2)-(Settings.LDHeight/2)+67, 540, 870);
		frameLinkDiaduler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLinkDiaduler.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();
		        while (notches > 0) {
		        	if(isEditing)
						return;
		        	if(imagemode)
						return;
		        	DateManager.addWeek(1);
					weekContentsChanger();
		            notches--;
		        }
		        while (notches < 0) {
		        	if(isEditing)
						return;
		        	if(imagemode)
						return;
		        	DateManager.addWeek(-1);
					weekContentsChanger();
		            notches++;
		        }
			}
		});
		
		pnlWeekPage = new JPanel();
		pnlWeekPage.setBackground(Settings.background);
		pnlWeekPage.setBorder(new EmptyBorder(7, 7, 7, 7) );
		frameLinkDiaduler.getContentPane().add(pnlWeekPage, BorderLayout.CENTER);
		pnlWeekPage.setLayout(new BorderLayout(0, 0));
		
		
		JPanel pnlNonFlexible = new JPanel();
		pnlNonFlexible.setBackground(Settings.background);
		pnlNonFlexible.setBorder(new EmptyBorder(0, 0, 5, 0) );
		pnlWeekPage.add(pnlNonFlexible, BorderLayout.NORTH);
		pnlNonFlexible.setLayout(new BorderLayout(0, 0));
		
		
		//Control 패널 Start 
		pnlControl = new JPanel();
		pnlNonFlexible.add(pnlControl, BorderLayout.WEST);
		pnlControl.setLayout(new BorderLayout(0, 0));
		
			pnlControlTOP = new JPanel();
			pnlControlTOP.setBackground(Settings.button);
			pnlControl.add(pnlControlTOP, BorderLayout.NORTH);
			GridBagLayout gbl_pnlControlTOP = new GridBagLayout();
			gbl_pnlControlTOP.columnWeights = new double[]{1.0, 1.0};
			gbl_pnlControlTOP.rowWeights = new double[]{0.0};
			pnlControlTOP.setLayout(gbl_pnlControlTOP);
			
			btnLastYear = new JButton("Last Year");
			btnLastYear.setForeground(Settings.font);
			btnLastYear.setBackground(Settings.button);
			btnLastYear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//작년 대입
					if(isEditing)
						return;
					if(imagemode)
						return;
					DateManager.addYear(-1);
					weekContentsChanger();
				}
			});
			GridBagConstraints gbc_btnLastYear = new GridBagConstraints();
			gbc_btnLastYear.fill = GridBagConstraints.BOTH;
			gbc_btnLastYear.gridx = 0;
			gbc_btnLastYear.gridy = 0;
			pnlControlTOP.add(btnLastYear, gbc_btnLastYear);
			
			btnNextYear = new JButton("Next Year");
			btnNextYear.setForeground(Settings.font);
			btnNextYear.setBackground(Settings.button);
			btnNextYear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//내년 대입
					if(isEditing)
						return;
					if(imagemode)
						return;
					DateManager.addYear(1);
					weekContentsChanger();
				}
			});
			GridBagConstraints gbc_btnNextYear = new GridBagConstraints();
			gbc_btnNextYear.fill = GridBagConstraints.BOTH;
			gbc_btnNextYear.gridx = 1;
			gbc_btnNextYear.gridy = 0;
			pnlControlTOP.add(btnNextYear, gbc_btnNextYear);
			
			pnlControlBOTTOM = new JPanel();
			pnlControlBOTTOM.setBackground(Settings.button);
			pnlControl.add(pnlControlBOTTOM, BorderLayout.SOUTH);
			GridBagLayout gbl_pnlControlBOTTOM = new GridBagLayout();
			gbl_pnlControlBOTTOM.columnWeights = new double[]{1.0, 1.0};
			gbl_pnlControlBOTTOM.rowWeights = new double[]{0.0};
			pnlControlBOTTOM.setLayout(gbl_pnlControlBOTTOM);
			
			btnLastMonth = new JButton("Last Month");
			btnLastMonth.setForeground(Settings.font);
			btnLastMonth.setBackground(Settings.button);
			btnLastMonth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//저번달 대입
					if(isEditing)
						return;
					if(imagemode)
						return;
					DateManager.addMonth(-1);
					weekContentsChanger();
				}
			});
			GridBagConstraints gbc_btnLastMonth = new GridBagConstraints();
			gbc_btnLastMonth.fill = GridBagConstraints.BOTH;
			gbc_btnLastMonth.gridx = 0;
			gbc_btnLastMonth.gridy = 0;
			pnlControlBOTTOM.add(btnLastMonth, gbc_btnLastMonth);
			
			btnNextMonth = new JButton("Next Month");
			btnNextMonth.setForeground(Settings.font);
			btnNextMonth.setBackground(Settings.button);
			btnNextMonth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//다음달 대입
					if(isEditing)
						return;
					if(imagemode)
						return;
					DateManager.addMonth(1);
					weekContentsChanger();
				}
			});
			GridBagConstraints gbc_btnNextMonth = new GridBagConstraints();
			gbc_btnNextMonth.fill = GridBagConstraints.BOTH;
			gbc_btnNextMonth.gridx = 1;
			gbc_btnNextMonth.gridy = 0;
			pnlControlBOTTOM.add(btnNextMonth, gbc_btnNextMonth);
			
			pnlControlCENTER = new JPanel();
			pnlControlCENTER.setBackground(Settings.button);
			pnlControl.add(pnlControlCENTER, BorderLayout.CENTER);
			GridBagLayout gbl_pnlControlCENTER = new GridBagLayout();
			gbl_pnlControlCENTER.columnWidths = new int[]{0, 100, 0};
			gbl_pnlControlCENTER.columnWeights = new double[]{1.0, 1.0, 1.0};
			gbl_pnlControlCENTER.rowWeights = new double[]{0.0};
			pnlControlCENTER.setLayout(gbl_pnlControlCENTER);
			
			btnLastWeek = new JButton("◀");
			btnLastWeek.setForeground(Settings.font);
			btnLastWeek.setBackground(Settings.button);
			btnLastWeek.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//저번주 대입
					if(isEditing)
						return;
					if(imagemode)
						return;
					DateManager.addWeek(-1);
					weekContentsChanger();
				}
			});
			GridBagConstraints gbc_btnLastWeek = new GridBagConstraints();
			gbc_btnLastWeek.insets = new Insets(0, 0, 0, 5);
			gbc_btnLastWeek.fill = GridBagConstraints.BOTH;
			gbc_btnLastWeek.gridx = 0;
			gbc_btnLastWeek.gridy = 0;
			pnlControlCENTER.add(btnLastWeek, gbc_btnLastWeek);
			
			btnNextWeek = new JButton("▶");
			btnNextWeek.setForeground(Settings.font);
			btnNextWeek.setBackground(Settings.button);
			btnNextWeek.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//다음주 대입
					if(isEditing)
						return;
					if(imagemode)
						return;
					DateManager.addWeek(1);
					weekContentsChanger();
				}
			});
			
			btnWarpDate = new JButton("Today's Date");
			btnWarpDate.setForeground(Settings.font);
			btnWarpDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(isEditing || imagemode)
						return;
					Main.openframeWarpDate();
				}
			});
			btnWarpDate.setBackground(Settings.button);
			btnWarpDate.setToolTipText("\uB0A0\uC9DC\uB97C \uBCC0\uACBD\uD560 \uC218 \uC788\uC2B5\uB2C8\uB2E4.");
			GridBagConstraints gbc_btnWarpDate = new GridBagConstraints();
			gbc_btnWarpDate.fill = GridBagConstraints.BOTH;
			gbc_btnWarpDate.insets = new Insets(0, 0, 0, 5);
			gbc_btnWarpDate.gridx = 1;
			gbc_btnWarpDate.gridy = 0;
			pnlControlCENTER.add(btnWarpDate, gbc_btnWarpDate);
			GridBagConstraints gbc_btnNextWeek = new GridBagConstraints();
			gbc_btnNextWeek.fill = GridBagConstraints.BOTH;
			gbc_btnNextWeek.gridx = 2;
			gbc_btnNextWeek.gridy = 0;
			pnlControlCENTER.add(btnNextWeek, gbc_btnNextWeek);
		//Control 패널  End
		
		
		
		
		//Manager 패널  Start
		pnlManager = new JPanel();
		pnlManager.setBackground(Settings.background);
		pnlNonFlexible.add(pnlManager, BorderLayout.CENTER);
		pnlManager.setLayout(new CardLayout(0, 0));
		
		pnlOWNMode = new JPanel();
		pnlOWNMode.setBackground(Settings.background);
		pnlManager.add(pnlOWNMode);
		pnlOWNMode.setLayout(null);
			
			tglbtnEdit = new JToggleButton(new ImageIcon(edit));
			tglbtnEdit.setToolTipText("텍스트 수정모드로 변환합니다. 날짜이동이 불가능해집니다.");
			tglbtnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tglbtnEdit.getToolTipText()=="텍스트 수정모드로 변환합니다. 날짜이동이 불가능해집니다."){//토글
						tglbtnEdit.setIcon(new ImageIcon(save));
						tglbtnEdit.setToolTipText("텍스트 수정모드를 마칩니다. 날짜이동이 가능해집니다.");
						epSunText.setEditable(true);
						epMonText.setEditable(true);
						epTueText.setEditable(true);
						epWedText.setEditable(true);
						epThuText.setEditable(true);
						epFriText.setEditable(true);
						epSatText.setEditable(true);
						isEditing=(true);
						
						if(DataManager.onlinemode)
							btnSSC.setVisible(false);
					}
					else{//버튼 토글이 풀릴때
						tglbtnEdit.setIcon(new ImageIcon(edit));
						tglbtnEdit.setToolTipText("텍스트 수정모드로 변환합니다. 날짜이동이 불가능해집니다.");
						int[] textLength=new int[7];
						try {
							textLength[0] = epSunText.getDocument().getText(0, epSunText.getDocument().getLength()).length();
							textLength[1] = epMonText.getDocument().getText(0, epMonText.getDocument().getLength()).length();
							textLength[2] = epTueText.getDocument().getText(0, epTueText.getDocument().getLength()).length();
							textLength[3] = epWedText.getDocument().getText(0, epWedText.getDocument().getLength()).length();
							textLength[4] = epThuText.getDocument().getText(0, epThuText.getDocument().getLength()).length();
							textLength[5] = epFriText.getDocument().getText(0, epFriText.getDocument().getLength()).length();
							textLength[6] = epSatText.getDocument().getText(0, epSatText.getDocument().getLength()).length();
						} catch (BadLocationException e1) {
						}
						if(DataManager.onlinemode){//온라인 글자 갯수제한	
							if(textLength[0]>60 || textLength[1]>60 || textLength[2]>60 || textLength[3]>60 || textLength[4]>60 || textLength[5]>60 || textLength[6]>60){
								JOptionPane.showMessageDialog(null, "60자를 초과하였습니다", "Error!", JOptionPane.ERROR_MESSAGE);
								return;//아래의 버튼 원상복귀를 스킵함
							}
						}
	
						DataManager.fixDecodedText(0, epSunText.getText(), textLength[0]);
						DataManager.fixDecodedText(1, epMonText.getText(), textLength[1]);
						DataManager.fixDecodedText(2, epTueText.getText(), textLength[2]);
						DataManager.fixDecodedText(3, epWedText.getText(), textLength[3]);
						DataManager.fixDecodedText(4, epThuText.getText(), textLength[4]);
						DataManager.fixDecodedText(5, epFriText.getText(), textLength[5]);
						DataManager.fixDecodedText(6, epSatText.getText(), textLength[6]);
						DataManager.saveIndex('t');
						
						
						if(DataManager.onlinemode)
							btnSSC.setVisible(true);
						epSunText.setEditable(false);
						epMonText.setEditable(false);
						epTueText.setEditable(false);
						epWedText.setEditable(false);
						epThuText.setEditable(false);
						epFriText.setEditable(false);
						epSatText.setEditable(false);
						isEditing=(false);
					}
				}	
			});
			tglbtnEdit.setBounds(150, 60, 40, 20);
			pnlOWNMode.add(tglbtnEdit);
			
			btnMacro = new JButton(new ImageIcon(macro));
			btnMacro.setToolTipText("주간/월간/년간 반복 일정을 작성가능합니다.");
			btnMacro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Main.openframeMacro();
				}
			});
			btnMacro.setBounds(12, 60, 50, 20);
			pnlOWNMode.add(btnMacro);
			
			btnSettings = new JButton(rescaleImage(settings, 23, 23));
			btnSettings.setBackground(Color.WHITE);
			btnSettings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Main.openframeSettings();
				}
			});
			btnSettings.setBounds(12, 0, 23, 23);
			pnlOWNMode.add(btnSettings);
			
			JLabel lblWelcome = new JLabel(DataManager.user+"님 웰컴!ㅋ");
			lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
			lblWelcome.setForeground(Settings.font);
			lblWelcome.setFont(new Font("굴림", Font.BOLD, 13));
			lblWelcome.setBounds(12, 30, 178, 20);
			pnlOWNMode.add(lblWelcome);
			
			if(DataManager.onlinemode){
				btnSSC = new JButton("SSC");
				btnSSC.setToolTipText("구독모드로 변경합니다.");
				btnSSC.setBackground(Settings.button);
				btnSSC.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						isSubscribing=true;
						pnlOWNMode.setVisible(false);
						pnlSSCMode.setVisible(true);
					
						btnSunImageAdd.setVisible(false);
						btnSunImageDel.setVisible(false);
						btnMonImageAdd.setVisible(false);
						btnMonImageDel.setVisible(false);
						btnTueImageAdd.setVisible(false);
						btnTueImageDel.setVisible(false);
						btnWedImageAdd.setVisible(false);
						btnWedImageDel.setVisible(false);
						btnThuImageAdd.setVisible(false);
						btnThuImageDel.setVisible(false);
						btnFriImageAdd.setVisible(false);
						btnFriImageDel.setVisible(false);
						btnSatImageAdd.setVisible(false);
						btnSatImageDel.setVisible(false);
						
						weekContentsChanger();
					}
				});
				btnSSC.setBounds(140, 0, 63, 23);
				pnlOWNMode.add(btnSSC);
			}		
		
		pnlSSCMode = new JPanel();
		pnlSSCMode.setBackground(Settings.background);
		pnlManager.add(pnlSSCMode);
		pnlSSCMode.setLayout(null);
		pnlSSCMode.setVisible(false);
		
			btnOWN = new JButton("OWN");
			btnOWN.setToolTipText("자신의 계정으로 돌아옵니다.");
			btnOWN.setBackground(Settings.button);
			btnOWN.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					pnlOWNMode.setVisible(true);
					pnlSSCMode.setVisible(false);
					
					btnSunImageAdd.setVisible(true);
					btnSunImageDel.setVisible(true);
					btnMonImageAdd.setVisible(true);
					btnMonImageDel.setVisible(true);
					btnTueImageAdd.setVisible(true);
					btnTueImageDel.setVisible(true);
					btnWedImageAdd.setVisible(true);
					btnWedImageDel.setVisible(true);
					btnThuImageAdd.setVisible(true);
					btnThuImageDel.setVisible(true);
					btnFriImageAdd.setVisible(true);
					btnFriImageDel.setVisible(true);
					btnSatImageAdd.setVisible(true);
					btnSatImageDel.setVisible(true);
					isSubscribing=false;
					weekContentsChanger();
				}
			});
			btnOWN.setBounds(140, 0, 63, 23);
			pnlSSCMode.add(btnOWN);
			
			spSSC = new JScrollPane();
			spSSC.setBounds(12, 0, 124, 59);
			pnlSSCMode.add(spSSC);
			listSSC = new JList();
			if(DataManager.SSClist!=null){
				Vector vector=new Vector();
				vector.addAll(Arrays.asList(DataManager.returnSSCArray()));
				listSSC.setListData(vector);
			}
			listSSC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			spSSC.setViewportView(listSSC);
			
			btnAddSSC = new JToggleButton("+");
			btnAddSSC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(btnAddSSC.getToolTipText()=="배포자 추가 모드를 불러옵니다."){
						btnAddSSC.setToolTipText("입력한 배포자를 추가합니다.");
						txtAddSSCnickname.setVisible(true);
						spSSC.setVisible(false);
						btnDelSSC.setVisible(false);
						btnSubscribe.setVisible(false);
					}
					else{
						String addSSCNickname=txtAddSSCnickname.getText();
						
						txtAddSSCnickname.setVisible(false);
						spSSC.setVisible(true);
						btnDelSSC.setVisible(true);
						btnSubscribe.setVisible(true);
						btnAddSSC.setToolTipText("배포자 추가 모드를 불러옵니다.");
						
						ClientManager.connect(new CarrierFromClient('a', DataManager.serialCode, DataManager.SSClist, addSSCNickname));
	
						if(DataManager.SSClist!=null){
							Vector vector=new Vector();
							vector.addAll(Arrays.asList(DataManager.returnSSCArray()));
							listSSC.setListData(vector);
							spSSC.setViewportView(listSSC);
						}
					}
				}
			});
			btnAddSSC.setToolTipText("배포자 추가 모드를 불러옵니다.");
			btnAddSSC.setBackground(Settings.button);
			btnAddSSC.setBounds(150, 36, 53, 23);
			pnlSSCMode.add(btnAddSSC);
			
			btnDelSSC = new JButton("-");
			btnDelSSC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(listSSC.isSelectionEmpty())
						return;
					ClientManager.connect(new CarrierFromClient('d', DataManager.serialCode, DataManager.SSClist, (String)listSSC.getSelectedValue()));
	
					if(DataManager.SSClist!=null){
						Vector vector=new Vector();
						vector.addAll(Arrays.asList(DataManager.returnSSCArray()));
						listSSC.setListData(vector);
						spSSC.setViewportView(listSSC);
					}
					else{
						Vector vector=new Vector();
						listSSC.setListData(vector);
						spSSC.setViewportView(listSSC);
					}
				}
			});
			btnDelSSC.setToolTipText("\uC120\uD0DD\uD55C \uBC30\uD3EC\uC790\uB97C \uC0AD\uC81C\uD569\uB2C8\uB2E4.");
			btnDelSSC.setBackground(Settings.button);
			btnDelSSC.setBounds(150, 60, 53, 23);
			pnlSSCMode.add(btnDelSSC);
			
			btnSubscribe = new JButton("Subscribe");
			btnSubscribe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listSSC.isSelectionEmpty())
						return;
					ClientManager.connect(new CarrierFromClient((String)listSSC.getSelectedValue()));
					weekContentsChanger();
				}
			});
			btnSubscribe.setBackground(Settings.button);
			btnSubscribe.setToolTipText("\uB9AC\uC2A4\uD2B8\uC5D0\uC11C \uC120\uD0DD\uD55C \uAD6C\uB3C5\uC790\uB97C \uBD88\uB7EC\uC635\uB2C8\uB2E4.");
			btnSubscribe.setBounds(12, 60, 124, 23);
			pnlSSCMode.add(btnSubscribe);
			
			txtAddSSCnickname = new JTextField();
			txtAddSSCnickname.setVisible(false);
			txtAddSSCnickname.setBounds(12, 36, 124, 23);
			pnlSSCMode.add(txtAddSSCnickname);
			txtAddSSCnickname.setColumns(10);
		//Manager 패널 End
		
		
		
		
		//Manager 패널 End
		pnlShowDate = new JPanel();
		pnlShowDate.setBackground(Settings.background);
		pnlShowDate.setBorder(new EmptyBorder(0, 0, 0, 5) );
		pnlWeekPage.add(pnlShowDate, BorderLayout.WEST);
		GridBagConstraints gbc_pnlShowDate = new GridBagConstraints();
		gbc_pnlShowDate.insets = new Insets(0, 10, 10, 5);
		gbc_pnlShowDate.fill = GridBagConstraints.BOTH;
		gbc_pnlShowDate.gridx = 0;
		gbc_pnlShowDate.gridy = 1;
		gbc_pnlShowDate.gridheight=22;
		GridBagLayout gbl_pnlShowDate = new GridBagLayout();
		gbl_pnlShowDate.columnWidths = new int[]{70};
		gbl_pnlShowDate.columnWeights = new double[]{1.0};
		gbl_pnlShowDate.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0};
		pnlShowDate.setLayout(gbl_pnlShowDate);
		
			lblSunDate = new JToggleButton("Sun Date", new ImageIcon(sun));
			lblSunDate.setForeground(Settings.font);
			lblSunDate.setToolTipText("\uD574\uB2F9 \uC694\uC77C\uC758 \uC774\uBBF8\uC9C0 \uBAA8\uB4DC\uB85C \uC804\uD658\uD569\uB2C8\uB2E4.");
			lblSunDate.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblSunDate.setHorizontalTextPosition(SwingConstants.CENTER);
			lblSunDate.setBackground(Settings.button);
			lblSunDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dayofweekImageMode=0;
					lblMonDate.setSelected(false);
					lblTueDate.setSelected(false);
					lblWedDate.setSelected(false);
					lblThuDate.setSelected(false);
					lblFriDate.setSelected(false);
					lblSatDate.setSelected(false);
					if(lblSunDate.isSelected())
						dayContentsModeChanger(true);
					else
						dayContentsModeChanger(false);
					pnlShowDate.setVisible(false);
				}
			});
			
			cbSunDate = new JCheckBox("");
			GridBagConstraints gbc_cbSunDate = new GridBagConstraints();
			gbc_cbSunDate.insets = new Insets(0, 0, 0, 0);
			gbc_cbSunDate.gridx = 0;
			gbc_cbSunDate.gridy = 0;
			pnlShowDate.add(cbSunDate, gbc_cbSunDate);
			GridBagConstraints gbc_lblSunDate = new GridBagConstraints();
			gbc_lblSunDate.fill = GridBagConstraints.BOTH;
			gbc_lblSunDate.insets = new Insets(0, 0, 0, 0);
			gbc_lblSunDate.gridx = 0;
			gbc_lblSunDate.gridy = 1;
			pnlShowDate.add(lblSunDate, gbc_lblSunDate);
			
			lblMonDate = new JToggleButton("Mon Date", new ImageIcon(mon));
			lblMonDate.setForeground(Settings.font);
			lblMonDate.setToolTipText("\uD574\uB2F9 \uC694\uC77C\uC758 \uC774\uBBF8\uC9C0 \uBAA8\uB4DC\uB85C \uC804\uD658\uD569\uB2C8\uB2E4.");
			lblMonDate.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblMonDate.setHorizontalTextPosition(SwingConstants.CENTER);
			lblMonDate.setBackground(Settings.button);
			lblMonDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dayofweekImageMode=1;
					lblSunDate.setSelected(false);
					lblTueDate.setSelected(false);
					lblWedDate.setSelected(false);
					lblThuDate.setSelected(false);
					lblFriDate.setSelected(false);
					lblSatDate.setSelected(false);
					if(lblMonDate.isSelected())
						dayContentsModeChanger(true);
					else
						dayContentsModeChanger(false);
				}
			});
			
			cbMonDate = new JCheckBox("");
			GridBagConstraints gbc_cbMonDate = new GridBagConstraints();
			gbc_cbMonDate.insets = new Insets(0, 0, 0, 0);
			gbc_cbMonDate.gridx = 0;
			gbc_cbMonDate.gridy = 2;
			pnlShowDate.add(cbMonDate, gbc_cbMonDate);
			GridBagConstraints gbc_lblMonDate = new GridBagConstraints();
			gbc_lblMonDate.fill = GridBagConstraints.BOTH;
			gbc_lblMonDate.insets = new Insets(0, 0, 0, 0);
			gbc_lblMonDate.gridx = 0;
			gbc_lblMonDate.gridy = 3;
			pnlShowDate.add(lblMonDate, gbc_lblMonDate);
			
			lblTueDate = new JToggleButton("Tue Date", new ImageIcon(tue));
			lblTueDate.setForeground(Settings.font);
			lblTueDate.setToolTipText("\uD574\uB2F9 \uC694\uC77C\uC758 \uC774\uBBF8\uC9C0 \uBAA8\uB4DC\uB85C \uC804\uD658\uD569\uB2C8\uB2E4.");
			lblTueDate.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblTueDate.setHorizontalTextPosition(SwingConstants.CENTER);
			lblTueDate.setBackground(Settings.button);
			lblTueDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dayofweekImageMode=2;
					lblSunDate.setSelected(false);
					lblMonDate.setSelected(false);
					lblWedDate.setSelected(false);
					lblThuDate.setSelected(false);
					lblFriDate.setSelected(false);
					lblSatDate.setSelected(false);
					if(lblTueDate.isSelected())
						dayContentsModeChanger(true);
					else
						dayContentsModeChanger(false);
				}
			});
			
			cbTueDate = new JCheckBox("");
			GridBagConstraints gbc_cbTueDate = new GridBagConstraints();
			gbc_cbTueDate.insets = new Insets(0, 0, 0, 0);
			gbc_cbTueDate.gridx = 0;
			gbc_cbTueDate.gridy = 4;
			pnlShowDate.add(cbTueDate, gbc_cbTueDate);
			GridBagConstraints gbc_lblTueDate = new GridBagConstraints();
			gbc_lblTueDate.fill = GridBagConstraints.BOTH;
			gbc_lblTueDate.insets = new Insets(0, 0, 0, 0);
			gbc_lblTueDate.gridx = 0;
			gbc_lblTueDate.gridy = 5;
			pnlShowDate.add(lblTueDate, gbc_lblTueDate);
			
			lblWedDate = new JToggleButton("Wed Date", new ImageIcon(wed));
			lblWedDate.setForeground(Settings.font);
			lblWedDate.setToolTipText("\uD574\uB2F9 \uC694\uC77C\uC758 \uC774\uBBF8\uC9C0 \uBAA8\uB4DC\uB85C \uC804\uD658\uD569\uB2C8\uB2E4.");
			lblWedDate.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblWedDate.setHorizontalTextPosition(SwingConstants.CENTER);
			lblWedDate.setBackground(Settings.button);
			lblWedDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dayofweekImageMode=3;
					lblSunDate.setSelected(false);
					lblMonDate.setSelected(false);
					lblTueDate.setSelected(false);
					lblThuDate.setSelected(false);
					lblFriDate.setSelected(false);
					lblSatDate.setSelected(false);
					if(lblWedDate.isSelected())
						dayContentsModeChanger(true);
					else
						dayContentsModeChanger(false);
				}
			});
			
			cbWedDate = new JCheckBox("");
			GridBagConstraints gbc_cbWedDate = new GridBagConstraints();
			gbc_cbWedDate.insets = new Insets(0, 0, 0, 0);
			gbc_cbWedDate.gridx = 0;
			gbc_cbWedDate.gridy = 6;
			pnlShowDate.add(cbWedDate, gbc_cbWedDate);
			GridBagConstraints gbc_lblWedDate = new GridBagConstraints();
			gbc_lblWedDate.fill = GridBagConstraints.BOTH;
			gbc_lblWedDate.insets = new Insets(0, 0, 0, 0);
			gbc_lblWedDate.gridx = 0;
			gbc_lblWedDate.gridy = 7;
			pnlShowDate.add(lblWedDate, gbc_lblWedDate);
			
			lblThuDate = new JToggleButton("Thu Date", new ImageIcon(thu));
			lblThuDate.setForeground(Settings.font);
			lblThuDate.setToolTipText("\uD574\uB2F9 \uC694\uC77C\uC758 \uC774\uBBF8\uC9C0 \uBAA8\uB4DC\uB85C \uC804\uD658\uD569\uB2C8\uB2E4.");
			lblThuDate.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblThuDate.setHorizontalTextPosition(SwingConstants.CENTER);
			lblThuDate.setBackground(Settings.button);
			lblThuDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dayofweekImageMode=4;
					lblSunDate.setSelected(false);
					lblMonDate.setSelected(false);
					lblTueDate.setSelected(false);
					lblWedDate.setSelected(false);
					lblFriDate.setSelected(false);
					lblSatDate.setSelected(false);
					if(lblThuDate.isSelected())
						dayContentsModeChanger(true);
					else
						dayContentsModeChanger(false);
				}
			});
			
			cbThuDate = new JCheckBox("");
			GridBagConstraints gbc_cbThuDate = new GridBagConstraints();
			gbc_cbThuDate.insets = new Insets(0, 0, 0, 0);
			gbc_cbThuDate.gridx = 0;
			gbc_cbThuDate.gridy = 8;
			pnlShowDate.add(cbThuDate, gbc_cbThuDate);
			GridBagConstraints gbc_lblThuDate = new GridBagConstraints();
			gbc_lblThuDate.fill = GridBagConstraints.BOTH;
			gbc_lblThuDate.insets = new Insets(0, 0, 0, 0);
			gbc_lblThuDate.gridx = 0;
			gbc_lblThuDate.gridy = 9;
			pnlShowDate.add(lblThuDate, gbc_lblThuDate);
			
			lblFriDate = new JToggleButton("Fri Date", new ImageIcon(fri));
			lblFriDate.setForeground(Settings.font);
			lblFriDate.setToolTipText("\uD574\uB2F9 \uC694\uC77C\uC758 \uC774\uBBF8\uC9C0 \uBAA8\uB4DC\uB85C \uC804\uD658\uD569\uB2C8\uB2E4.");
			lblFriDate.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblFriDate.setHorizontalTextPosition(SwingConstants.CENTER);
			lblFriDate.setBackground(Settings.button);
			lblFriDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dayofweekImageMode=5;
					lblSunDate.setSelected(false);
					lblMonDate.setSelected(false);
					lblTueDate.setSelected(false);
					lblWedDate.setSelected(false);
					lblThuDate.setSelected(false);
					lblSatDate.setSelected(false);
					if(lblFriDate.isSelected())
						dayContentsModeChanger(true);
					else
						dayContentsModeChanger(false);
				}
			});
			
			cbFriDate = new JCheckBox("");
			GridBagConstraints gbc_cbFriDate = new GridBagConstraints();
			gbc_cbFriDate.insets = new Insets(0, 0, 0, 0);
			gbc_cbFriDate.gridx = 0;
			gbc_cbFriDate.gridy = 10;
			pnlShowDate.add(cbFriDate, gbc_cbFriDate);
			GridBagConstraints gbc_lblFriDate = new GridBagConstraints();
			gbc_lblFriDate.fill = GridBagConstraints.BOTH;
			gbc_lblFriDate.insets = new Insets(0, 0, 0, 0);
			gbc_lblFriDate.gridx = 0;
			gbc_lblFriDate.gridy = 11;
			pnlShowDate.add(lblFriDate, gbc_lblFriDate);
			
			lblSatDate = new JToggleButton("Sat Date", new ImageIcon(sat));
			lblSatDate.setForeground(Settings.font);
			lblSatDate.setToolTipText("\uD574\uB2F9 \uC694\uC77C\uC758 \uC774\uBBF8\uC9C0 \uBAA8\uB4DC\uB85C \uC804\uD658\uD569\uB2C8\uB2E4.");
			lblSatDate.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblSatDate.setHorizontalTextPosition(SwingConstants.CENTER);
			lblSatDate.setBackground(Settings.button);
			lblSatDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dayofweekImageMode=6;
					lblSunDate.setSelected(false);
					lblMonDate.setSelected(false);
					lblTueDate.setSelected(false);
					lblWedDate.setSelected(false);
					lblThuDate.setSelected(false);
					lblFriDate.setSelected(false);
					if(lblSatDate.isSelected())
						dayContentsModeChanger(true);
					else
						dayContentsModeChanger(false);
				}
			});
			
			cbSatDate = new JCheckBox("");
			GridBagConstraints gbc_cbSatDate = new GridBagConstraints();
			gbc_cbSatDate.insets = new Insets(0, 0, 0, 0);
			gbc_cbSatDate.gridx = 0;
			gbc_cbSatDate.gridy = 12;
			pnlShowDate.add(cbSatDate, gbc_cbSatDate);
			GridBagConstraints gbc_lblSatDate = new GridBagConstraints();
			gbc_lblSatDate.fill = GridBagConstraints.BOTH;
			gbc_lblSatDate.gridx = 0;
			gbc_lblSatDate.gridy = 13;
			pnlShowDate.add(lblSatDate, gbc_lblSatDate);
		//ShowDate 패널 End
		
		
		
		//ShowContents 패널 Start
		pnlShowContents = new JPanel();
		pnlShowContents.setBackground(Settings.background);
		pnlWeekPage.add(pnlShowContents, BorderLayout.CENTER);
		pnlShowContents.setLayout(new CardLayout(0, 0));
		
			//ShowContentsDefault 패널 Start
			pnlShowContentsDefault = new JPanel();
			pnlShowContentsDefault.setBackground(Settings.background);
			pnlShowContents.add(pnlShowContentsDefault);
			GridBagLayout gbl_pnlShowContentsDefault = new GridBagLayout();
			gbl_pnlShowContentsDefault.columnWeights = new double[]{1.0};
			gbl_pnlShowContentsDefault.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
			pnlShowContentsDefault.setLayout(gbl_pnlShowContentsDefault);
			
			pnlSunContentsDefault = new JPanel();
			pnlSunContentsDefault.setPreferredSize(new Dimension(1000, lblSunDate.getHeight()));
			GridBagConstraints gbc_pnlSunContentsDefault = new GridBagConstraints();
			gbc_pnlSunContentsDefault.insets = new Insets(0, 0, 0, 0);
			gbc_pnlSunContentsDefault.fill = GridBagConstraints.BOTH;
			gbc_pnlSunContentsDefault.gridx = 0;
			gbc_pnlSunContentsDefault.gridy = 0;
			pnlShowContentsDefault.add(pnlSunContentsDefault, gbc_pnlSunContentsDefault);
			pnlSunContentsDefault.setLayout(new GridLayout(1, 0, 0, 0));
			
			scSunContentsDefault = new JScrollPane();
			scSunContentsDefault.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			pnlSunContentsDefault.add(scSunContentsDefault);
			
			epSunText = new JEditorPane("text/html", "UTF-8");
			epSunText.setForeground(Settings.font);
			epSunText.setBackground(new Color(255, 255, 240));
			epSunText.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent hle) {
                    if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                        try {
                        	Desktop.getDesktop().browse(new java.net.URI(hle.getURL().toString()));
                        }
                        catch(IOException e1) {
                        	e1.printStackTrace();
                        }
                        catch(URISyntaxException e1) {
                        	e1.printStackTrace();
                        }
                    }
                }
			});
			epSunText.setSize(500,lblSunDate.getHeight());
			epSunText.setFont(new Font("굴림", Font.PLAIN, 8));
			scSunContentsDefault.setViewportView(epSunText);
			
			pnlSunImageDefault = new JPanel();
			pnlSunImageDefault.setBackground(Settings.background);
			scSunContentsDefault.setRowHeaderView(pnlSunImageDefault);
			pnlSunImageDefault.setLayout(new BorderLayout(0, 0));
			
			btnSunImageViewDefault = new JButton("");
			btnSunImageViewDefault.setBackground(new Color(255, 255, 255));
			btnSunImageViewDefault.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					 try {
						 Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[0][imagenum[0]]));
					 }
					 catch(IOException e1) {
						 e1.printStackTrace();
					 }
					 catch(URISyntaxException e1) {
						 e1.printStackTrace();
					 }
					
				}
			});
			btnSunImageViewDefault.setSize(120, 100);
			btnSunImageViewDefault.setMargin(new Insets(0, 0, 0, 0));
			btnSunImageViewDefault.setForeground(Color.WHITE);
			pnlSunImageDefault.add(btnSunImageViewDefault, BorderLayout.CENTER);
			
			pnlSunImageControl = new JPanel();
			pnlSunImageControl.setBackground(Settings.background);
			pnlSunImageDefault.add(pnlSunImageControl, BorderLayout.EAST);
			GridBagLayout gbl_pnlSunImageControl = new GridBagLayout();
			gbl_pnlSunImageControl.columnWeights = new double[]{0.0};
			gbl_pnlSunImageControl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
			pnlSunImageControl.setLayout(gbl_pnlSunImageControl);
			
			btnSunImageAdd = new JButton("+");
			btnSunImageAdd.setForeground(Settings.font);
			btnSunImageAdd.setBackground(Settings.button);
			btnSunImageAdd.setToolTipText("\uC774\uBBF8\uC9C0\uB97C \uCD94\uAC00\uD569\uB2C8\uB2E4.");
			btnSunImageAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Main.openframeImageContainer(DateManager.dayofweekCalendar("sunCal"));
				}
			});
			btnSunImageAdd.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnSunImageAdd = new GridBagConstraints();
			gbc_btnSunImageAdd.fill = GridBagConstraints.BOTH;
			gbc_btnSunImageAdd.insets = new Insets(0, 0, 0, 0);
			gbc_btnSunImageAdd.gridx = 0;
			gbc_btnSunImageAdd.gridy = 0;
			pnlSunImageControl.add(btnSunImageAdd, gbc_btnSunImageAdd);
			
			btnSunImageDel = new JButton("-");
			btnSunImageDel.setForeground(Settings.font);
			btnSunImageDel.setBackground(Settings.button);
			btnSunImageDel.setToolTipText("\uD574\uB2F9 \uC774\uBBF8\uC9C0\uB97C \uC0AD\uC81C\uD569\uB2C8\uB2E4.");
			btnSunImageDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(DataManager.dayofweekICnum[0]!=0){
						DataManager.fixDecodedImage("del", DateManager.dayofweekCalendar("sunCal"), DataManager.dayofweekImagename[0][imagenum[0]], DataManager.dayofweekURL[0][imagenum[0]]);
						if(DataManager.onlinemode)
							ClientManager.connect(new CarrierFromClient("DEL", DataManager.serialCode, DataManager.decodedImage));
						else
							DataManager.saveIndex('i');
					
						weekContentsChanger();
					}
				}
			});
			btnSunImageDel.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnSunImageDel = new GridBagConstraints();
			gbc_btnSunImageDel.fill = GridBagConstraints.BOTH;
			gbc_btnSunImageDel.insets = new Insets(0, 0, 0, 0);
			gbc_btnSunImageDel.gridx = 0;
			gbc_btnSunImageDel.gridy = 1;
			pnlSunImageControl.add(btnSunImageDel, gbc_btnSunImageDel);
			
			btnSunImagePass = new JButton("\u25B2");
			btnSunImagePass.setForeground(Settings.font);
			btnSunImagePass.setBackground(Settings.button);
			btnSunImagePass.setToolTipText("\uC774\uC804 \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnSunImagePass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(imagenum[0]-1 >= 0){
						btnSunImageViewDefault.setIcon(dayImageChanger(0, false));
						imagenum[0]--;
					}
				}
			});
			btnSunImagePass.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnSunImagePass = new GridBagConstraints();
			gbc_btnSunImagePass.fill = GridBagConstraints.BOTH;
			gbc_btnSunImagePass.insets = new Insets(0, 0, 0, 0);
			gbc_btnSunImagePass.gridx = 0;
			gbc_btnSunImagePass.gridy = 2;
			pnlSunImageControl.add(btnSunImagePass, gbc_btnSunImagePass);
			
			btnSunImageReturn = new JButton("\u25BC");
			btnSunImageReturn.setForeground(Settings.font);
			btnSunImageReturn.setBackground(Settings.button);
			btnSunImageReturn.setToolTipText("\uB2E4\uC74C \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnSunImageReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(imagenum[0]+1 <= DataManager.dayofweekICnum[0]-1){
						btnSunImageViewDefault.setIcon(dayImageChanger(0, true));
						imagenum[0]++;
					}
				}
			});
			btnSunImageReturn.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnSunImageReturn = new GridBagConstraints();
			gbc_btnSunImageReturn.fill = GridBagConstraints.BOTH;
			gbc_btnSunImageReturn.gridx = 0;
			gbc_btnSunImageReturn.gridy = 3;
			pnlSunImageControl.add(btnSunImageReturn, gbc_btnSunImageReturn);
			
			pnlMonContentsDefault = new JPanel();
			pnlMonContentsDefault.setPreferredSize(new Dimension(1000, lblSunDate.getHeight()));
			GridBagConstraints gbc_pnlMonContentsDefault = new GridBagConstraints();
			gbc_pnlMonContentsDefault.insets = new Insets(0, 0, 0, 0);
			gbc_pnlMonContentsDefault.fill = GridBagConstraints.BOTH;
			gbc_pnlMonContentsDefault.gridx = 0;
			gbc_pnlMonContentsDefault.gridy = 1;
			pnlShowContentsDefault.add(pnlMonContentsDefault, gbc_pnlMonContentsDefault);
			pnlMonContentsDefault.setLayout(new GridLayout(1, 0, 0, 0));
			
			scMonContentsDefault = new JScrollPane();
			scMonContentsDefault.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			pnlMonContentsDefault.add(scMonContentsDefault);
			
			epMonText = new JEditorPane("text/html", "UTF-8");
			epMonText.setForeground(Settings.font);
			epMonText.setBackground(new Color(255, 255, 240));
			epMonText.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent hle) {
                    if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                        try {
                        	Desktop.getDesktop().browse(new java.net.URI(hle.getURL().toString()));
                        }
                        catch(IOException e1) {
                        	e1.printStackTrace();
                        }
                        catch(URISyntaxException e1) {
                        	e1.printStackTrace();
                        }
                    }
                }
			});
			epMonText.setFont(new Font("굴림", Font.PLAIN, 9));
			scMonContentsDefault.setViewportView(epMonText);
			
			pnlMonImageDefault = new JPanel();
			pnlMonImageDefault.setBackground(Settings.background);
			scMonContentsDefault.setRowHeaderView(pnlMonImageDefault);
			pnlMonImageDefault.setLayout(new BorderLayout(0, 0));
			
			btnMonImageViewDefault = new JButton("");
			btnMonImageViewDefault.setBackground(new Color(255, 255, 255));
			btnMonImageViewDefault.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 try {
						 Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[1][imagenum[1]]));
					 }
					 catch(IOException e1) {
						 e1.printStackTrace();
					 }
					 catch(URISyntaxException e1) {
						 e1.printStackTrace();
					 }
				}
			});
			btnMonImageViewDefault.setSize(120, 100);
			btnMonImageViewDefault.setMargin(new Insets(0, 0, 0, 0));
			btnMonImageViewDefault.setForeground(Color.WHITE);
			pnlMonImageDefault.add(btnMonImageViewDefault, BorderLayout.CENTER);
			
			pnlMonImageControl = new JPanel();
			pnlMonImageControl.setBackground(Settings.background);
			pnlMonImageDefault.add(pnlMonImageControl, BorderLayout.EAST);
			GridBagLayout gbl_pnlMonImageControl = new GridBagLayout();
			gbl_pnlMonImageControl.columnWeights = new double[]{0.0};
			gbl_pnlMonImageControl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
			pnlMonImageControl.setLayout(gbl_pnlMonImageControl);
			
			btnMonImageAdd = new JButton("+");
			btnMonImageAdd.setForeground(Settings.font);
			btnMonImageAdd.setBackground(Settings.button);
			btnMonImageAdd.setToolTipText("\uC774\uBBF8\uC9C0\uB97C \uCD94\uAC00\uD569\uB2C8\uB2E4.");
			btnMonImageAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Main.openframeImageContainer(DateManager.dayofweekCalendar("monCal"));
					
				}
			});
			btnMonImageAdd.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnMonImageAdd = new GridBagConstraints();
			gbc_btnMonImageAdd.fill = GridBagConstraints.BOTH;
			gbc_btnMonImageAdd.insets = new Insets(0, 0, 0, 0);
			gbc_btnMonImageAdd.gridx = 0;
			gbc_btnMonImageAdd.gridy = 0;
			pnlMonImageControl.add(btnMonImageAdd, gbc_btnMonImageAdd);
			
			btnMonImageDel = new JButton("-");
			btnMonImageDel.setForeground(Settings.font);
			btnMonImageDel.setBackground(Settings.button);
			btnMonImageDel.setToolTipText("\uD574\uB2F9 \uC774\uBBF8\uC9C0\uB97C \uC0AD\uC81C\uD569\uB2C8\uB2E4.");
			btnMonImageDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(DataManager.dayofweekICnum[1]!=0){
						DataManager.fixDecodedImage("del", DateManager.dayofweekCalendar("monCal"), DataManager.dayofweekImagename[1][imagenum[1]], DataManager.dayofweekURL[1][imagenum[1]]);
						if(DataManager.onlinemode)
							ClientManager.connect(new CarrierFromClient("DEL", DataManager.serialCode, DataManager.decodedImage));
						else
							DataManager.saveIndex('i');
						
						weekContentsChanger();
					}
				}
			});
			btnMonImageDel.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnMonImageDel = new GridBagConstraints();
			gbc_btnMonImageDel.fill = GridBagConstraints.BOTH;
			gbc_btnMonImageDel.insets = new Insets(0, 0, 0, 0);
			gbc_btnMonImageDel.gridx = 0;
			gbc_btnMonImageDel.gridy = 1;
			pnlMonImageControl.add(btnMonImageDel, gbc_btnMonImageDel);
			
			btnMonImagePass = new JButton("\u25B2");
			btnMonImagePass.setForeground(Settings.font);
			btnMonImagePass.setBackground(Settings.button);
			btnMonImagePass.setToolTipText("\uC774\uC804 \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnMonImagePass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(imagenum[1]-1 >= 0){
						btnMonImageViewDefault.setIcon(dayImageChanger(1, false));
						imagenum[1]--;
					}
				}
			});
			btnMonImagePass.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnMonImagePass = new GridBagConstraints();
			gbc_btnMonImagePass.fill = GridBagConstraints.BOTH;
			gbc_btnMonImagePass.insets = new Insets(0, 0, 0, 0);
			gbc_btnMonImagePass.gridx = 0;
			gbc_btnMonImagePass.gridy = 2;
			pnlMonImageControl.add(btnMonImagePass, gbc_btnMonImagePass);
			
			btnMonImageReturn = new JButton("\u25BC");
			btnMonImageReturn.setForeground(Settings.font);
			btnMonImageReturn.setBackground(Settings.button);
			btnMonImageReturn.setToolTipText("\uB2E4\uC74C \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnMonImageReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[1]+1 <= DataManager.dayofweekICnum[1]-1){
						btnMonImageViewDefault.setIcon(dayImageChanger(1, true));
						imagenum[1]++;
					}
				}
			});
			btnMonImageReturn.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnMonImageReturn = new GridBagConstraints();
			gbc_btnMonImageReturn.fill = GridBagConstraints.BOTH;
			gbc_btnMonImageReturn.gridx = 0;
			gbc_btnMonImageReturn.gridy = 3;
			pnlMonImageControl.add(btnMonImageReturn, gbc_btnMonImageReturn);
			
			pnlTueContentsDefault = new JPanel();
			pnlTueContentsDefault.setPreferredSize(new Dimension(1000, lblSunDate.getHeight()));
			GridBagConstraints gbc_pnlTueContentsDefault = new GridBagConstraints();
			gbc_pnlTueContentsDefault.insets = new Insets(0, 0, 0, 0);
			gbc_pnlTueContentsDefault.fill = GridBagConstraints.BOTH;
			gbc_pnlTueContentsDefault.gridx = 0;
			gbc_pnlTueContentsDefault.gridy = 2;
			pnlShowContentsDefault.add(pnlTueContentsDefault, gbc_pnlTueContentsDefault);
			pnlTueContentsDefault.setLayout(new GridLayout(1, 0, 0, 0));
			
			scTueContentsDefault = new JScrollPane();
			scTueContentsDefault.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			pnlTueContentsDefault.add(scTueContentsDefault);
			
			epTueText = new JEditorPane("text/html", "UTF-8");
			epTueText.setForeground(Settings.font);
			epTueText.setBackground(new Color(255, 255, 240));
			epTueText.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent hle) {
                    if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                        try {
                        	Desktop.getDesktop().browse(new java.net.URI(hle.getURL().toString()));
                        }
                        catch(IOException e1) {
                        	e1.printStackTrace();
                        }
                        catch(URISyntaxException e1) {
                        	e1.printStackTrace();
                        }
                    }
                }
			});
			epTueText.setFont(new Font("굴림", Font.PLAIN, 9));
			scTueContentsDefault.setViewportView(epTueText);
			
			pnlTueImageDefault = new JPanel();
			pnlTueImageDefault.setBackground(Settings.background);
			scTueContentsDefault.setRowHeaderView(pnlTueImageDefault);
			pnlTueImageDefault.setLayout(new BorderLayout(0, 0));
			
			btnTueImageViewDefault = new JButton("");
			btnTueImageViewDefault.setBackground(new Color(255, 255, 255));
			btnTueImageViewDefault.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 try {
						 Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[2][imagenum[2]]));
					 }
					 catch(IOException e1) {
						 e1.printStackTrace();
					 }
					 catch(URISyntaxException e1) {
						 e1.printStackTrace();
					 }
				}
			});
			btnTueImageViewDefault.setSize(120, 100);
			btnTueImageViewDefault.setMargin(new Insets(0, 0, 0, 0));
			btnTueImageViewDefault.setForeground(Color.WHITE);
			pnlTueImageDefault.add(btnTueImageViewDefault, BorderLayout.CENTER);
			
			pnlTueImageControl = new JPanel();
			pnlTueImageControl.setBackground(Settings.background);
			pnlTueImageDefault.add(pnlTueImageControl, BorderLayout.EAST);
			GridBagLayout gbl_pnlTueImageControl = new GridBagLayout();
			gbl_pnlTueImageControl.columnWeights = new double[]{0.0};
			gbl_pnlTueImageControl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
			pnlTueImageControl.setLayout(gbl_pnlTueImageControl);
			
			btnTueImageAdd = new JButton("+");
			btnTueImageAdd.setForeground(Settings.font);
			btnTueImageAdd.setBackground(Settings.button);
			btnTueImageAdd.setToolTipText("\uC774\uBBF8\uC9C0\uB97C \uCD94\uAC00\uD569\uB2C8\uB2E4.");
			btnTueImageAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Main.openframeImageContainer(DateManager.dayofweekCalendar("tueCal"));
				}
			});
			btnTueImageAdd.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnTueImageAdd = new GridBagConstraints();
			gbc_btnTueImageAdd.fill = GridBagConstraints.BOTH;
			gbc_btnTueImageAdd.insets = new Insets(0, 0, 0, 0);
			gbc_btnTueImageAdd.gridx = 0;
			gbc_btnTueImageAdd.gridy = 0;
			pnlTueImageControl.add(btnTueImageAdd, gbc_btnTueImageAdd);
			
			btnTueImageDel = new JButton("-");
			btnTueImageDel.setForeground(Settings.font);
			btnTueImageDel.setBackground(Settings.button);
			btnTueImageDel.setToolTipText("\uD574\uB2F9 \uC774\uBBF8\uC9C0\uB97C \uC0AD\uC81C\uD569\uB2C8\uB2E4.");
			btnTueImageDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(DataManager.dayofweekICnum[2]!=0){
						DataManager.fixDecodedImage("del", DateManager.dayofweekCalendar("tueCal"), DataManager.dayofweekImagename[2][imagenum[2]], DataManager.dayofweekURL[2][imagenum[2]]);
						if(DataManager.onlinemode)
							ClientManager.connect(new CarrierFromClient("DEL", DataManager.serialCode, DataManager.decodedImage));
						else
							DataManager.saveIndex('i');
					
						weekContentsChanger();
					}
				}
			});
			btnTueImageDel.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnTueImageDel = new GridBagConstraints();
			gbc_btnTueImageDel.fill = GridBagConstraints.BOTH;
			gbc_btnTueImageDel.insets = new Insets(0, 0, 0, 0);
			gbc_btnTueImageDel.gridx = 0;
			gbc_btnTueImageDel.gridy = 1;
			pnlTueImageControl.add(btnTueImageDel, gbc_btnTueImageDel);
			
			btnTueImagePass = new JButton("\u25B2");
			btnTueImagePass.setForeground(Settings.font);
			btnTueImagePass.setBackground(Settings.button);
			btnTueImagePass.setToolTipText("\uC774\uC804 \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnTueImagePass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[2]-1 >= 0){
						btnTueImageViewDefault.setIcon(dayImageChanger(2, false));
						imagenum[2]--;
					}
				}
			});
			btnTueImagePass.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnTueImagePass = new GridBagConstraints();
			gbc_btnTueImagePass.fill = GridBagConstraints.BOTH;
			gbc_btnTueImagePass.insets = new Insets(0, 0, 0, 0);
			gbc_btnTueImagePass.gridx = 0;
			gbc_btnTueImagePass.gridy = 2;
			pnlTueImageControl.add(btnTueImagePass, gbc_btnTueImagePass);
			
			btnTueImageReturn = new JButton("\u25BC");
			btnTueImageReturn.setForeground(Settings.font);
			btnTueImageReturn.setBackground(Settings.button);
			btnTueImageReturn.setToolTipText("\uB2E4\uC74C \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnTueImageReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[2]+1 <= DataManager.dayofweekICnum[2]-1){
						btnTueImageViewDefault.setIcon(dayImageChanger(2, true));
						imagenum[2]++;
					}
				}
			});
			btnTueImageReturn.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnTueImageReturn = new GridBagConstraints();
			gbc_btnTueImageReturn.fill = GridBagConstraints.BOTH;
			gbc_btnTueImageReturn.gridx = 0;
			gbc_btnTueImageReturn.gridy = 3;
			pnlTueImageControl.add(btnTueImageReturn, gbc_btnTueImageReturn);
			
			pnlWedContentsDefault = new JPanel();
			pnlWedContentsDefault.setPreferredSize(new Dimension(1000, lblSunDate.getHeight()));
			GridBagConstraints gbc_pnlWedContentsDefault = new GridBagConstraints();
			gbc_pnlWedContentsDefault.insets = new Insets(0, 0, 0, 0);
			gbc_pnlWedContentsDefault.fill = GridBagConstraints.BOTH;
			gbc_pnlWedContentsDefault.gridx = 0;
			gbc_pnlWedContentsDefault.gridy = 3;
			pnlShowContentsDefault.add(pnlWedContentsDefault, gbc_pnlWedContentsDefault);
			pnlWedContentsDefault.setLayout(new GridLayout(1, 0, 0, 0));
			
			scWedContentsDefault = new JScrollPane();
			scWedContentsDefault.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			pnlWedContentsDefault.add(scWedContentsDefault);
			
			epWedText = new JEditorPane("text/html", "UTF-8");
			epWedText.setForeground(Settings.font);
			epWedText.setBackground(new Color(255, 255, 240));
			epWedText.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent hle) {
                    if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                        try {
                        	Desktop.getDesktop().browse(new java.net.URI(hle.getURL().toString()));
                        }
                        catch(IOException e1) {
                        	e1.printStackTrace();
                        }
                        catch(URISyntaxException e1) {
                        	e1.printStackTrace();
                        }
                    }
                }
			});
			epWedText.setFont(new Font("굴림", Font.PLAIN, 9));
			scWedContentsDefault.setViewportView(epWedText);
			
			pnlWedImageDefault = new JPanel();
			pnlWedImageDefault.setBackground(Settings.background);
			scWedContentsDefault.setRowHeaderView(pnlWedImageDefault);
			pnlWedImageDefault.setLayout(new BorderLayout(0, 0));
			
			btnWedImageViewDefault = new JButton("");
			btnWedImageViewDefault.setBackground(new Color(255, 255, 255));
			btnWedImageViewDefault.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 try {
						 Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[3][imagenum[3]]));
					 }
					 catch(IOException e1) {
						 e1.printStackTrace();
					 }
					 catch(URISyntaxException e1) {
						 e1.printStackTrace();
					 }
				}
			});
			btnWedImageViewDefault.setSize(120, 100);
			btnWedImageViewDefault.setMargin(new Insets(0, 0, 0, 0));
			btnWedImageViewDefault.setForeground(Color.WHITE);
			pnlWedImageDefault.add(btnWedImageViewDefault, BorderLayout.CENTER);
			
			pnlWedImageControl = new JPanel();
			pnlWedImageControl.setBackground(Settings.background);
			pnlWedImageDefault.add(pnlWedImageControl, BorderLayout.EAST);
			GridBagLayout gbl_pnlWedImageControl = new GridBagLayout();
			gbl_pnlWedImageControl.columnWeights = new double[]{0.0};
			gbl_pnlWedImageControl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
			pnlWedImageControl.setLayout(gbl_pnlWedImageControl);
			
			btnWedImageAdd = new JButton("+");
			btnWedImageAdd.setForeground(Settings.font);
			btnWedImageAdd.setBackground(Settings.button);
			btnWedImageAdd.setToolTipText("\uC774\uBBF8\uC9C0\uB97C \uCD94\uAC00\uD569\uB2C8\uB2E4.");
			btnWedImageAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Main.openframeImageContainer(DateManager.dayofweekCalendar("wedCal"));
				}
			});
			btnWedImageAdd.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnWedImageAdd = new GridBagConstraints();
			gbc_btnWedImageAdd.fill = GridBagConstraints.BOTH;
			gbc_btnWedImageAdd.insets = new Insets(0, 0, 0, 0);
			gbc_btnWedImageAdd.gridx = 0;
			gbc_btnWedImageAdd.gridy = 0;
			pnlWedImageControl.add(btnWedImageAdd, gbc_btnWedImageAdd);
			
			btnWedImageDel = new JButton("-");
			btnWedImageDel.setForeground(Settings.font);
			btnWedImageDel.setBackground(Settings.button);
			btnWedImageDel.setToolTipText("\uD574\uB2F9 \uC774\uBBF8\uC9C0\uB97C \uC0AD\uC81C\uD569\uB2C8\uB2E4.");
			btnWedImageDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(DataManager.dayofweekICnum[3]!=0){
						DataManager.fixDecodedImage("del", DateManager.dayofweekCalendar("wedCal"), DataManager.dayofweekImagename[3][imagenum[3]], DataManager.dayofweekURL[3][imagenum[3]]);
						if(DataManager.onlinemode)
							ClientManager.connect(new CarrierFromClient("DEL", DataManager.serialCode, DataManager.decodedImage));
						else
							DataManager.saveIndex('i');
					
						weekContentsChanger();
					}
				}
			});
			btnWedImageDel.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnWedImageDel = new GridBagConstraints();
			gbc_btnWedImageDel.fill = GridBagConstraints.BOTH;
			gbc_btnWedImageDel.insets = new Insets(0, 0, 0, 0);
			gbc_btnWedImageDel.gridx = 0;
			gbc_btnWedImageDel.gridy = 1;
			pnlWedImageControl.add(btnWedImageDel, gbc_btnWedImageDel);
			
			btnWedImagePass = new JButton("\u25B2");
			btnWedImagePass.setForeground(Settings.font);
			btnWedImagePass.setBackground(Settings.button);
			btnWedImagePass.setToolTipText("\uC774\uC804 \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnWedImagePass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[3]-1 >= 0){
						btnWedImageViewDefault.setIcon(dayImageChanger(3, false));
						imagenum[3]--;
					}
				}
			});
			btnWedImagePass.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnWedImagePass = new GridBagConstraints();
			gbc_btnWedImagePass.fill = GridBagConstraints.BOTH;
			gbc_btnWedImagePass.insets = new Insets(0, 0, 0, 0);
			gbc_btnWedImagePass.gridx = 0;
			gbc_btnWedImagePass.gridy = 2;
			pnlWedImageControl.add(btnWedImagePass, gbc_btnWedImagePass);
			
			btnWedImageReturn = new JButton("\u25BC");
			btnWedImageReturn.setForeground(Settings.font);
			btnWedImageReturn.setBackground(Settings.button);
			btnWedImageReturn.setToolTipText("\uB2E4\uC74C \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnWedImageReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[3]+1 <= DataManager.dayofweekICnum[3]-1){
						btnWedImageViewDefault.setIcon(dayImageChanger(3, true));
						imagenum[3]++;
					}
				}
			});
			btnWedImageReturn.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnWedImageReturn = new GridBagConstraints();
			gbc_btnWedImageReturn.fill = GridBagConstraints.BOTH;
			gbc_btnWedImageReturn.gridx = 0;
			gbc_btnWedImageReturn.gridy = 3;
			pnlWedImageControl.add(btnWedImageReturn, gbc_btnWedImageReturn);
			
			pnlThuContentsDefault = new JPanel();
			pnlThuContentsDefault.setPreferredSize(new Dimension(1000, lblSunDate.getHeight()));
			GridBagConstraints gbc_pnlThuContentsDefault = new GridBagConstraints();
			gbc_pnlThuContentsDefault.insets = new Insets(0, 0, 0, 0);
			gbc_pnlThuContentsDefault.fill = GridBagConstraints.BOTH;
			gbc_pnlThuContentsDefault.gridx = 0;
			gbc_pnlThuContentsDefault.gridy = 4;
			pnlShowContentsDefault.add(pnlThuContentsDefault, gbc_pnlThuContentsDefault);
			pnlThuContentsDefault.setLayout(new GridLayout(1, 0, 0, 0));
			
			scThuContentsDefault = new JScrollPane();
			scThuContentsDefault.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			pnlThuContentsDefault.add(scThuContentsDefault);
			
			epThuText = new JEditorPane("text/html", "UTF-8");
			epThuText.setForeground(Settings.font);
			epThuText.setBackground(new Color(255, 255, 240));
			epThuText.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent hle) {
                    if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                        try {
                        	
                        	Desktop.getDesktop().browse(new java.net.URI(hle.getURL().toString()));
                        }
                        catch(IOException e1) {
                        	e1.printStackTrace();
                        }
                        catch(URISyntaxException e1) {
                        	e1.printStackTrace();
                        }
                    }
                }
			});
			epThuText.setFont(new Font("굴림", Font.PLAIN, 9));
			scThuContentsDefault.setViewportView(epThuText);
			
			pnlThuImageDefault = new JPanel();
			pnlThuImageDefault.setBackground(Settings.background);
			scThuContentsDefault.setRowHeaderView(pnlThuImageDefault);
			pnlThuImageDefault.setLayout(new BorderLayout(0, 0));
			
			btnThuImageViewDefault = new JButton("");
			btnThuImageViewDefault.setBackground(new Color(255, 255, 255));
			btnThuImageViewDefault.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 try {
						 Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[4][imagenum[4]]));
					 }
					 catch(IOException e1) {
						 e1.printStackTrace();
					 }
					 catch(URISyntaxException e1) {
						 e1.printStackTrace();
					 }
				}
			});
			btnThuImageViewDefault.setSize(120, 100);
			btnThuImageViewDefault.setMargin(new Insets(0, 0, 0, 0));
			btnThuImageViewDefault.setForeground(Color.WHITE);
			pnlThuImageDefault.add(btnThuImageViewDefault, BorderLayout.CENTER);
			
			pnlThuImageControl = new JPanel();
			pnlThuImageControl.setBackground(Settings.background);
			pnlThuImageDefault.add(pnlThuImageControl, BorderLayout.EAST);
			GridBagLayout gbl_pnlThuImageControl = new GridBagLayout();
			gbl_pnlThuImageControl.columnWeights = new double[]{0.0};
			gbl_pnlThuImageControl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
			pnlThuImageControl.setLayout(gbl_pnlThuImageControl);
			
			btnThuImageAdd = new JButton("+");
			btnThuImageAdd.setForeground(Settings.font);
			btnThuImageAdd.setBackground(Settings.button);
			btnThuImageAdd.setToolTipText("\uC774\uBBF8\uC9C0\uB97C \uCD94\uAC00\uD569\uB2C8\uB2E4.");
			btnThuImageAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Main.openframeImageContainer(DateManager.dayofweekCalendar("thuCal"));
				}
			});
			btnThuImageAdd.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnThuImageAdd = new GridBagConstraints();
			gbc_btnThuImageAdd.fill = GridBagConstraints.BOTH;
			gbc_btnThuImageAdd.insets = new Insets(0, 0, 0, 0);
			gbc_btnThuImageAdd.gridx = 0;
			gbc_btnThuImageAdd.gridy = 0;
			pnlThuImageControl.add(btnThuImageAdd, gbc_btnThuImageAdd);
			
			btnThuImageDel = new JButton("-");
			btnThuImageDel.setForeground(Settings.font);
			btnThuImageDel.setBackground(Settings.button);
			btnThuImageDel.setToolTipText("\uD574\uB2F9 \uC774\uBBF8\uC9C0\uB97C \uC0AD\uC81C\uD569\uB2C8\uB2E4.");
			btnThuImageDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(DataManager.dayofweekICnum[4]!=0){
						DataManager.fixDecodedImage("del", DateManager.dayofweekCalendar("thuCal"), DataManager.dayofweekImagename[4][imagenum[4]], DataManager.dayofweekURL[4][imagenum[4]]);
						if(DataManager.onlinemode)
							ClientManager.connect(new CarrierFromClient("DEL", DataManager.serialCode, DataManager.decodedImage));
						else
							DataManager.saveIndex('i');
					
					weekContentsChanger();
					}
				}
			});
			btnThuImageDel.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnThuImageDel = new GridBagConstraints();
			gbc_btnThuImageDel.fill = GridBagConstraints.BOTH;
			gbc_btnThuImageDel.insets = new Insets(0, 0, 0, 0);
			gbc_btnThuImageDel.gridx = 0;
			gbc_btnThuImageDel.gridy = 1;
			pnlThuImageControl.add(btnThuImageDel, gbc_btnThuImageDel);
			
			btnThuImagePass = new JButton("\u25B2");
			btnThuImagePass.setForeground(Settings.font);
			btnThuImagePass.setBackground(Settings.button);
			btnThuImagePass.setToolTipText("\uC774\uC804 \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnThuImagePass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[4]-1 >= 0){
						btnThuImageViewDefault.setIcon(dayImageChanger(4, false));
						imagenum[4]--;
					}
				}
			});
			btnThuImagePass.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnThuImagePass = new GridBagConstraints();
			gbc_btnThuImagePass.fill = GridBagConstraints.BOTH;
			gbc_btnThuImagePass.insets = new Insets(0, 0, 0, 0);
			gbc_btnThuImagePass.gridx = 0;
			gbc_btnThuImagePass.gridy = 2;
			pnlThuImageControl.add(btnThuImagePass, gbc_btnThuImagePass);
			
			btnThuImageReturn = new JButton("\u25BC");
			btnThuImageReturn.setForeground(Settings.font);
			btnThuImageReturn.setBackground(Settings.button);
			btnThuImageReturn.setToolTipText("\uB2E4\uC74C \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnThuImageReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[4]+1 <= DataManager.dayofweekICnum[4]-1){
						btnThuImageViewDefault.setIcon(dayImageChanger(4, true));
						imagenum[4]++;
					}
				}
			});
			btnThuImageReturn.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnThuImageReturn = new GridBagConstraints();
			gbc_btnThuImageReturn.fill = GridBagConstraints.BOTH;
			gbc_btnThuImageReturn.gridx = 0;
			gbc_btnThuImageReturn.gridy = 3;
			pnlThuImageControl.add(btnThuImageReturn, gbc_btnThuImageReturn);
			
			pnlFriContentsDefault = new JPanel();
			pnlFriContentsDefault.setPreferredSize(new Dimension(1000, lblSunDate.getHeight()));
			GridBagConstraints gbc_pnlFriContentsDefault = new GridBagConstraints();
			gbc_pnlFriContentsDefault.insets = new Insets(0, 0, 0, 0);
			gbc_pnlFriContentsDefault.fill = GridBagConstraints.BOTH;
			gbc_pnlFriContentsDefault.gridx = 0;
			gbc_pnlFriContentsDefault.gridy = 5;
			pnlShowContentsDefault.add(pnlFriContentsDefault, gbc_pnlFriContentsDefault);
			pnlFriContentsDefault.setLayout(new GridLayout(1, 0, 0, 0));
			
			scFriContentsDefault = new JScrollPane();
			scFriContentsDefault.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			pnlFriContentsDefault.add(scFriContentsDefault);
			
			epFriText = new JEditorPane("text/html", "UTF-8");
			epFriText.setForeground(Settings.font);
			epFriText.setBackground(new Color(255, 255, 240));
			epFriText.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent hle) {
                    if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                        try {
                        	
                        	Desktop.getDesktop().browse(new java.net.URI(hle.getURL().toString()));
                        }
                        catch(IOException e1) {
                        	e1.printStackTrace();
                        }
                        catch(URISyntaxException e1) {
                        	e1.printStackTrace();
                        }
                    }
                }
			});
			epFriText.setFont(new Font("굴림", Font.PLAIN, 9));
			scFriContentsDefault.setViewportView(epFriText);
			
			pnlFriImageDefault = new JPanel();
			pnlFriImageDefault.setBackground(Settings.background);
			scFriContentsDefault.setRowHeaderView(pnlFriImageDefault);
			pnlFriImageDefault.setLayout(new BorderLayout(0, 0));
			
			btnFriImageViewDefault = new JButton("");
			btnFriImageViewDefault.setBackground(new Color(255, 255, 255));
			btnFriImageViewDefault.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 try {
						 Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[5][imagenum[5]]));
					 }
					 catch(IOException e1) {
						 e1.printStackTrace();
					 }
					 catch(URISyntaxException e1) {
						 e1.printStackTrace();
					 }
				}
			});
			btnFriImageViewDefault.setSize(120, 100);
			btnFriImageViewDefault.setMargin(new Insets(0, 0, 0, 0));
			btnFriImageViewDefault.setForeground(Color.WHITE);
			pnlFriImageDefault.add(btnFriImageViewDefault, BorderLayout.CENTER);
			
			pnlFriImageControl = new JPanel();
			pnlFriImageControl.setBackground(Settings.background);
			pnlFriImageDefault.add(pnlFriImageControl, BorderLayout.EAST);
			GridBagLayout gbl_pnlFriImageControl = new GridBagLayout();
			gbl_pnlFriImageControl.columnWeights = new double[]{0.0};
			gbl_pnlFriImageControl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
			pnlFriImageControl.setLayout(gbl_pnlFriImageControl);
			
			btnFriImageAdd = new JButton("+");
			btnFriImageAdd.setForeground(Settings.font);
			btnFriImageAdd.setBackground(Settings.button);
			btnFriImageAdd.setToolTipText("\uC774\uBBF8\uC9C0\uB97C \uCD94\uAC00\uD569\uB2C8\uB2E4.");
			btnFriImageAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Main.openframeImageContainer(DateManager.dayofweekCalendar("friCal"));
				}
			});
			btnFriImageAdd.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnFriImageAdd = new GridBagConstraints();
			gbc_btnFriImageAdd.fill = GridBagConstraints.BOTH;
			gbc_btnFriImageAdd.insets = new Insets(0, 0, 0, 0);
			gbc_btnFriImageAdd.gridx = 0;
			gbc_btnFriImageAdd.gridy = 0;
			pnlFriImageControl.add(btnFriImageAdd, gbc_btnFriImageAdd);
			
			btnFriImageDel = new JButton("-");
			btnFriImageDel.setForeground(Settings.font);
			btnFriImageDel.setBackground(Settings.button);
			btnFriImageDel.setToolTipText("\uD574\uB2F9 \uC774\uBBF8\uC9C0\uB97C \uC0AD\uC81C\uD569\uB2C8\uB2E4.");
			btnFriImageDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(DataManager.dayofweekICnum[5]!=0){
						DataManager.fixDecodedImage("del", DateManager.dayofweekCalendar("friCal"), DataManager.dayofweekImagename[5][imagenum[5]], DataManager.dayofweekURL[5][imagenum[5]]);
						if(DataManager.onlinemode)
							ClientManager.connect(new CarrierFromClient("DEL", DataManager.serialCode, DataManager.decodedImage));
						else
							DataManager.saveIndex('i');
					
						weekContentsChanger();
					}
				}
			});
			btnFriImageDel.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnFriImageDel = new GridBagConstraints();
			gbc_btnFriImageDel.fill = GridBagConstraints.BOTH;
			gbc_btnFriImageDel.insets = new Insets(0, 0, 0, 0);
			gbc_btnFriImageDel.gridx = 0;
			gbc_btnFriImageDel.gridy = 1;
			pnlFriImageControl.add(btnFriImageDel, gbc_btnFriImageDel);
			
			btnFriImagePass = new JButton("\u25B2");
			btnFriImagePass.setForeground(Settings.font);
			btnFriImagePass.setBackground(Settings.button);
			btnFriImagePass.setToolTipText("\uC774\uC804 \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnFriImagePass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[5]-1 >= 0){
						btnFriImageViewDefault.setIcon(dayImageChanger(5, false));
						imagenum[5]--;
					}
				}
			});
			btnFriImagePass.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnFriImagePass = new GridBagConstraints();
			gbc_btnFriImagePass.fill = GridBagConstraints.BOTH;
			gbc_btnFriImagePass.insets = new Insets(0, 0, 0, 0);
			gbc_btnFriImagePass.gridx = 0;
			gbc_btnFriImagePass.gridy = 2;
			pnlFriImageControl.add(btnFriImagePass, gbc_btnFriImagePass);
			
			btnFriImageReturn = new JButton("\u25BC");
			btnFriImageReturn.setForeground(Settings.font);
			btnFriImageReturn.setBackground(Settings.button);
			btnFriImageReturn.setToolTipText("\uB2E4\uC74C \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnFriImageReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[5]+1 <= DataManager.dayofweekICnum[5]-1){
						btnFriImageViewDefault.setIcon(dayImageChanger(5, true));
						imagenum[5]++;
					}
				}
			});
			btnFriImageReturn.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnFriImageReturn = new GridBagConstraints();
			gbc_btnFriImageReturn.fill = GridBagConstraints.BOTH;
			gbc_btnFriImageReturn.gridx = 0;
			gbc_btnFriImageReturn.gridy = 3;
			pnlFriImageControl.add(btnFriImageReturn, gbc_btnFriImageReturn);
			
			pnlSatContentsDefault = new JPanel();
			pnlSatContentsDefault.setPreferredSize(new Dimension(1000, lblSunDate.getHeight()));
			GridBagConstraints gbc_pnlSatContentsDefault = new GridBagConstraints();
			gbc_pnlSatContentsDefault.insets = new Insets(0, 0, 0, 0);
			gbc_pnlSatContentsDefault.fill = GridBagConstraints.BOTH;
			gbc_pnlSatContentsDefault.gridx = 0;
			gbc_pnlSatContentsDefault.gridy = 6;
			pnlShowContentsDefault.add(pnlSatContentsDefault, gbc_pnlSatContentsDefault);
			pnlSatContentsDefault.setLayout(new GridLayout(1, 0, 0, 0));
			
			scSatContentsDefault = new JScrollPane();
			scSatContentsDefault.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			pnlSatContentsDefault.add(scSatContentsDefault);
			
			epSatText = new JEditorPane("text/html", "UTF-8");
			epSatText.setForeground(Settings.font);
			epSatText.setBackground(new Color(255, 255, 240));
			epSatText.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent hle) {
                    if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                        try {
                        	Desktop.getDesktop().browse(new java.net.URI(hle.getURL().toString()));
                        }
                        catch(IOException e1) {
                        	e1.printStackTrace();
                        }
                        catch(URISyntaxException e1) {
                        	e1.printStackTrace();
                        }
                    }
                }
			});
			epSatText.setFont(new Font("굴림", Font.PLAIN, 9));
			scSatContentsDefault.setViewportView(epSatText);
			
			pnlSatImageDefault = new JPanel();
			pnlSatImageDefault.setBackground(Settings.background);
			scSatContentsDefault.setRowHeaderView(pnlSatImageDefault);
			pnlSatImageDefault.setLayout(new BorderLayout(0, 0));
			
			btnSatImageViewDefault = new JButton("");
			btnSatImageViewDefault.setBackground(new Color(255, 255, 255));
			btnSatImageViewDefault.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 try {
						 Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[6][imagenum[6]]));
					 }
					 catch(IOException e1) {
						 e1.printStackTrace();
					 }
					 catch(URISyntaxException e1) {
						 e1.printStackTrace();
					 }
				}
			});
			btnSatImageViewDefault.setSize(120, 100);
			btnSatImageViewDefault.setMargin(new Insets(0, 0, 0, 0));
			btnSatImageViewDefault.setForeground(Color.WHITE);
			pnlSatImageDefault.add(btnSatImageViewDefault, BorderLayout.CENTER);
			
			pnlSatImageControl = new JPanel();
			pnlSatImageControl.setBackground(Settings.background);
			pnlSatImageDefault.add(pnlSatImageControl, BorderLayout.EAST);
			GridBagLayout gbl_pnlSatImageControl = new GridBagLayout();
			gbl_pnlSatImageControl.columnWeights = new double[]{0.0};
			gbl_pnlSatImageControl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
			pnlSatImageControl.setLayout(gbl_pnlSatImageControl);
			
			btnSatImageAdd = new JButton("+");
			btnSatImageAdd.setForeground(Settings.font);
			btnSatImageAdd.setBackground(Settings.button);
			btnSatImageAdd.setToolTipText("\uC774\uBBF8\uC9C0\uB97C \uCD94\uAC00\uD569\uB2C8\uB2E4.");
			btnSatImageAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Main.openframeImageContainer(DateManager.dayofweekCalendar("satCal"));
				}
			});
			btnSatImageAdd.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnSatImageAdd = new GridBagConstraints();
			gbc_btnSatImageAdd.fill = GridBagConstraints.BOTH;
			gbc_btnSatImageAdd.insets = new Insets(0, 0, 0, 0);
			gbc_btnSatImageAdd.gridx = 0;
			gbc_btnSatImageAdd.gridy = 0;
			pnlSatImageControl.add(btnSatImageAdd, gbc_btnSatImageAdd);
			
			btnSatImageDel = new JButton("-");
			btnSatImageDel.setForeground(Settings.font);
			btnSatImageDel.setBackground(Settings.button);
			btnSatImageDel.setToolTipText("\uD574\uB2F9 \uC774\uBBF8\uC9C0\uB97C \uC0AD\uC81C\uD569\uB2C8\uB2E4.");
			btnSatImageDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(DataManager.dayofweekICnum[6]!=0){
						DataManager.fixDecodedImage("del", DateManager.dayofweekCalendar("satCal"), DataManager.dayofweekImagename[6][imagenum[6]], DataManager.dayofweekURL[6][imagenum[6]]);
						if(DataManager.onlinemode)
							ClientManager.connect(new CarrierFromClient("DEL", DataManager.serialCode, DataManager.decodedImage));
						else
							DataManager.saveIndex('i');
					
						weekContentsChanger();
					}
				}
			});
			btnSatImageDel.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnSatImageDel = new GridBagConstraints();
			gbc_btnSatImageDel.fill = GridBagConstraints.BOTH;
			gbc_btnSatImageDel.insets = new Insets(0, 0, 0, 0);
			gbc_btnSatImageDel.gridx = 0;
			gbc_btnSatImageDel.gridy = 1;
			pnlSatImageControl.add(btnSatImageDel, gbc_btnSatImageDel);
			
			btnSatImagePass = new JButton("\u25B2");
			btnSatImagePass.setForeground(Settings.font);
			btnSatImagePass.setBackground(Settings.button);
			btnSatImagePass.setToolTipText("\uC774\uC804 \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnSatImagePass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[6]-1 >= 0){
						btnSatImageViewDefault.setIcon(dayImageChanger(6, false));
						imagenum[6]--;
					}
				}
			});
			btnSatImagePass.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnSatImagePass = new GridBagConstraints();
			gbc_btnSatImagePass.fill = GridBagConstraints.BOTH;
			gbc_btnSatImagePass.insets = new Insets(0, 0, 0, 0);
			gbc_btnSatImagePass.gridx = 0;
			gbc_btnSatImagePass.gridy = 2;
			pnlSatImageControl.add(btnSatImagePass, gbc_btnSatImagePass);
			
			btnSatImageReturn = new JButton("\u25BC");
			btnSatImageReturn.setForeground(Settings.font);
			btnSatImageReturn.setBackground(Settings.button);
			btnSatImageReturn.setToolTipText("\uB2E4\uC74C \uC774\uBBF8\uC9C0\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4.");
			btnSatImageReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(imagenum[6]+1 <= DataManager.dayofweekICnum[6]-1){
						btnSatImageViewDefault.setIcon(dayImageChanger(6, true));
						imagenum[6]++;
					}
				}
			});
			btnSatImageReturn.setFont(new Font("굴림", Font.PLAIN, 10));
			GridBagConstraints gbc_btnSatImageReturn = new GridBagConstraints();
			gbc_btnSatImageReturn.fill = GridBagConstraints.BOTH;
			gbc_btnSatImageReturn.gridx = 0;
			gbc_btnSatImageReturn.gridy = 3;
			pnlSatImageControl.add(btnSatImageReturn, gbc_btnSatImageReturn);
			//ShowContentsDefault 패널 End
		
			//ShowContentsImageMode 패널 Start
			pnlShowContentsImageMode = new JPanel();
			pnlShowContentsImageMode.setBackground(Settings.background);
			pnlShowContents.add(pnlShowContentsImageMode);
			GridBagLayout gbl_pnlShowContentsImageMode = new GridBagLayout();
			gbl_pnlShowContentsImageMode.columnWeights = new double[]{1.0, 1.0};
			gbl_pnlShowContentsImageMode.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0};
			pnlShowContentsImageMode.setLayout(gbl_pnlShowContentsImageMode);
			pnlShowContentsImageMode.setVisible(false);
			
			if(!DataManager.onlinemode){
				pnlImageModeControl = new JPanel();
				pnlImageModeControl.setBackground(Settings.background);
				GridBagConstraints gbc_pnlImageModeControl = new GridBagConstraints();
				gbc_pnlImageModeControl.gridwidth = 2;
				gbc_pnlImageModeControl.insets = new Insets(0, 0, 5, 5);
				gbc_pnlImageModeControl.fill = GridBagConstraints.BOTH;
				gbc_pnlImageModeControl.gridx = 0;
				gbc_pnlImageModeControl.gridy = 0;
				pnlShowContentsImageMode.add(pnlImageModeControl, gbc_pnlImageModeControl);
				pnlImageModeControl.setLayout(new BorderLayout(0, 0));
	
				pnlImageModeControl2 = new JPanel();
				FlowLayout fl_pnlImageModeControl2 = (FlowLayout) pnlImageModeControl2.getLayout();
				fl_pnlImageModeControl2.setVgap(0);
				fl_pnlImageModeControl2.setHgap(0);
				pnlImageModeControl.add(pnlImageModeControl2, BorderLayout.EAST);
	
	
	
				btnLast8Images = new JButton("Last 8 imgs");
				btnLast8Images.setBackground(Settings.button);
				btnLast8Images.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if((imageWave/8) >= 1){
							System.out.print(imageWave);
							imageWave-=8;
						}
						dayContentsModeChanger(true);
					}
				});
				pnlImageModeControl2.add(btnLast8Images);
				btnNext8Images = new JButton("Next 8 imgs");
				btnNext8Images.setBackground(Settings.button);
				btnNext8Images.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if((DataManager.dayofweekICnum[dayofweekImageMode]-(7+imageWave))>0){
							imageWave+=8;
						}
						dayContentsModeChanger(true);
					}
				});
				pnlImageModeControl2.add(btnNext8Images);
			}
			
			btnImageModeImage1 = new JButton("");
			btnImageModeImage1.setBackground(new Color(255, 255, 255));
			btnImageModeImage1.setMargin(new Insets(0,0,0,0));
			btnImageModeImage1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(DataManager.dayofweekICnum[dayofweekImageMode]-imageWave>=1){
							Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[dayofweekImageMode][0+imageWave]));
						}
					}
					 catch(IOException e1) {
						 e1.printStackTrace();
					 }
					 catch(URISyntaxException e1) {
						 e1.printStackTrace();
					 }
				}
			});
			
			GridBagConstraints gbc_btnImageModeImage1 = new GridBagConstraints();
			gbc_btnImageModeImage1.fill = GridBagConstraints.BOTH;
			gbc_btnImageModeImage1.insets = new Insets(0, 0, 5, 2);
			gbc_btnImageModeImage1.gridx = 0;
			gbc_btnImageModeImage1.gridy = 1;
			pnlShowContentsImageMode.add(btnImageModeImage1, gbc_btnImageModeImage1);
			
			btnImageModeImage2 = new JButton("");
			btnImageModeImage2.setBackground(new Color(255, 255, 255));
			btnImageModeImage2.setMargin(new Insets(0, 0, 0, 0));
			btnImageModeImage2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(DataManager.dayofweekICnum[dayofweekImageMode]-imageWave>=2){
							Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[dayofweekImageMode][1+imageWave]));
						}
					}
					catch(IOException e1) {
						e1.printStackTrace();
					}
					catch(URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			});
			GridBagConstraints gbc_btnImageModeImage2 = new GridBagConstraints();
			gbc_btnImageModeImage2.fill = GridBagConstraints.BOTH;
			gbc_btnImageModeImage2.insets = new Insets(0, 2, 5, 0);
			gbc_btnImageModeImage2.gridx = 1;
			gbc_btnImageModeImage2.gridy = 1;
			pnlShowContentsImageMode.add(btnImageModeImage2, gbc_btnImageModeImage2);
			
			btnImageModeImage3 = new JButton("");
			btnImageModeImage3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(DataManager.dayofweekICnum[dayofweekImageMode]-imageWave>=3){
							Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[dayofweekImageMode][2+imageWave]));
						}
					}
					catch(IOException e1) {
						e1.printStackTrace();
					}
					catch(URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnImageModeImage3.setBackground(new Color(255, 255, 255));
			btnImageModeImage3.setMargin(new Insets(0, 0, 0, 0));
			GridBagConstraints gbc_btnImageModeImage3 = new GridBagConstraints();
			gbc_btnImageModeImage3.fill = GridBagConstraints.BOTH;
			gbc_btnImageModeImage3.insets = new Insets(0, 0, 5, 2);
			gbc_btnImageModeImage3.gridx = 0;
			gbc_btnImageModeImage3.gridy = 2;
			pnlShowContentsImageMode.add(btnImageModeImage3, gbc_btnImageModeImage3);
			
			btnImageModeImage4 = new JButton("");
			btnImageModeImage4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(DataManager.dayofweekICnum[dayofweekImageMode]-imageWave>=4){
							Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[dayofweekImageMode][3+imageWave]));
						}
					}
					catch(IOException e1) {
						e1.printStackTrace();
					}
					catch(URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnImageModeImage4.setBackground(new Color(255, 255, 255));
			btnImageModeImage4.setMargin(new Insets(0, 0, 0, 0));
			GridBagConstraints gbc_btnImageModeImage4 = new GridBagConstraints();
			gbc_btnImageModeImage4.fill = GridBagConstraints.BOTH;
			gbc_btnImageModeImage4.insets = new Insets(0, 2, 5, 0);
			gbc_btnImageModeImage4.gridx = 1;
			gbc_btnImageModeImage4.gridy = 2;
			pnlShowContentsImageMode.add(btnImageModeImage4, gbc_btnImageModeImage4);
			
			btnImageModeImage5 = new JButton("");
			btnImageModeImage5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(DataManager.dayofweekICnum[dayofweekImageMode]-imageWave>=5){
							Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[dayofweekImageMode][4+imageWave]));
						}
					}
					catch(IOException e1) {
						e1.printStackTrace();
					}
					catch(URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnImageModeImage5.setBackground(new Color(255, 255, 255));
			btnImageModeImage5.setMargin(new Insets(0, 0, 0, 0));
			GridBagConstraints gbc_btnImageModeImage5 = new GridBagConstraints();
			gbc_btnImageModeImage5.fill = GridBagConstraints.BOTH;
			gbc_btnImageModeImage5.insets = new Insets(0, 0, 5, 2);
			gbc_btnImageModeImage5.gridx = 0;
			gbc_btnImageModeImage5.gridy = 3;
			pnlShowContentsImageMode.add(btnImageModeImage5, gbc_btnImageModeImage5);
			
			btnImageModeImage6 = new JButton("");
			btnImageModeImage6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(DataManager.dayofweekICnum[dayofweekImageMode]-imageWave>=6){
							Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[dayofweekImageMode][5+imageWave]));
						}
					}
					catch(IOException e1) {
						e1.printStackTrace();
					}
					catch(URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnImageModeImage6.setBackground(new Color(255, 255, 255));
			btnImageModeImage6.setMargin(new Insets(0, 0, 0, 0));
			GridBagConstraints gbc_btnImageModeImage6 = new GridBagConstraints();
			gbc_btnImageModeImage6.fill = GridBagConstraints.BOTH;
			gbc_btnImageModeImage6.insets = new Insets(0, 2, 5, 0);
			gbc_btnImageModeImage6.gridx = 1;
			gbc_btnImageModeImage6.gridy = 3;
			pnlShowContentsImageMode.add(btnImageModeImage6, gbc_btnImageModeImage6);
			
			btnImageModeImage7 = new JButton("");
			btnImageModeImage7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(DataManager.dayofweekICnum[dayofweekImageMode]-imageWave>=7){
							Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[dayofweekImageMode][6+imageWave]));
						}
					}
					catch(IOException e1) {
						e1.printStackTrace();
					}
					catch(URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnImageModeImage7.setBackground(new Color(255, 255, 255));
			btnImageModeImage7.setMargin(new Insets(0, 0, 0, 0));
			GridBagConstraints gbc_btnImageModeImage7 = new GridBagConstraints();
			gbc_btnImageModeImage7.fill = GridBagConstraints.BOTH;
			gbc_btnImageModeImage7.insets = new Insets(0, 0, 0, 2);
			gbc_btnImageModeImage7.gridx = 0;
			gbc_btnImageModeImage7.gridy = 4;
			pnlShowContentsImageMode.add(btnImageModeImage7, gbc_btnImageModeImage7);
			
			btnImageModeImage8 = new JButton("");
			btnImageModeImage8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(DataManager.dayofweekICnum[dayofweekImageMode]-imageWave>=8){
							Desktop.getDesktop().browse(new java.net.URI(DataManager.dayofweekURL[dayofweekImageMode][7+imageWave]));
						}
					}
					catch(IOException e1) {
						e1.printStackTrace();
					}
					catch(URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnImageModeImage8.setBackground(new Color(255, 255, 255));
			GridBagConstraints gbc_btnImageModeImage8 = new GridBagConstraints();
			gbc_btnImageModeImage8.fill = GridBagConstraints.BOTH;
			gbc_btnImageModeImage8.insets = new Insets(0, 2, 0, 0);
			gbc_btnImageModeImage8.gridx = 1;
			gbc_btnImageModeImage8.gridy = 4;
			pnlShowContentsImageMode.add(btnImageModeImage8, gbc_btnImageModeImage8);
			//ShowContentsImageMode 패널 End
		//ShowContents 패널 End
		
		epSunText.setEditable(false);
		epMonText.setEditable(false);
		epTueText.setEditable(false);
		epWedText.setEditable(false);
		epThuText.setEditable(false);
		epFriText.setEditable(false);
		epSatText.setEditable(false);
		
		DataManager.decodeIndex('t');
		DataManager.decodeIndex('i');
		
		
		
		frameLinkDiaduler.setVisible(true);
		weekContentsChanger();
	}
	public void weekContentsChanger(){
		if(DataManager.onlinemode){
			if(!isSubscribing)
				ClientManager.connect(new CarrierFromClient(DataManager.serialCode, Integer.parseInt(DateManager.toString(DateManager.getSun(), 6))));
			else{
				ClientManager.connect(new CarrierFromClient(DataManager.SSCserialCode, Integer.parseInt(DateManager.toString(DateManager.getSun(), 6))));
				System.out.println(DataManager.SSCserialCode);
			}
		}
		//해당주 분할
		DataManager.sortDOWText();
		DataManager.sortDOWImageContents();
		//로딩
		weekDateChanger();
		weekTextChanger();
		weekImageChanger();
		
	}
	private void weekDateChanger(){
		for(int i=0; i<7; i++){
			imagenum[i]=0;
		}
		btnWarpDate.setText("검색");
		lblSunDate.setText("3/17");
		lblMonDate.setText("3/23");
		lblTueDate.setText("4/14");
		lblWedDate.setText("4/22");
		lblThuDate.setText("5/9");
		lblFriDate.setText("5/10");
		lblSatDate.setText("5/13");
	}
	public void weekImageChanger(){
		btnSunImageViewDefault.setIcon(  (DataManager.dayofweekICnum[0]==0) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[0][0], 200, lblSunDate.getHeight())  );
		btnMonImageViewDefault.setIcon(  (DataManager.dayofweekICnum[1]==0) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[1][0], 200, lblSunDate.getHeight())  );
		btnTueImageViewDefault.setIcon(  (DataManager.dayofweekICnum[2]==0) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[2][0], 200, lblSunDate.getHeight())  );
		btnWedImageViewDefault.setIcon(  (DataManager.dayofweekICnum[3]==0) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[3][0], 200, lblSunDate.getHeight())  );
		btnThuImageViewDefault.setIcon(  (DataManager.dayofweekICnum[4]==0) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[4][0], 200, lblSunDate.getHeight())  );
		btnFriImageViewDefault.setIcon(  (DataManager.dayofweekICnum[5]==0) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[5][0], 200, lblSunDate.getHeight())  );
		btnSatImageViewDefault.setIcon(  (DataManager.dayofweekICnum[6]==0) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[6][0], 200, lblSunDate.getHeight())  );
	}
	private void weekTextChanger(){
		epSunText.setText(DataManager.dayofweekText[0]);
		epMonText.setText(DataManager.dayofweekText[1]);
		epTueText.setText(DataManager.dayofweekText[2]);
		epWedText.setText(DataManager.dayofweekText[3]);
		epThuText.setText(DataManager.dayofweekText[4]);
		epFriText.setText(DataManager.dayofweekText[5]);
		epSatText.setText(DataManager.dayofweekText[6]);
	}
	private ImageIcon dayImageChanger(int dayofweek, boolean identifier){//날짜당 이미지 체인저
		if(identifier){//다음 이미지, identifier=true
			return rescaleImage(DataManager.dayofweekImage[dayofweek][imagenum[dayofweek]+1], 200, lblSunDate.getHeight());
		}
		else{//이전 이미지,
			return rescaleImage(DataManager.dayofweekImage[dayofweek][imagenum[dayofweek]-1], 200, lblSunDate.getHeight());
		}
	}
	private void dayContentsModeChanger(boolean modechange){
		if(modechange){
			imagemode=modechange;
			pnlShowContentsDefault.setVisible(false);
			pnlShowContentsImageMode.setVisible(true);
			
			btnImageModeImage1.setIcon(  (DataManager.dayofweekICnum[dayofweekImageMode]==(0+imageWave)) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[dayofweekImageMode][0+imageWave], btnImageModeImage1.getWidth(), btnImageModeImage1.getHeight())  );
			btnImageModeImage2.setIcon(  (DataManager.dayofweekICnum[dayofweekImageMode]<=(1+imageWave)) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[dayofweekImageMode][1+imageWave], btnImageModeImage1.getWidth(), btnImageModeImage1.getHeight())  );
			btnImageModeImage3.setIcon(  (DataManager.dayofweekICnum[dayofweekImageMode]<=(2+imageWave)) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[dayofweekImageMode][2+imageWave], btnImageModeImage1.getWidth(), btnImageModeImage1.getHeight())  );
			btnImageModeImage4.setIcon(  (DataManager.dayofweekICnum[dayofweekImageMode]<=(3+imageWave)) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[dayofweekImageMode][3+imageWave], btnImageModeImage1.getWidth(), btnImageModeImage1.getHeight())  );
			btnImageModeImage5.setIcon(  (DataManager.dayofweekICnum[dayofweekImageMode]<=(4+imageWave)) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[dayofweekImageMode][4+imageWave], btnImageModeImage1.getWidth(), btnImageModeImage1.getHeight())  );
			btnImageModeImage6.setIcon(  (DataManager.dayofweekICnum[dayofweekImageMode]<=(5+imageWave)) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[dayofweekImageMode][5+imageWave], btnImageModeImage1.getWidth(), btnImageModeImage1.getHeight())  );
			btnImageModeImage7.setIcon(  (DataManager.dayofweekICnum[dayofweekImageMode]<=(6+imageWave)) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[dayofweekImageMode][6+imageWave], btnImageModeImage1.getWidth(), btnImageModeImage1.getHeight())  );
			btnImageModeImage8.setIcon(  (DataManager.dayofweekICnum[dayofweekImageMode]<=(7+imageWave)) ? rescaleImage(defaultimage, 120, 100) : rescaleImage(DataManager.dayofweekImage[dayofweekImageMode][7+imageWave], btnImageModeImage1.getWidth(), btnImageModeImage1.getHeight())  );
		}
		else{
			imageWave=0;
			imagemode=modechange;
			pnlShowContentsDefault.setVisible(true);
			pnlShowContentsImageMode.setVisible(false);
			weekContentsChanger();
		}
	}

	public ImageIcon rescaleImage(BufferedImage image, int maxWidth, int maxHeight)
	{
	    int newHeight = 0, newWidth = 0;        // Variables for the new height and width
	    int priorHeight = 0, priorWidth = 0;
	    ImageIcon sizeImage;

	    sizeImage = new ImageIcon(image);

	    if(sizeImage!=null)
	    {
	        priorHeight = sizeImage.getIconHeight(); 
	        priorWidth = sizeImage.getIconWidth();
	    }

	    // Calculate the correct new height and width
	    if((float)priorHeight/(float)priorWidth > (float)maxHeight/(float)maxWidth)
	    {
	        newHeight = maxHeight;
	        newWidth = (int)(((float)priorWidth/(float)priorHeight)*(float)newHeight);
	    }
	    else 
	    {
	        newWidth = maxWidth;
	        newHeight = (int)(((float)priorHeight/(float)priorWidth)*(float)newWidth);
	    }


	    // Resize the image

	    // 1. Create a new Buffered Image and Graphic2D object
	    BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    // 2. Use the Graphic object to draw a new image to the image in the buffer
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(image, 0, 0, newWidth, newHeight, null);
	    g2.dispose();

	    // 3. Convert the buffered image into an ImageIcon for return
	    return (new ImageIcon(resizedImg));
	}
	public ImageIcon rescaleImage(ImageIcon imageicon, int maxWidth, int maxHeight)
	{
	    int newHeight = 0, newWidth = 0;        // Variables for the new height and width
	    int priorHeight = 0, priorWidth = 0;
	    
	    if(imageicon!=null)
	    {
	    	priorHeight = imageicon.getIconHeight(); 
	    	priorWidth = imageicon.getIconWidth();
	    }

	    // Calculate the correct new height and width
	    if((float)priorHeight/(float)priorWidth > (float)maxHeight/(float)maxWidth)
	    {
	        newHeight = maxHeight;
	        newWidth = (int)(((float)priorWidth/(float)priorHeight)*(float)newHeight);
	    }
	    else 
	    {
	        newWidth = maxWidth;
	        newHeight = (int)(((float)priorHeight/(float)priorWidth)*(float)newWidth);
	    }


	    // Resize the image

	    // 1. Create a new Buffered Image and Graphic2D object
	    BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    // 2. Use the Graphic object to draw a new image to the image in the buffer
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(imageicon.getImage(), 0, 0, newWidth, newHeight, null);
	    g2.dispose();

	    // 3. Convert the buffered image into an ImageIcon for return
	    return (new ImageIcon(resizedImg));
	}
}
