package captcha.printer;

import java.awt.image.BufferedImage;

public interface ImagePrinter {
	
	public abstract BufferedImage createImage(String content);
	
}
