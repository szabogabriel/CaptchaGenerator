package captcha;

public class StringGenerator {
	
	public static final char[] DEFAULT_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
	
	public static String generateString(int length) {
		return generateString(DEFAULT_ALPHABET, length);
	}
	
	public static String generateString(char[] alphabet, int length) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++) {
			ret.append(alphabet[getRandomPosition(alphabet.length)]);
		}
		return ret.toString();
	}
	
	private static int getRandomPosition(int size) {
		return (int)(Math.random() * (double)(size));
	}

}
