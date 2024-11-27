import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * OFImage is a class that defines an image in OF (Objects First) format.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 2.0
 */
public class OFImage extends BufferedImage
{
    /**
     * Create an OFImage copied from a BufferedImage.
     * @param image The image to copy.
     */
    public OFImage(BufferedImage image)
    {
         super(image.getColorModel(), image.copyData(null), 
               image.isAlphaPremultiplied(), null);
    }

    /**
     * Create an OFImage with specified size and unspecified content.
     * @param width The width of the image.
     * @param height The height of the image.
     */
    public OFImage(int width, int height)
    {
        super(width, height, TYPE_INT_RGB);
    }

    /**
     * Set a given pixel of this image to a specified color. The
     * color is represented as an (r,g,b) value.
     * @param x The x position of the pixel.
     * @param y The y position of the pixel.
     * @param col The color of the pixel.
     */
    public void setPixel(int x, int y, Color col)
    {
        int pixel = col.getRGB();
        setRGB(x, y, pixel);
    }
    
    /**
     * Get the color value at a specified pixel position.
     * @param x The x position of the pixel.
     * @param y The y position of the pixel.
     * @return The color of the pixel at the given position.
     */
    public Color getPixel(int x, int y)
    {
        int pixel = getRGB(x, y);
        return new Color(pixel);
    }
    
    /**
     * Get a subimage from this image (delegating to BufferedImage's getSubimage method).
     * @param x The x coordinate of the top left corner of the subimage.
     * @param y The y coordinate of the top left corner of the subimage.
     * @param width The width of the subimage.
     * @param height The height of the subimage.
     * @return A new OFImage representing the subimage.
     */
    public OFImage getSubimage(int x, int y, int width, int height) {
        BufferedImage subimage = super.getSubimage(x, y, width, height);
        return new OFImage(subimage);
    }
}
