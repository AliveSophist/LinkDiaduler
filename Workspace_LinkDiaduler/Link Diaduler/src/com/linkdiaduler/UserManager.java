package com.linkdiaduler;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
public class UserManager{
	
	public JFrame frameManager;
	//유저모드 선택화면
	private JPanel pnlChooseMode;
	private JLabel lblWelcome;
	private JButton btnOnlineMode, btnOfflineMode;
	//온라인유저 로그인화면
	private JPanel pnlLoginOnlineUser;
	private JLabel lblID, lblPassword;
	private JTextField IDField;
	private JPasswordField PWField;
	private JButton btnLogin, btnRegisterFromOn;
	private JCheckBox chckbxIdpw;
	//온라인유저 가입화면
	private JPanel pnlRegisterOnlineUser;
	private JLabel lblRegisterID, lblRegisterPW, lblCheckPW, lblRegisterNickname, lblRegisterEmail;
	private JTextField tfRegisterID, tfRegisterNickname, tfRegisterEmail;
	private JPasswordField tfRegisterPW, tfCheckPW;
	private JCheckBox chckbxAcceptSharing;
	private JButton btnRegister;
	//오프라인유저 선택화면
	private JPanel pnlChooseOfflineUser;
	private JLabel lblLoading;
	private JScrollPane scrollPane;
	private JList<?> list;
	private JButton btnChoiceUser, btnframeAddUser, btnDeleteUser;
	//오프라인유저 추가화면
	private JPanel pnlAddOfflineUser;
	private JTextField username;
	private JLabel lblUserName, lblexplanation1, lblexplanation2, lblexplanation3, lblexplanation4;
	private JButton btnRegisterFromOff, btnMakeUser;
	
	public static BufferedImage loading, lindaicon;
	

	public UserManager() throws IOException {
		loading=DataManager.loadimage("loading", "gif");
		lindaicon=DataManager.loadimage("Linda", "png");
		
		initialize();
		pnlChooseMode.setVisible(true);
		pnlLoginOnlineUser.setVisible(false);
		pnlRegisterOnlineUser.setVisible(false);
		pnlChooseOfflineUser.setVisible(false);
		pnlAddOfflineUser.setVisible(false);
	}

	private void initialize() {
		frameManager = new JFrame();
		frameManager.setResizable(false);
		frameManager.setTitle("UserManager");
		frameManager.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-125, Toolkit.getDefaultToolkit().getScreenSize().height/2-150, 250, 290);
		frameManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameManager.getContentPane().setLayout(null);
		frameManager.setIconImage(lindaicon);
		
		pnlChooseMode = new JPanel();
		pnlChooseMode.setBackground(Settings.background);
		pnlChooseMode.setBounds(0, 0, 244, 262);
		frameManager.getContentPane().add(pnlChooseMode);
		pnlChooseMode.setLayout(null);
		
		lblWelcome = new JLabel("Welcome to Link Diaduler!");
		lblWelcome.setForeground(Settings.font);
		lblWelcome.setFont(new Font("굴림", Font.BOLD, 13));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(12, 10, 220, 46);
		pnlChooseMode.add(lblWelcome);
		
