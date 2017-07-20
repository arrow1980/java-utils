

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonArraySingleItemAdjuster {
	public static String adjustJson(String json, String arrayKey){
		String returnString = "";
		
		StringBuffer jsonString = new StringBuffer(json.toString());
		Pattern pattern = Pattern.compile("(\""+arrayKey+"\":\\{)|(\\{)|(\\})");
		Matcher matcher = pattern.matcher(jsonString.toString());
		
		List<String[]> matchList = new ArrayList<String[]>();
		while (matcher.find()) {
			String[] item = new String[2];
			item[0] = matcher.group();
			item[1] = String.valueOf(matcher.end());
			matchList.add(item);
		}
		
		boolean find = false;
		int startCount = 0;
		int insertCount = 0;
		for(String[] item : matchList){
			if(item[0].contains(arrayKey)){
				find = true;
				jsonString.insert(Integer.parseInt(item[1])+insertCount-1, "[");
				insertCount++;
				continue;
			}else{
				if(find){
					if(item[0].equals("{")){
						startCount++;
						continue;
					}
					if(item[0].equals("}")){
						if(startCount > 0){
							startCount--;
							continue;
						}else if(startCount == 0){
							jsonString.insert(Integer.parseInt(item[1])+insertCount, "]");
							insertCount++;
							find = false;
						}
					}
				}
			}
		}
		
		returnString = jsonString.toString();
		jsonString.delete(0, jsonString.length());
		
		return returnString;
	}
}
