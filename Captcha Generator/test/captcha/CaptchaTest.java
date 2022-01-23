package captcha;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.jupiter.api.Test;

import captcha.hash.DefaultHashCalculator;
import captcha.printer.DefaultImagePrinter;

public class CaptchaTest {
	
	@Test
	void testDefaultCaptcha() throws FileNotFoundException {
		Captcha c = new Captcha("aBcd342", "jpg", new DefaultImagePrinter(true), new DefaultHashCalculator());
		c.writeImage(new FileOutputStream("test.jpg"));
	}
	
}
