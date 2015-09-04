package com.web.core.utility;

import java.util.Random;

public abstract class ReplaceUtil {
	public static int getRandomInt(int i){
		Random random = new Random();
		return random.nextInt(i);
	}
	public static String get4LengthStr(int i){
		  String pattern="0000";
		  java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
		return df.format(i);
	}
	public static String get6LengthStr(int i){
		String pattern="000000";
		java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
		return df.format(i);
	}
	public static String get8LengthStr(int i){
		  String pattern="00000000";
		  java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
		return df.format(i);
	}
}
