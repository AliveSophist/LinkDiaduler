package com.test;

import java.util.StringTokenizer;

public class cbsaser {

	public static void main(String[] args) {
		// TODO 자동 생성된 메소드 스텁
		String[] macrolist;
		String nogada="case \"escape\":*return 27;^case \"page_up\":*return 33;^case \"page_down\":*return 34;^case \"end\":*return 35;^case \"home\":*return 36;^case \"left_arrow\":*return 37;^case \"up_arrow\":*return 38;^case \"right_arrow\":*return 39;^case \"down_arrow\":*return 40;^case \"insert\":*return 45;^case \"delete\":*return 46;^case \"0\":*return 48;^case \"1\":*return 49;^case \"2\":*return 50;^case \"3\":*return 51;^case \"4\":*return 52;^case \"5\":*return 53;^case \"6\":*return 54;^case \"7\":*return 55;^case \"8\":*return 56;^case \"9\":*return 57;^case \"a\":*return 65;^case \"b\":*return 66;^case \"c\":*return 67;^case \"d\":*return 68;^case \"e\":*return 69;^case \"f\":*return 70;^case \"g\":*return 71;^case \"h\":*return 72;^case \"i\":*return 73;^case \"j\":*return 74;^case \"k\":*return 75;^case \"l\":*return 76;^case \"m\":*return 77;^case \"n\":*return 78;^case \"o\":*return 79;^case \"p\":*return 80;^case \"q\":*return 81;^case \"r\":*return 82;^case \"s\":*return 83;^case \"t\":*return 84;^case \"u\":*return 85;^case \"v\":*return 86;^case \"w\":*return 87;^case \"x\":*return 88;^case \"y\":*return 89;^case \"z\":*return 90;^case \"left_window_key\":*return 91;^case \"right_window_key\":*return 92;^case \"select_key\":*return 93;^case \"numpad_0\":*return 96;^case \"numpad_1\":*return 97;^case \"numpad_2\":*return 98;^case \"numpad_3\":*return 99;^case \"numpad_4\":*return 100;^case \"numpad_5\":*return 101;^case \"numpad_6\":*return 102;^case \"numpad_7\":*return 103;^case \"numpad_8\":*return 104;^case \"numpad_9\":*return 105;^case \"multiply\":*return 106;^case \"add\":*return 107;^case \"subtract\":*return 109;^case \"decimal_point\":*return 110;^case \"divide\":*return 111;^case \"f1\":*return 112;^case \"f2\":*return 113;^case \"f3\":*return 114;^case \"f4\":*return 115;^case \"f5\":*return 116;^case \"f6\":*return 117;^case \"f7\":*return 118;^case \"f8\":*return 119;^case \"f9\":*return 120;^case \"f10\":*return 121;^case \"f11\":*return 122;^case \"f12\":*return 123;^case \"num_lock\":*return 144;^case \"scroll_lock\":*return 145;^case \"semi-colon\":*return 186;^case \"equal_sign\":*return 187;^case \"comma\":*return 188;^case \"dash\":*return 189;^case \"period\":*return 190;^case \"forward_slash\":*return 191;^case \"grave_accent\":*return 192;^case \"open_bracket\":*return 219;^case \"back_slash\":*return 220;^case \"close_braket\":*return 221;^case \"single_quote\":*return 222;";

		String cased, returned;
		String a, b, c;
		StringTokenizer st = new StringTokenizer(nogada,"^");
		macrolist=new String[st.countTokens()];

		for(int i=0;st.hasMoreTokens();i++) {
			macrolist[i]=st.nextToken();

			StringTokenizer kt = new StringTokenizer(macrolist[i],"*");
			cased=kt.nextToken();
			a=cased.split(" ")[1];
			returned=kt.nextToken();
			b=returned.split(" ")[1];
			c=a;
			a=b;
			b=c;
			System.out.println(cased.split(" ")[0]+" "+a);
			System.out.println(returned.split(" ")[0]+" "+b);
		}
	}
}
