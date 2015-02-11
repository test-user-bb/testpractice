//9:47
package com.bawankar.niraj.javapractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SortMap {

	public static void main(String[] args){
		Map<String,Integer> wordMap = new HashMap<String, Integer>();
		
		//wordMap.put("Niraj", 100);
		//wordMap.put("ram", 110);
		//wordMap.put("mohan", 70);
		
		Set<Entry<String,Integer>> wordSet = wordMap.entrySet();
		
		List<Entry<String,Integer>> lst = new ArrayList<Entry<String,Integer>>(wordSet);
		
		Collections.sort(lst, new Comparator<Entry<String,Integer>>() {

			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		for( Entry<String,Integer> ent : lst){
			System.out.println("Key :"+ent.getKey() +" value " +ent.getValue());
		}
		
		
//		Collections.
		
		
	}
}
