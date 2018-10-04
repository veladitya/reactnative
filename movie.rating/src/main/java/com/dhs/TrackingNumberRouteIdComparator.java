package com.dhs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrackingNumberRouteIdComparator {

	public static List<String> readFileInList(String fileName) {

		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	List<String> allLines = null;
	Map<String, Set<String>> trackingNumRouteMap = new HashMap<>();
	String[] tokens = null;
	Set<String> routeIds;

	public static void main(String args[]) {
		TrackingNumberRouteIdComparator comparator = new TrackingNumberRouteIdComparator();
		File folder = new File("./data");
		File[] listOfFiles = folder.listFiles();
		comparator.processData(listOfFiles);
		comparator.printData();
	}

	private void printData() {
		StringBuffer buffer = new StringBuffer();
		trackingNumRouteMap.forEach((k, v) -> {
			if (v.size() >= 2) {
				buffer.append(k + "," + v.toString().replaceAll("\\[", "").replaceAll("\\]", "")+"\n");
			}
		});
		try {
			writeDiference("./data/test.txt", buffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeDiference(String filePath, String data) throws IOException {
		

		Path path = Paths.get(filePath);
		byte[] strToBytes = data.getBytes();

		Files.write(path, strToBytes);

		String read = Files.readAllLines(path).get(0);
	}

	public void processData(File[] listOfFiles) {
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				allLines = readFileInList(listOfFiles[i].getPath());

				allLines.forEach(l -> {
					tokens = l.split("\t");
					if (trackingNumRouteMap.get(tokens[0]) == null) {
						trackingNumRouteMap.put(tokens[0], new HashSet<>());
						routeIds = trackingNumRouteMap.get(tokens[0]);
					} else {
						routeIds = trackingNumRouteMap.get(tokens[0]);
					}
					if (tokens.length >= 2)
						routeIds.add(tokens[1]);
					else
						routeIds.add("");
				});
			}
		}
	}
}
