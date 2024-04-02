package com.linkdiaduler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Main{
	
	public static String route="C:\\LinkDiaduler\\";
	/**
	 * �ټ��� Ŭ���̾�Ʈ�� �����Ͽ� ��� ����ϴ� ���� ���α׷�
	 * step
	 * 1. �������� ����.
	 * 2. Ŭ���̾�Ʈ�� ���� ���ӽñ��� ����ŷ���ѳ���.
	 * 3. Ŭ���̾�Ʈ�� ���� ���ӽ� ���ο� �����忡 �Ҵ� �� ������Ʈ ����.
	 */
	
	
	
	public static void go() throws IOException{
		ServerSocket ss = null;
		Socket s = null;

		try{
			ss = new ServerSocket(4567);
			System.out.println("**���� ����**");
			//�ټ��� Ŭ���̾�Ʈ�� ����ϱ� ���� loop
			while(true){
				System.out.println("����ŷ ��");
				s = ss.accept(); //Ŭ���̾�Ʈ ���ӽñ��� ����ŷ

				ServerThread st = new ServerThread(s);//Ŭ���̾�Ʈ ���ӽ� ����ŷ�����Ǹ鼭 ������� ����������.
				st.start(); //�����������.
				System.out.println("  **"+s.getInetAddress().getHostAddress()+"�� ����");
			}
		}finally{
			if (s != null)
				s.close();
			if (ss != null)
				ss.close();
			System.out.println("**���� ����**");
		}
	}

	public static void main(String[] args) {
		
		SQLWords.JDBC_DRIVER = "com.mysql.jdbc.Driver";
		SQLWords.DB_URL_createDB = "jdbc:mysql://localhost:3306/"+"?autoReconnect=true&useSSL=false";
		SQLWords.DB_URL = "jdbc:mysql://localhost:3306/linkdiaduler"+"?autoReconnect=true&useSSL=false";
		SQLWords.USER = "root";
		SQLWords.PASS = "student13";
		
		if(!SQLWords.returnDatabaseExists("linkdiaduler"))
			buildLinkDiadulerDB();
		
		/**
		 * ���� ��� ��� ����.
		 * 
		 * XXXXXXXXXXXXXX = ��������/�����߻� ��
		 *   **XXXXXXXXXX = Ŭ���̾�Ʈ ����/����
		 *   ****XXXXXXXX = Ŭ���̾�Ʈ�� ������Ʈ ����
		 */
		
		try {
			go();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static void buildLinkDiadulerDB() {
		//'LinkDiaduler ���� DB'�� �����Ѵ�.
		SQLWords.createDB();
		//'���� ����� ���̺�'�� �����Ѵ�.
		SQLWords.createAccountTable();
		//'���� ������ ����� �ߺ� ���̺�'�� �����Ѵ�.
		SQLWords.createTempTable();
	}
}