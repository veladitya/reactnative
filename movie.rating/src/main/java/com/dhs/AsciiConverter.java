package com.dhs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsciiConverter {

	public static void main(String args[]) {
		String reasonCode = "405>0000";
		Map<Integer, List<Integer>> reasonCds = getAllReasonCds(reasonCode);
		
		reasonCds.forEach((k, v) -> {
			System.out.println("========================");
			System.out.println("Key: "+ k+"\n");
			for(int o : v ) {
				System.out.println(o);
			}
		});
		/*//String reasonCode = "5>";
		int serializedReasonCd;
		List<Integer> reasonCds = null;
		for (char c : reasonCode.toCharArray()) {
			reasonCds = new ArrayList<Integer>(); 
			serializedReasonCd = c;
			serializedReasonCd = serializedReasonCd - 48;
			getReasonCodes(serializedReasonCd, reasonCds); 
			System.out.println("========================");
			for(int o : reasonCds ) {
				System.out.println(o);
			}
		}*/
	}
	
	static Map<Integer, List<Integer>> getAllReasonCds(String serializedReasonCdStr) {
		Map<Integer, List<Integer>> reasonCdMap = null;
		if(serializedReasonCdStr != null) {
			Integer charIndex = 0;
			List<Integer> reasonCds = null;
			int serializedAsciiReasonCd;
			reasonCdMap = new HashMap<>();
			for (char c : serializedReasonCdStr.toCharArray()) {
				++charIndex;
				reasonCds = new ArrayList<Integer>(); 
				serializedAsciiReasonCd = c;
				serializedAsciiReasonCd = serializedAsciiReasonCd - 48;
				getReasonCodes(serializedAsciiReasonCd, reasonCds); 
				reasonCdMap.put(charIndex, reasonCds);			
			}
			return reasonCdMap;
		}
		return reasonCdMap;
	}
	
	static List<Integer> getReasonCodes(int serializedReasonCd, List<Integer> reasonCds) {
		int nthPowerOf2 = (int) (Math.log(serializedReasonCd) / Math.log(2));
		int reasonCd = (int) Math.pow(2, nthPowerOf2);
		int remainingSerializedReasonCd = serializedReasonCd- reasonCd;
		reasonCds.add(reasonCd) ;
		if(remainingSerializedReasonCd > 0) {
			getReasonCodes(remainingSerializedReasonCd, reasonCds);
		}
		return reasonCds;
	}

}
