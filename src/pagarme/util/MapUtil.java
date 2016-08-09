package pagarme.util;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {  
	public static String mapToString(Map<String, String> map) {  
		StringBuilder stringBuilder = new StringBuilder();  

		for (String key : map.keySet()) {  
			if (stringBuilder.length() > 0) {  
				stringBuilder.append("&");  
			}  
			String value = map.get(key);  
			try {  
				stringBuilder.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));  
				stringBuilder.append("=");  
				stringBuilder.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");  
			} catch (UnsupportedEncodingException e) {  
				throw new RuntimeException("This method requires UTF-8 encoding support", e);  
			}  
		}  

		return stringBuilder.toString();  
	}  

	public static Map<String, String> stringToMap(String input) {  
		Map<String, String> map = new HashMap<String, String>();  

		String[] nameValuePairs = input.split("&");  
		for (String nameValuePair : nameValuePairs) {  
			String[] nameValue = nameValuePair.split("=");  
			try {  
				map.put(URLDecoder.decode(nameValue[0], "UTF-8"), nameValue.length > 1 ? URLDecoder.decode(  
						nameValue[1], "UTF-8") : "");  
			} catch (UnsupportedEncodingException e) {  
				throw new RuntimeException("This method requires UTF-8 encoding support", e);  
			}  
		}  

		return map;  
	}  

	public static String listOfMapToString(List<Map<String,String>> mapList){
		StringBuilder stringBuilder = new StringBuilder();  
		for(Map<String,String> m : mapList){
			stringBuilder.append(mapToString(m));
			stringBuilder.append("&"); 
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		return stringBuilder.toString();
	}

	public static Map<String, Object> objectToMap(Object obj) {

		Map<String, Object> result = new HashMap<String, Object>();
		BeanInfo info = null; 
		Method reader= null;
		try {
			info = Introspector.getBeanInfo(obj.getClass());

			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				reader = pd.getReadMethod();
				if (reader != null)
					 if(reader.invoke(obj)!=null && !reader.invoke(obj).toString().contains("class"))
					result.put(pd.getName(), reader.invoke(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}  