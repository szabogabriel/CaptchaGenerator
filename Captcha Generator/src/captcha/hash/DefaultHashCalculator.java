package captcha.hash;

public class DefaultHashCalculator implements HashCalculator {

	private static final long serialVersionUID = 4940445509821742839L;

	@Override
	public String calculateHash(String value) {
		return value.hashCode() + "";
	}

}
