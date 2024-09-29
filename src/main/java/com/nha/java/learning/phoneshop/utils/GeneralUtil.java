package com.nha.java.learning.phoneshop.utils;

import java.util.List;

public class GeneralUtil {
	
	//convert list of string to list of integer
	
	public static List<Integer> toInteger(List<String> list){
		return list.stream()
			.map(s -> s.length())
			.toList();
	}
	
	public static List<Integer> getEventNumber(List<Integer> list){
		return list.stream().filter(x -> x%2 == 0).toList();
	}
}