		btnOfflineMode = new JButton("Offline");
		btnOfflineMode.setForeground(Settings.font);
		btnOfflineMode.setFont(new Font("궁서", Font.BOLD, 18));
		btnOfflineMode.setBackground(Settings.button);
		btnOfflineMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlChooseMode.setVisible(false);
				pnlChooseOfflineUser.setVisible(true);
			}
		});
		btnOfflineMode.setBounds(12, 66, 111, 186);
		pnlChooseMode.add(btnOfflineMode);
		
		btnOnlineMode = new JButton("Online");
		btnOnlineMode.setForeground(Settings.font);
		btnOnlineMode.setFont(new Font("궁서", Font.BOLD, 18));
		btnOnlineMode.setBackground(Settings.button);
		btnOnlineMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlChooseMode.setVisible(false);
				pnlLoginOnlineUser.setVisible(true);
				DataManager.onlinemode=true;
				DateManager.limitEnd=2050;
			}
		});
		btnOnlineMode.setBounds(121, 66, 111, 186);
		pnlChooseMode.add(btnOnlineMode);
		
		lblLoading = new JLabel(new ImageIcon(loading));
		lblLoading.setBounds(0, 0, 244, 262);
		pnlChooseMode.add(lblLoading);
		lblLoading.setVisible(false);
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		
		
		pnlLoginOnlineUser = new JPanel();
		pnlLoginOnlineUser.setBackground(Settings.background);
		pnlLoginOnlineUser.setBounds(0, 0, 244, 262);
		frameManager.getContentPane().add(pnlLoginOnlineUser);
		pnlLoginOnlineUser.setLayout(null);
		pnlLoginOnlineUser.setVisible(false);
		
		lblID = new JLabel("ID");
		lblID.setForeground(Settings.font);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(8, 58, 81, 24);
		pnlLoginOnlineUser.add(lblID);
		
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(Settings.font);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(8, 92, 81, 24);
		pnlLoginOnlineUser.add(lblPassword);
		
		IDField = new JTextField();
		if(Settings.savedID!=null)
			IDField.setText(Settings.savedID);
		IDField.setBounds(101, 60, 116, 21);
		pnlLoginOnlineUser.add(IDField);
		IDField.setColumns(10);
		
		PWField = new JPasswordField();
		if(Settings.savedPW!=null)
			PWField.setText(Settings.savedPW);
		PWField.setColumns(10);
		PWField.setBounds(101, 94, 116, 21);
		pnlLoginOnlineUser.add(PWField);
		
		btnLogin = new JButton("Login");
		btnLogin.setForeground(Settings.font);
		btnLogin.setBackground(Settings.button);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ClientManager.connect(new CarrierFromClient(IDField.getText(), PWField.getText()))){
					if(chckbxIdpw.isSelected()){
						Settings.savedID=IDField.getText();
						Settings.savedPW=PWField.getText();
					}
					else{
						Settings.savedID=null;
						Settings.savedPW=null;
					}
					
					Main.openframeHome();
					frameManager.dispose();
				}
			}
		});
		btnLogin.setBounds(136, 126, 81, 40);
		pnlLoginOnlineUser.add(btnLogin);
		
		btnRegisterFromOn = new JButton("Register");
		btnRegisterFromOn.setForeground(Settings.font);
		btnRegisterFromOn.setBackground(Settings.button);
		btnRegisterFromOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlLoginOnlineUser.setVisible(false);
				pnlRegisterOnlineUser.setVisible(true);
			}
		});
		btnRegisterFromOn.setBounds(8, 229, 88, 23);
		pnlLoginOnlineUser.add(btnRegisterFromOn);
		
		chckbxIdpw = new JCheckBox(" ID / PW \uAE30\uC5B5");
		chckbxIdpw.setForeground(Settings.font);
		chckbxIdpw.setBackground(Settings.background);
		chckbxIdpw.setBounds(8, 135, 101, 23);
		pnlLoginOnlineUser.add(chckbxIdpw);
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		
		
		pnlRegisterOnlineUser = new JPanel();
		pnlRegisterOnlineUser.setBackground(Settings.background);
		pnlRegisterOnlineUser.setBounds(0, 0, 244, 262);
		frameManager.getContentPane().add(pnlRegisterOnlineUser);
		pnlRegisterOnlineUser.setLayout(null);
		pnlRegisterOnlineUser.setVisible(false);
		
		lblRegisterID = new JLabel("ID");
		lblRegisterID.setForeground(Settings.font);
		lblRegisterID.setBounds(25, 45, 81, 15);
		pnlRegisterOnlineUser.add(lblRegisterID);
		
		lblRegisterPW = new JLabel("PASSWORD");
		lblRegisterPW.setForeground(Settings.font);
		lblRegisterPW.setBounds(25, 70, 81, 15);
		pnlRegisterOnlineUser.add(lblRegisterPW);
		
		lblCheckPW = new JLabel("re password");
		lblCheckPW.setForeground(Settings.font);
		lblCheckPW.setBounds(25, 95, 81, 15);
		pnlRegisterOnlineUser.add(lblCheckPW);
		
		lblRegisterNickname = new JLabel("NICKNAME");
		lblRegisterNickname.setForeground(Settings.font);
		lblRegisterNickname.setBounds(25, 120, 81, 15);
		pnlRegisterOnlineUser.add(lblRegisterNickname);
		
		lblRegisterEmail = new JLabel("E - MAIL");
		lblRegisterEmail.setForeground(Settings.font);
		lblRegisterEmail.setBounds(25, 145, 81, 15);
		pnlRegisterOnlineUser.add(lblRegisterEmail);
		
		tfRegisterID = new JTextField();
		tfRegisterID.setBounds(114, 42, 106, 21);
		pnlRegisterOnlineUser.add(tfRegisterID);
		tfRegisterID.setColumns(10);
		
		tfRegisterPW = new JPasswordField();
		tfRegisterPW.setBounds(114, 67, 106, 21);
		pnlRegisterOnlineUser.add(tfRegisterPW);
		tfRegisterPW.setColumns(10);
		
		tfCheckPW = new JPasswordField();
		tfCheckPW.setBounds(114, 92, 106, 21);
		pnlRegisterOnlineUser.add(tfCheckPW);
		tfCheckPW.setColumns(10);
		
		tfRegisterNickname = new JTextField();
		tfRegisterNickname.setBounds(114, 117, 106, 21);
		pnlRegisterOnlineUser.add(tfRegisterNickname);
		tfRegisterNickname.setColumns(10);
		
		tfRegisterEmail = new JTextField();
		tfRegisterEmail.setBounds(114, 142, 106, 21);
		pnlRegisterOnlineUser.add(tfRegisterEmail);
		tfRegisterEmail.setColumns(10);
		
		chckbxAcceptSharing = new JCheckBox(" \uC77C\uC815\uC744 \uACF5\uC720\uD569\uB2C8\uB2E4");
		chckbxAcceptSharing.setForeground(Settings.font);
		chckbxAcceptSharing.setBackground(Settings.background);
		chckbxAcceptSharing.setSelected(true);
		chckbxAcceptSharing.setBounds(35, 168, 171, 21);
		pnlRegisterOnlineUser.add(chckbxAcceptSharing);
		
		btnRegister = new JButton("\uD574\uB2F9 \uC815\uBCF4\uB85C \uAC00\uC785");
		btnRegister.setBackground(Settings.button);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!String.valueOf(tfRegisterPW.getPassword()).equals(String.valueOf(tfCheckPW.getPassword())))
					JOptionPane.showMessageDialog(null, "비밀번호 재입력을 잘못 입력하셨습니다.", "Error!", JOptionPane.ERROR_MESSAGE);
				
				String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
				if(!tfRegisterID.getText().matches(regex)){
					JOptionPane.showMessageDialog(null, "아이디 시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5자 이상 12자 이하", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(String.valueOf(tfRegisterPW.getPassword()).length()>10){
					JOptionPane.showMessageDialog(null, "패스워드는 10자이하", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(tfRegisterNickname.getText().length()<4 || tfRegisterNickname.getText().length()>16){
					JOptionPane.showMessageDialog(null, "닉네임은 4자 이상 15자 이하", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
				if(!tfRegisterEmail.getText().matches(regex)){
					JOptionPane.showMessageDialog(null, "이메일 형식에 맞지 않습니다.", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int acceptSSC=0;
				if(chckbxAcceptSharing.isSelected())
					acceptSSC=1;
				if(ClientManager.connect(new CarrierFromClient(tfRegisterID.getText(), String.valueOf(tfRegisterPW.getPassword()), tfRegisterNickname.getText(), acceptSSC, tfRegisterEmail.getText()))){
					Main.openframeHome();
					frameManager.dispose();
				}
			}
		});
		btnRegister.setBounds(80, 195, 152, 57);
		pnlRegisterOnlineUser.add(btnRegister);
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		
		
		pnlChooseOfflineUser = new JPanel();
		pnlChooseOfflineUser.setBackground(Settings.background);
		pnlChooseOfflineUser.setBounds(0, 0, 244, 262);
		pnlChooseOfflineUser.setLayout(null);
		frameManager.getContentPane().add(pnlChooseOfflineUser);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 25, 196, 138);
		pnlChooseOfflineUser.add(scrollPane);
		list = new JList<Object>(DataManager.returnUserArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		btnChoiceUser = new JButton("해당 유저로 동기화");
		btnChoiceUser.setForeground(Settings.font);
		btnChoiceUser.setBackground(Settings.button);
		btnChoiceUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblLoading.setVisible(true);
				
				if((String)list.getSelectedValue()==null){
					JOptionPane.showMessageDialog(null, "동기화하려는 유저를 선택해주세요!", "Error!", JOptionPane.ERROR_MESSAGE);					
				}
				else{
					DataManager.user=(String)list.getSelectedValue();

					Main.openframeHome();
					frameManager.dispose();
				}
			}
		});
		btnChoiceUser.setBounds(22, 174, 196, 30);
		pnlChooseOfflineUser.add(btnChoiceUser);
		
		btnframeAddUser = new JButton("유저 추가");
		btnframeAddUser.setForeground(Settings.font);
		btnframeAddUser.setBackground(Settings.button);
		btnframeAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlChooseOfflineUser.setVisible(false);
				
				pnlAddOfflineUser.setVisible(true);
			}
		});
		btnframeAddUser.setBounds(22, 214, 90, 30);
		pnlChooseOfflineUser.add(btnframeAddUser);
		
		btnDeleteUser = new JButton("유저 삭제");
		btnDeleteUser.setForeground(Settings.font);
		btnDeleteUser.setBackground(Settings.button);
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataManager.deleteUser((String)list.getSelectedValue());
				
				list = new JList<Object>(DataManager.returnUserArray());
				scrollPane.setViewportView(list);
			}
		});
		
		btnDeleteUser.setBounds(128, 214, 90, 30);
		pnlChooseOfflineUser.add(btnDeleteUser);
		
		loading=DataManager.loadimage("loading", "gif");
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		
		
		pnlAddOfflineUser = new JPanel();
		pnlAddOfflineUser.setBackground(Settings.background);
		pnlAddOfflineUser.setBounds(0, 0, 244, 262);
		pnlAddOfflineUser.setLayout(null);
		frameManager.getContentPane().add(pnlAddOfflineUser);
		
		username = new JTextField();
		username.setBounds(94, 18, 136, 21);
		username.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		username.setText("");
        		username.setEditable(true);
        	}
        });
		pnlAddOfflineUser.add(username);
		username.setColumns(10);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Settings.font);
		lblUserName.setBounds(12, 21, 64, 15);
		pnlAddOfflineUser.add(lblUserName);
		
		btnMakeUser = new JButton("유저 생성 [본 PC내]");
		btnMakeUser.setForeground(Settings.font);
		btnMakeUser.setBackground(Settings.button);
		btnMakeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userlist[]=(DataManager.returnUserArray());
				if(username.getText().length()<3){
					username.setText("※3글자 미만!");
					username.setEditable(false);
					return ;
				}
				for(int i=0; i<userlist.length; i++)
					if(userlist[i].matches(username.getText())==true){
						username.setText("※유저 중복됨!");
						username.setEditable(false);
						return ;
					}
				if(username.getText()=="※3글자 미만!"||username.getText()=="※유저 중복됨!")
					return ;
				
				DataManager.makeUserDirectory(username.getText());
				username.setText("");
				
				list = new JList<Object>(DataManager.returnUserArray());
				scrollPane.setViewportView(list);
				
				pnlAddOfflineUser.setVisible(false);
				pnlChooseOfflineUser.setVisible(true);
			}
		});
		btnMakeUser.setBounds(12, 49, 218, 60);
		pnlAddOfflineUser.add(btnMakeUser);
		
		lblexplanation1 = new JLabel("\uC628\uB77C\uC778 \uACC4\uC815\uC740 \uB2E4\uB978\uD68C\uC6D0\uC774 \uC62C\uB9B0");
		lblexplanation1.setForeground(Settings.font);
		lblexplanation1.setBounds(12, 145, 224, 15);
		pnlAddOfflineUser.add(lblexplanation1);
		
		lblexplanation2 = new JLabel("\uC77C\uC815\uC744 \uAD6C\uB3C5 \uAC00\uB2A5\uD558\uBA70 \uC790\uC2E0\uC758");
		lblexplanation2.setForeground(Settings.font);
		lblexplanation2.setBounds(12, 161, 224, 15);
		pnlAddOfflineUser.add(lblexplanation2);
		
		lblexplanation3 = new JLabel("\uC77C\uC815\uC744 \uC11C\uBC84\uC5D0 \uBCF4\uAD00\uD560 \uC218 \uC788\uC2B5\uB2C8\uB2E4.");
		lblexplanation3.setForeground(Settings.font);
		lblexplanation3.setBounds(12, 177, 224, 15);
		pnlAddOfflineUser.add(lblexplanation3);
		
		lblexplanation4 = new JLabel("Android\uAE30\uAE30\uC5D0\uC11C \uC5F4\uB78C\uB3C4 \uAC00\uB2A5\uD569\uB2C8\uB2E4");
		lblexplanation4.setForeground(Settings.font);
		lblexplanation4.setBounds(12, 193, 224, 15);
		pnlAddOfflineUser.add(lblexplanation4);
		
		btnRegisterFromOff = new JButton("지금 계정을 만들겠습니다.");
		btnRegisterFromOff.setForeground(Settings.font);
		btnRegisterFromOff.setBackground(Settings.button);
		btnRegisterFromOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlAddOfflineUser.setVisible(false);
				pnlRegisterOnlineUser.setVisible(true);
			}
		});
		btnRegisterFromOff.setBounds(12, 211, 218, 23);
		pnlAddOfflineUser.add(btnRegisterFromOff);
		
	}
}
