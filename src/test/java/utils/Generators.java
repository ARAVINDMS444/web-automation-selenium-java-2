package utils;

import java.util.Random;

public class Generators {
    private static final Random random = new Random();

    /**
     * Generates a random string of uppercase letters of the specified length.
     */
    public static String generateRandomUppercaseString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return generateRandomStringFrom(characters, length);
    }

    /**
     * Generates a random string of lowercase letters of the specified length.
     */
    public static String generateRandomLowercaseString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        return generateRandomStringFrom(characters, length);
    }

    /**
     * Generates a random string of both uppercase and lowercase letters of the specified length.
     */
    public static String generateRandomCaseSensitiveString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return generateRandomStringFrom(characters, length);
    }

    /**
     * Generates a random number between the specified min and max values (inclusive).
     */
    public static int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generates a random string of uppercase letters and numbers of the specified length.
     */
    public static String generateRandomStringAndNumbersUppercase(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return generateRandomStringFrom(characters, length);
    }

    /**
     * Generates a random string of lowercase letters and numbers of the specified length.
     */
    public static String generateRandomStringAndNumbersLowercase(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        return generateRandomStringFrom(characters, length);
    }

    /**
     * Generates a random string of uppercase and lowercase letters, and numbers of the specified length.
     */
    public static String generateRandomStringAndNumbersCaseSensitive(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return generateRandomStringFrom(characters, length);
    }

    /**
     * Generates a random phone number of the specified length (numeric only).
     */
    public static String generatePhoneNumber(int length) {
        String numbers = "0123456789";
        return generateRandomStringFrom(numbers, length);
    }

    /**
     * Generic utility to generate a string from a given set of characters.
     */
    private static String generateRandomStringFrom(String characters, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}