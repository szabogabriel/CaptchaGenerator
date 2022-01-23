package captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

import javax.imageio.ImageIO;

import captcha.hash.DefaultHashCalculator;
import captcha.hash.HashCalculator;
import captcha.printer.DefaultImagePrinter;
import captcha.printer.ImagePrinter;

public class Captcha implements Serializable {

	private static final long serialVersionUID = 7900360526847852173L;
	
	private final byte[] image;
	private final int width;
	private final int height;
	private final String hashCode;
	private final HashCalculator hashCalculator;
	
	public Captcha(String captchaText) {
		this(captchaText, "jpg");
	}
	
	public Captcha(String captchaText, ImagePrinter printer) {
		this(captchaText, "jpg", printer, new DefaultHashCalculator());
	}

	public Captcha(String captchaText, String imageType, ImagePrinter printer) {
		this(captchaText, imageType, printer, new DefaultHashCalculator());
	}
	
	public Captcha(String captchaText, String imageType) {
		this(captchaText, imageType, new DefaultImagePrinter(), new DefaultHashCalculator());
	}
	
	public Captcha(String captchaText, String imageType, ImagePrinter printer, HashCalculator hashCalculator) {
		this.hashCalculator = hashCalculator;
		this.hashCode = hashCalculator.calculateHash(captchaText);
		
		BufferedImage image = printer.createImage(captchaText);
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, imageType, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.image = baos.toByteArray();
	}
	
	public boolean isMatchingString(String enteredText) {
		return enteredText != null && hashCode.equals(hashCalculator.calculateHash(enteredText));
	}

	public byte[] getImageBytes() {
		return image;
	}
	
	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(getImageBytes());
	}
	
	public void writeImage(OutputStream out) throws IOException {
		out.write(image);
	}
	
	public int getImageWidht() {
		return width;
	}
	
	public int getImageHeight() {
		return height;
	}
	
	public String getStoredHash() {
		return hashCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Objects.hash(hashCode);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Captcha other = (Captcha) obj;
		return Objects.equals(hashCode, other.hashCode) && Arrays.equals(image, other.image);
	}
	
}
