package captcha.printer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class DefaultImagePrinter implements ImagePrinter {
	
	private static final int FONT_OFFSET = 5;
	private static final int FONT_HEIGHT = 30;
	private static final int FONT_WIDTH = 30;
	private static final int FIXED_HEIGHT = 100;
	private static final int STRIPE_HEIGHT = 10;
	
	private static final Font f = new Font("Default", Font.PLAIN, FONT_HEIGHT);
	
	private final boolean striped;
	
	public DefaultImagePrinter() {
		this(false);
	}
	
	public DefaultImagePrinter(boolean striped) {
		this.striped = striped;
	}

	@Override
	public BufferedImage createImage(String cont) {
		Point size = new Point(cont.length() * FONT_WIDTH, FIXED_HEIGHT);
		BufferedImage image = new BufferedImage(size.x, size.y, BufferedImage.TYPE_INT_RGB);
		char[] content = cont.toCharArray();
		Graphics g = image.getGraphics();
		
		// set background
		g.setColor(Color.gray);
		g.fillRect(0, 0, size.x, size.y);
		
		// set font and its color
		g.setColor(Color.darkGray);
		g.setFont(f);
	
		// draw content to random vertical positions
		for (int i = 0; i < content.length; i++) {
			g.drawChars(new char[] {content[i]}, 0, 1, (i * FONT_WIDTH) + FONT_OFFSET, getRandomHeight());
		}
		
		// draw red horizontal lines, if set 
		if (striped) {
			g.setColor(Color.red);
			for (int i = (STRIPE_HEIGHT / 2); i < FIXED_HEIGHT; i += STRIPE_HEIGHT) {
				g.drawLine(0, i, size.x, i);
			}
		}
		
		return image;
	}
	
	private int getRandomHeight() {
		return (int)(Math.random() * ((double)(FIXED_HEIGHT - (FONT_HEIGHT / 2) - (2 * FONT_OFFSET)))) + (FONT_HEIGHT / 2) + FONT_OFFSET;
	}
	
}
