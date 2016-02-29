package aw.utils;

public class StringUtils {
	
	public static String[] splitBySpace(String s){
		int spaces = 0;
		
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == ' ') spaces++;
		}
		
		String[] splitString = new String[spaces + 1];
		int index = 0;
		
		for(int i = 0; i < spaces; i++){
			splitString[i] = "";
		}
		
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == ' ') index++;
			else splitString[index] += s.charAt(i);
		}
		
		return splitString;
	}
}
