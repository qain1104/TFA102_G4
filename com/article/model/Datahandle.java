package com.article.model;

import java.io.UnsupportedEncodingException;

public class Datahandle {

public String getArticleContent(byte[] bytes) {
	String str="";
	try {
		str = new String(bytes,"UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return str;
}

//public static void main(String args[]) {
//	String s= "1234567";
//byte[] b =s.getBytes();
//Datahandle dh = new Datahandle();
//System.out.println(dh.getArticleContent(b));
//
//} 
}
