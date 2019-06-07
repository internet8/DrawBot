package drawBot;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class App {

	public void draw(BufferedImage bf) throws AWTException, IOException, InterruptedException {
		Bot bot = new Bot();
		App app = new App();
		//bot.MoveMouseControlled(0.5, 0.5);
		BufferedImage image = null;
		//URL url = new URL("https://amp.thisisinsider.com/images/5b7acee73cccd122008b45ac-750-563.jpg");
		//image = ImageIO.read(url);
		image = bf;
		
		// colors
		ArrayList<SColor> colors = new ArrayList<SColor>();
		ArrayList<FinalResult> fn = new ArrayList<FinalResult>();
		colors.add(new SColor(1, 255, 255, 255, 590, 850));
		colors.add(new SColor(2, 193, 193, 193, 610, 850));
		colors.add(new SColor(3, 239, 19, 11, 630, 850));
		colors.add(new SColor(4, 255, 113, 0, 660, 850));
		colors.add(new SColor(5, 255, 228, 0, 680, 850));
		colors.add(new SColor(6, 0, 204, 0, 700, 850));
		colors.add(new SColor(7, 0, 178, 255, 720, 850));
		colors.add(new SColor(8, 35, 31, 211, 760, 850));
		colors.add(new SColor(9, 163, 0, 186, 780, 850));
		colors.add(new SColor(10, 211, 124, 170, 800, 850));
		colors.add(new SColor(11, 160, 82, 45, 820, 850));
		colors.add(new SColor(12, 0, 0, 0, 590, 875));
		colors.add(new SColor(13, 76, 76, 76, 610, 875));
		colors.add(new SColor(14, 116, 11, 7, 630, 875));
		colors.add(new SColor(15, 194, 56, 0, 660, 875));
		colors.add(new SColor(16, 232, 162, 0, 680, 875));
		colors.add(new SColor(17, 0, 85, 16, 700, 875));
		colors.add(new SColor(18, 0, 86, 158, 720, 875));
		colors.add(new SColor(19, 14, 8, 101, 760, 875));
		colors.add(new SColor(20, 85, 0, 105, 780, 875));
		colors.add(new SColor(21, 167, 85, 116, 800, 875));
		colors.add(new SColor(22, 99, 48, 13, 820, 875));
		int bestX = 0;
		int bestY = 0;
		int width = image.getWidth();
		int height = image.getHeight();
		
		while (width > 520 || height > 520) {
			width /= 1.2;
			height /= 1.2;
		}
		image = app.scale(image, width, height);
		
		Thread.sleep(2000);
		for (int x = 0; x < image.getWidth(); x+=3) {
			for (int y = 0; y < image.getHeight(); y+=3) {
				int color = image.getRGB(x, y);

				int red = (color & 0x00ff0000) >> 16;
				int green = (color & 0x0000ff00) >> 8;
				int blue = color & 0x000000ff;
				int bestVal = 765;
				SColor best = null;
				for (SColor c : colors) {
					int val = Math.abs(red-c.getRed()) + Math.abs(green-c.getGreen()) + Math.abs(blue-c.getBlue());
					if (val <= bestVal) {
						bestVal = val;
						best = c;
						bestX = x + 550;
						bestY = y + 300;
					}
				}
				// alt drawing
				fn.add(new FinalResult(bestX, bestY, 0, best.getID()));
				// original drawing
//				bot.click(best.getX(), best.getY());
//				bot.click(x + 550, y + 300);
//				Thread.sleep(5);
			}
		}
		// alt drawing
		int currentX = 550;
		int currentY = 300;
		for (SColor c : colors) {
			Thread.sleep(1);
			bot.click(c.getX(), c.getY());
			bot.clickDown(currentX, currentY);
			for (FinalResult f : fn) {
				if (c.getID() == f.getId() && c.getID() != 1) {
					//Thread.sleep(1);
					if (currentX == f.getX() && f.getY() - currentY == 1) {
						bot.move(f.getX(), f.getY());
					} else {
						currentX = f.getX();
						bot.release(f.getX(), f.getY());
						bot.move(f.getX(), f.getY());
						bot.clickDown(f.getX(), f.getY());
					}
					currentY = f.getY();
					//bot.click(f.getX(), f.getY());
				}
			}
			bot.release(0, 0);
		}
	}

	
	public static BufferedImage scale(BufferedImage src, int w, int h)
	{
	    BufferedImage img = 
	            new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    int x, y;
	    int ww = src.getWidth();
	    int hh = src.getHeight();
	    int[] ys = new int[h];
	    for (y = 0; y < h; y++)
	        ys[y] = y * hh / h;
	    for (x = 0; x < w; x++) {
	        int newX = x * ww / w;
	        for (y = 0; y < h; y++) {
	            int col = src.getRGB(newX, ys[y]);
	            img.setRGB(x, y, col);
	        }
	    }
	    return img;
	}
}
