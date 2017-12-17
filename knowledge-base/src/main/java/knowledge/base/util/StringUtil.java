package knowledge.base.util;

public class StringUtil {
	
	public static String strFomat(String str){
		
		StringBuffer sb=new StringBuffer(str);
		
		return sb.deleteCharAt(str.lastIndexOf(":")).toString();
	}

}
