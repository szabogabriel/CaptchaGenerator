package captcha;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import captcha.hash.DefaultHashCalculator;
import captcha.printer.DefaultImagePrinter;

public class CaptchaTest {
	
	File targetFile = new File("test.png");
	
	@BeforeEach
	void initClean() {
		if (targetFile.exists()) {
			targetFile.delete();
		}
	}
	
	@AfterEach
	void afterClean() {
		if (targetFile.exists()) {
			targetFile.delete();
		}
	}
	
	@Test
	void testDefaultCaptcha() throws IOException {
		String input = "aBcd342";
		Captcha c = new Captcha(input, "png", new DefaultImagePrinter(true), new DefaultHashCalculator());
		c.writeImage(new FileOutputStream(targetFile));
		assertTrue(c.isMatchingString(input));
	}
	
}
