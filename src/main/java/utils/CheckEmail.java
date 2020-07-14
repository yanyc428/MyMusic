package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEmail {

	/**
	 * 检查所给的字符串是否是合法的邮箱地址
	 * @param string 要检查的字符串
	 * @return boolean 是返回true 否则false
	 */
	public static boolean check(String string) {
		if (string == null)
			return false;
		String regEx1 = "^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p;
		Matcher m;
		p = Pattern.compile(regEx1);
		m = p.matcher(string);
		if (m.matches())
			return true;
		else
			return false;
	}
}
