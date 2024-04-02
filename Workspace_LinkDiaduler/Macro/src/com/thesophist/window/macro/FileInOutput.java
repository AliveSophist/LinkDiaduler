package com.thesophist.window.macro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FileInOutput {
	public static String[] macrolist;
	private static File f = new File("list.txt");

	public static void fileInput() throws IOException {
		if (f.isFile()) {
			System.out.println("OK ���� �ֽ��ϴ�.");
		}
		else {
			System.out.println("Nope ������ ���� �����մϴ�.");
			new FileWriter(f, true);
		}

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f.getPath()));
		String temp="";

		while(true) {
			String line = br.readLine();
			if (line==null)
				break;
			temp=temp+line+"||";
		}

		StringTokenizer st = new StringTokenizer(temp,"||");

		macrolist=new String[st.countTokens()];

		for(int i=0;st.hasMoreTokens();i++) {
			macrolist[i]=st.nextToken();
		}
	}

	public static void fileOutput(){
		PrintWriter pw;
		try {
			pw = new PrintWriter(f.getPath());

			for (int i = 0; i < JPanelHome.list.getModel().getSize(); i++)
				pw.println(JPanelHome.list.getModel().getElementAt(i));

			pw.close();
		} catch (FileNotFoundException e) {
			// TODO �ڵ� ������ catch ����
			e.printStackTrace();
		}
	}
}