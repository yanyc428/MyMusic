package utils;

import java.util.Random;

public class RandomString {

	/**
	 * 得到随机字符串
	 * @param length 随机字符串的长度
	 * @return 生成的特定长度的随机字符串
	 */
	public static String getString(int length) {
		
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    Random random=new Random();
	    StringBuffer sb=new StringBuffer();
	    for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	    }
	    Log.log("生成随机字符串:" + sb.toString());
	    return sb.toString();
		
	}

}
