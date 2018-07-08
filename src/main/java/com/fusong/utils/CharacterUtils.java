package com.fusong.utils;

import java.util.Random;

public class CharacterUtils {
	public static String getRandomString(int length){
		String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
	
		for(int i=0; i<length; ++i){
		//产生0-61的数字
		int number=random.nextInt(62);
		sb.append(str.charAt(number));
		}
		
		return sb.toString();
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(CharacterUtils.getRandomString(8));
	}
}
