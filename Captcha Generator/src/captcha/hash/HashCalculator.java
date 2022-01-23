package captcha.hash;

import java.io.Serializable;

public interface HashCalculator extends Serializable {
	
	String calculateHash(String value);

}
