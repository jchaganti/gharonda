package org.jchlabs.gharonda.common;

import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class Thumbnail {
	/**
	 * Create a reduced jpeg version of an image. The width/height ratio is preserved.
	 * 
	 * @param data raw data of the image
	 * @param thumbWidth maximum width of the reduced image
	 * @param thumbHeight maximum heigth of the reduced image
	 * @param quality jpeg quality of the reduced image
	 * @return a reduced jpeg image if the image represented by data is bigger than the maximum dimensions of the
	 * reduced image, otherwise data is returned
	 */
	private Image image;

	public static byte[] createThumbArray(byte[] data, int thumbWidth, int thumbHeight, int quality) throws Exception {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		createThumb(data, thumbWidth, thumbHeight, quality, result);
		return result.toByteArray();
	}

	/**
	 * Create a reduced jpeg version of an image. The width/height ratio is preserved.
	 * 
	 * @param data raw data of the image
	 * @param thumbWidth maximum width of the reduced image
	 * @param thumbHeight maximum heigth of the reduced image
	 * @param quality jpeg quality of the reduced image
	 * @return a reduced jpeg image if the image represented by data is bigger than the maximum dimensions of the
	 * reduced image, otherwise data is returned
	 */
	public byte[] createThumbArray(InputStream data, int thumbWidth, int thumbHeight, int quality) throws Exception {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		createThumb(data, thumbWidth, thumbHeight, quality, result);
		return result.toByteArray();
	}

	/**
	 * Create a reduced jpeg version of an image. The width/height ratio is preserved.
	 * 
	 * @param data raw data of the image
	 * @param thumbWidth maximum width of the reduced image
	 * @param thumbHeight maximum heigth of the reduced image
	 * @param quality jpeg quality of the reduced image
	 * @param out produce a reduced jpeg image if the image represented by data is bigger than the maximum dimensions of
	 * the reduced image, otherwise data is written to this stream
	 */
	public static void createThumb(byte[] data, int thumbWidth, int thumbHeight, int quality, OutputStream out)
			throws Exception {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		// Image image = Toolkit.getDefaultToolkit().createImage(data);
		Image image = ImageIO.read(new ByteArrayInputStream(data));
		if (image.getWidth(null) <= thumbWidth && image.getHeight(null) <= thumbHeight)
			out.write(data);
		else
			createThumbInternal(image, thumbWidth, thumbHeight, quality, out);
	}

	/**
	 * Create a reduced jpeg version of an image. The width/height ratio is preserved.
	 * 
	 * @param data raw data of the image
	 * @param thumbWidth maximum width of the reduced image
	 * @param thumbHeight maximum heigth of the reduced image
	 * @param quality jpeg quality of the reduced image
	 * @param out produce a reduced jpeg image if the image represented by data is bigger than the maximum dimensions of
	 * the reduced image, otherwise data is written to this stream
	 */
	public void createThumb(InputStream data, int thumbWidth, int thumbHeight, int quality, OutputStream out)
			throws Exception {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		if (image == null)
			image = ImageIO.read(data);
		if (image.getWidth(null) <= thumbWidth && image.getHeight(null) <= thumbHeight) {
			// Send back the data as output stream
			BufferedInputStream bufferedInputStream = new BufferedInputStream(data);
			int d = -1;
			while ((d = bufferedInputStream.read()) != -1) {
				out.write(d);
			}
			bufferedInputStream.close();
		} else
			createThumbInternal(image, thumbWidth, thumbHeight, quality, out);
	}

	private static void createThumbInternal(Image image, int thumbWidth, int thumbHeight, int quality, OutputStream out)
			throws Exception {

		MediaTracker mediaTracker = new MediaTracker(new Panel());
		int trackID = 0;
		mediaTracker.addImage(image, trackID);
		mediaTracker.waitForID(trackID);
		createThumbUsingImg(image, thumbWidth, thumbHeight, quality, out);
	}

	/**
	 * Create a scaled jpeg of an image. The width/height ratio is preserved.
	 * 
	 * <p>
	 * If image is smaller than thumbWidth x thumbHeight, it will be magnified, otherwise it will be scaled down.
	 * </p>
	 * 
	 * @param image the image to reduce
	 * @param thumbWidth the maximum width of the thumbnail
	 * @param thumbHeight the maximum heigth of the thumbnail
	 * @param quality the jpeg quality ot the thumbnail
	 * @param out a stream where the thumbnail data is written to
	 */
	public static void createThumbUsingImg(Image image, int thumbWidth, int thumbHeight, int quality, OutputStream out)
			throws Exception {
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		double imageRatio = (double) imageWidth / (double) imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}
		// draw original image to thumbnail image object and
		// scale it to the new size on-the-fly
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		// save thumbnail image to out stream
		ImageIO.write(thumbImage, "jpeg", out);
	}
}
