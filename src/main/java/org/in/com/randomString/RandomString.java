package org.in.com.randomString;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class RandomString {
	

	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();

	public String generateRandomString(int length) {
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = RANDOM.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}

		return stringBuilder.toString();
	}

}
