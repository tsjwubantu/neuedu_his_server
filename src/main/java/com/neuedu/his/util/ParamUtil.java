package com.neuedu.his.util;

public class ParamUtil {

	/**
	 * 判断字符串是否能否转换为整形，如果不可以，赋为默认值
	 */
	public static String stringToInt(String str,int def) {
		if(str==null || str.trim().length()==0) {
			str = def+"";
		}else {
			try {
				Integer.parseInt(str);
			}catch(Exception e) {
				str = def+"";
			}
		}
		return str;
	}
}
