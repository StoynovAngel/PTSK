package org.uni.angel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class TextFileService {

	private static final String SUFFIX = ".txt";
	private static final Path TARGET_FOLDER = Path.of("target_files");

	private static final String PASSWORD = "SPOOKY_SECRET_ATTACK";
	private static final String SALT = "abcdef1234567890";

	private final TextEncryptor encryptor = Encryptors.text(
			PASSWORD, SALT
	);

	public void encryptAll() throws IOException {
		for (var file : getTxtFiles()) {
			var content = Files.readString(file.toPath());
			var encrypted = encryptor.encrypt(content);
			Files.writeString(file.toPath(), encrypted);
			log.info("[ENCRYPTED] {}", file.getName());
		}
	}

	public void decryptAll() throws IOException {
		for (var file : getTxtFiles()) {
			var content = Files.readString(file.toPath());
			var decrypted = encryptor.decrypt(content);
			Files.writeString(file.toPath(), decrypted);
			log.info("[DECRYPTED] {}", file.getName());
		}
	}

	private List<File> getTxtFiles() {
		var files = TARGET_FOLDER.toFile()
				.listFiles(
						(dir, name) -> name.endsWith(SUFFIX)
				);
		return files != null ? Arrays.asList(files) : List.of();
	}
}
