/**
 * 
 */
package com.fusong.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * @author 605162215@qq.com
 *
 * 2016年8月15日 上午10:11:00
 */
public class RandomUtil {
	
	private static SecureRandom random = new SecureRandom();
	
	public static String randomUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
	
	public static int randomInt(int min, int max){
        return (int) (min + Math.random() * (max - min));
    }
	
	public static String randomString(int length) {
		if(length > 22){
			return randomLongString(length);
		}
		BigInteger bi = new BigInteger(130, random);
		String str = bi.toString(32);
		return str.substring(0, length);
	}

	public static String randomNumberString(int length) {
		if(length > 35){
			return randomLongNumberString(length);
		}
		BigInteger bi = new BigInteger(130, random);
		String str = bi.toString(10);
		return str.substring(0, length);
	}
	
	private static String randomLongString(int length) {
		StringBuilder sb = new StringBuilder(length);
		while(sb.length() < length){
			sb.append(UUIDUtil.uuid());
		}
		if(sb.length() > length){
			return sb.substring(0, length);
		}
		return sb.toString();
	}
	
	private static String randomLongNumberString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for(int i=0; i<length; i++){
			int num = random.nextInt(10);
			sb.append(num);
		}
		return sb.toString();
	}

	
	public static void main(String[] args) {
		System.out.println("随机的10为数字字符串   "+randomNumberString(10));
		System.out.println("UUID     "+randomUUID());
        System.out.println("一个随机的整数，从1到1000     "+randomInt(1,1000));
        System.out.println("自定义位数的字符串包含子母和数字     "+randomLongString(10));
        System.out.println("自定义随机字符串，只包含数字       "+randomLongNumberString(15));
    }
	
}
