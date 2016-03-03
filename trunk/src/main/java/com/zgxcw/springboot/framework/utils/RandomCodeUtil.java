package com.zgxcw.springboot.framework.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机数字工具类
 * 
 */
public class RandomCodeUtil {
	public static char[]	arrayNumber	= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * 生成指定随机数字
	 * 
	 * @param length
	 * @return
	 */
	public static String randomNumber(int length) {
		StringBuffer randomCode = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++)
			randomCode.append(arrayNumber[random.nextInt(arrayNumber.length - 1)]);
		return randomCode.toString().toUpperCase();
	}

	/**
	 * 产生随机字符串 调用此方法randomString(int),int是字符串的长度，即可产生指定长度的随机字符串。
	 * */
	private static Random	randGen				= null;
	private static char[]	numbersAndLetters	= null;

	public static final String randomString(int length) {
		if (length < 1)
			return null;
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
			// numbersAndLetters =
			// ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++)
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		// randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
		return new String(randBuffer);
	}

	static List<String>	pswdList	= null;

	/**
	 * 产生随机码
	 * 包含（a-z、A-Z、0-9）除了小写字母l,o；大写字母O,I；数字1,0。
	 * 
	 * @param rms
	 *            产生多少个
	 * @param rmsize
	 *            随机码位数
	 * @return List随机数集合
	 */
	public static List<String> getRandoms(int rms, int rmsize) {
		pswdList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer("a,b,c,d,e,f,g,h,i,g,k,m,n,p,q,r,s,t,u,v,w,x,y,z"); // l,o
		sb.append(",A,B,C,D,E,F,G,H,G,K,L,M,N,P,Q,R,S,T,U,V,W,X,Y,Z"); // O,I
		sb.append(",2,3,4,5,6,7,8,9"); // 1,0
		String[] arr = sb.toString().split(",");
		while (pswdList.size() < rms) {
			String randstr = getPswd(arr, rmsize);
			if (isbe(randstr))
				pswdList.add(randstr);
		}
		return pswdList;
	}

	private static boolean isbe(String str) {
		for (int i = 0; i < pswdList.size(); i++)
			if (pswdList.get(i).equals(str))
				return false;
		return true;
	}

	private static String getPswd(String[] arr, int num) {
		StringBuffer b = new StringBuffer();
		Random r;
		int k;
		for (int i = 0; i < num; i++) {
			r = new Random();
			k = r.nextInt();
			b.append(String.valueOf(arr[Math.abs(k % arr.length)]));
		}
		return b.toString();
	}
}
