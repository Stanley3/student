package com.chejiawang.android.studentclient.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtils {

	/**
	 * 判断给定字符串是否空白串。
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串
	 * 若输入字符串为null或空字符串，返回true
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty( String input ) 
	{
		if ( input == null || "".equals( input ) )
			return true;
		
		for ( int i = 0; i < input.length(); i++ ) 
		{
			char c = input.charAt( i );
			if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' )
			{
				return false;
			}
		}
		return true;
	}
	   //将输入流转换成字符串  
		public static String inStream2String(InputStream is) throws Exception {
			if (is != null) {
				try {
					BufferedReader tBufferedReader = new BufferedReader(
							new InputStreamReader(is));
					StringBuffer tStringBuffer = new StringBuffer();
					String sTempOneLine = new String("");
					while ((sTempOneLine = tBufferedReader.readLine()) != null) {
						tStringBuffer.append(sTempOneLine);
					}
					return tStringBuffer.toString();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return null;
		}
}
