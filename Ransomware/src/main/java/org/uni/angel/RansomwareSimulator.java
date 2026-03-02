package org.uni.angel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RansomwareSimulator {

	public static void main(String[] args) {
		try {
			new ConsoleUI(new TextFileService()).run();
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage());
		}
	}
}
