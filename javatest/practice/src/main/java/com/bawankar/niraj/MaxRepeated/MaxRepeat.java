package com.bawankar.niraj.MaxRepeated;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//9:06

public class MaxRepeat {

	static File file = new File("/Users/niraj/Documents/workspace-sts-3.6.3.SR1/testpractice/javatest/practice/src/tmpfile.words");

	static Map<String,Integer> wordMap = new HashMap<String, Integer>();

	public static void main(String[] args) throws IOException{

		FileInputStream fis=null;
		BufferedReader br=null;
		int maxValue=0;
		String maxWord=null;
		try {
			fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String line=null;
		while((line=br.readLine()) != null){
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens()){
				String word = st.nextToken();
				if(wordMap.get(word)==null){
					wordMap.put(word, 0);
				}else{
					int value = wordMap.get(word)+1;
					if(value> maxValue){
						maxValue=value;
						maxWord=word;
					}
					wordMap.put(word,value );
				}

			}
		}

		System.out.println("Max Owrd is: "+maxWord + " with count "+maxValue);


	}
}

//9:26

