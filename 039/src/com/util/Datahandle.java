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
		 Typename="[�Q��]";
        		 break;
     case 1: 
    	 Typename="[�o��]";
		 break;
     case 2: 
    	 Typename="[�߱o]";
		 break;
    
     default: 
    	 Typename="���~";
		 break;
 }
	 return Typename;
}

public String getArticleClass(Integer articleClass) {
	String Classname="";
	 switch(articleClass) { 
	 case 0:
		 Classname="�B�ʥ�";
        		 break;
     case 1: 
    	 Classname="�ӫ~����";
		 break;
     case 2: 
    	 Classname="�ɨưQ��";
		 break;
    
     default: 
    	 Classname="���~";
		 break;
 }
	 return Classname;
}

}
