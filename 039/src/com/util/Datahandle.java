package util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

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

public String getTime(Object time) {
	SimpleDateFormat tformat = new SimpleDateFormat("yy/MM/dd HH:mm");
	return tformat.format(time);
}

public String getArticleType(Integer articleType) {
	String Typename="";
	 switch(articleType) { 
	 case 0:
		 Typename="[討論]";
        		 break;
     case 1: 
    	 Typename="[發問]";
		 break;
     case 2: 
    	 Typename="[心得]";
		 break;
    
     default: 
    	 Typename="錯誤";
		 break;
 }
	 return Typename;
}

public String getArticleClass(Integer articleClass) {
	String Classname="";
	 switch(articleClass) { 
	 case 0:
		 Classname="運動休閒";
        		 break;
     case 1: 
    	 Classname="商品分享";
		 break;
     case 2: 
    	 Classname="賽事討論";
		 break;
    
     default: 
    	 Classname="錯誤";
		 break;
 }
	 return Classname;
}

}
