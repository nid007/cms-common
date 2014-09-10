package com.github.cms.util;

import java.util.HashSet;
import java.util.Set;

public class Utils {
	
	public static Set<String> parseSet(String[] arr) {
		Set<String> set = new HashSet<String>();
		for(String item:arr){
			set.add(item);
		}
		return set;
	}

}
