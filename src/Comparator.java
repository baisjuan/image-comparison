
import java.awt.image.BufferedImage;

public class Comparator {

	private static final int MIN_DISTANCE = 45;

	private int[][] differencesMatrix;	
	
	public void compare(BufferedImage imageA, BufferedImage imageB) {
		
		int minWidth = imageA.getWidth() < imageB.getWidth() ? imageA.getWidth() : imageB.getWidth();
		int minHeight = imageA.getHeight() < imageB.getHeight() ? imageA.getHeight() : imageB.getHeight();
		
		initMatrix(minWidth,minHeight);
		
		for (int y = 0; y < minHeight; y++) {
			for (int x = 0; x < minHeight; x++) {
				if (areDifferent(imageA.getRGB(x, y),imageB.getRGB(x, y))) {
					differencesMatrix[x][y] = 1;
				}
			}
		}
	}
	
	private void initMatrix(int minWidth, int minHeight) {
		differencesMatrix = new int[minWidth][minHeight];
		for (int y = 0; y < minHeight; y++) {
			for (int x = 0; x < minWidth; x++) {
				differencesMatrix[x][y] = 0;
			}
		}
	}

	private boolean areDifferent(int rgbA, int rgbB) {
		int  redA   = (rgbA & 0x00ff0000) >> 16;
        int  greenA = (rgbA & 0x0000ff00) >> 8;
        int  blueA  =  rgbA & 0x000000ff;
        
        int  redB   = (rgbB & 0x00ff0000) >> 16;
        int  greenB = (rgbB & 0x0000ff00) >> 8;
        int  blueB  =  rgbB & 0x000000ff;
        
        return Math.sqrt(Math.pow(redA-redB, 2) + Math.pow(greenA-greenB, 2) + Math.pow(blueA-blueB, 2)) > MIN_DISTANCE;
	}

	public int[][] getDifferencesMatrix() {
		return differencesMatrix;
		
	}
}
