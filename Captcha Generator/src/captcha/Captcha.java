package captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import captcha.hash.DefaultHashCalculator;
import captcha.hash.HashCalculator;
import captcha.printer.DefaultImagePrinter;
import captcha.printer.ImagePrinter;

import java.util.Base64;

public class Captcha {

	private final BufferedImage image;
	private final String hashCode;
	private final String imageType;
	private final HashCalculator hashCalculator;
	
	public Captcha(String captchaText) {
		this(captchaText, "jpg");
	}
	
	public Captcha(String captchaText, String imageType) {
		this(captchaText, imageType, new DefaultImagePrinter(), new DefaultHashCalculator());
	}
	
	public Captcha(String captchaText, String imageType, ImagePrinter printer, HashCalculator hashCalculator) {
		this.hashCalculator = hashCalculator;
		hashCode = hashCalculator.calculateHash(captchaText);
		this.imageType = imageType;
		image = printer.createImage(captchaText);
	}
	
	public boolean isMatchingString(String enteredText) {
		return enteredText != null && hashCode.equals(hashCalculator.calculateHash(enteredText));
	}

	public byte[] getImageBytes() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		writeImage(baos);
		byte[] bytes = baos.toByteArray();
		return bytes;
	}
	
	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(getImageBytes());
	}
	
	public void writeImage(OutputStream out) {
		try {
			ImageIO.write(image, imageType, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
