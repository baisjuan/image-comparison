
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageComparator {

	public static void main(String[] args) {
		String imageAPath = "tandil.jpg";
		String imageBPath = "movediza.jpg";
		
		String outputNamePath = "output.png";
		
		try {
			BufferedImage imageA = ImageIO.read(new File(imageAPath));
			BufferedImage imageB = ImageIO.read(new File(imageBPath));
			
			BufferedImage output = generateOutput(imageA,imageB);
			File outputFIle = new File(outputNamePath);
			ImageIO.write(output, "png", outputFIle);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage generateOutput(BufferedImage imageA, BufferedImage imageB) {
		Comparator comparator = new Comparator();
		comparator.compare(imageA, imageB);
		int[][] differencesMatrix = comparator.getDifferencesMatrix();
		
		ImageGenerator imageGenerator = new ImageGenerator();
		return imageGenerator.createImage(imageB, differencesMatrix);
	}

}
