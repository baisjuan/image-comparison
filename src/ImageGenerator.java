import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageGenerator {

	private static final int RGB = Color.WHITE.getRGB();

	public BufferedImage createImage(BufferedImage original, int[][] differences) {
		BufferedImage outputImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < original.getHeight(); y++) {
			for (int x = 0; x < original.getWidth(); x++) {
				if (differences[x][y] == 1) {
					paintBorder(outputImage, differences, x, y);
				}
				if (differences[x][y] != 2) {
					outputImage.setRGB(x, y, original.getRGB(x, y));
				}
			}
		}
		return outputImage;
	}

	private void paintBorder(BufferedImage original, int[][] differences, int x, int y) {
		setRgb(original,differences,x-1,y);
		setRgb(original,differences,x-1,y-1);
		setRgb(original,differences,x,y-1);
		setRgb(original,differences,x+1,y-1);
		setRgb(original,differences,x+1,y);
		setRgb(original,differences,x+1,y+1);
		setRgb(original,differences,x,y+1);
		setRgb(original,differences,x-1,y+1);
	}

	private void setRgb(BufferedImage image, int[][] differences, int x, int y) {
		if (x > -1 && x < image.getWidth() && y > -1 && y < image.getHeight()) {
			if (differences[x][y] == 0) {
				image.setRGB(x, y, RGB);
				differences[x][y] = 2;
			}
		}
	}
}
