package org.uni.angel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleUI {

	private final Scanner scanner = new Scanner(System.in);
	private final TextFileService fileService;

	public void run() throws Exception {
		log.info("""
				========================================
				   RANSOMWARE SIMULATOR (Educational)
				========================================
				""");

		System.out.print("Press Enter to simulate attack...");
		scanner.nextLine();

		log.warn("SIMULATING RANSOMWARE ATTACK...");
		fileService.encryptAll();
		log.error("""
				========================================
				YOUR FILES HAVE BEEN ENCRYPTED!
				Pay the ransom to recover your files.
				========================================
				""");

		System.out.print("Pay now? (yes/no): ");
		var answer = scanner.nextLine().trim().toLowerCase();

		if ("yes".equals(answer)) {
			log.info("Payment confirmed. Decrypting files...");
			fileService.decryptAll();
			log.info("Decryption complete!");
		} else {
			log.warn("Your files remain encrypted. Goodbye!");
		}
	}
}
