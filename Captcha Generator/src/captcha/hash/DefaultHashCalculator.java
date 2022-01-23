package captcha.hash;

public class DefaultHashCalculator implements HashCalculator {

	@Override
	public String calculateHash(String value) {
		return value.hashCode() + "";
	}

}
