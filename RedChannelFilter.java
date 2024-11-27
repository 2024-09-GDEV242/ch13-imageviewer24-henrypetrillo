import java.awt.Color;

/**
 * RedChannelFilter applies a filter that isolates the red channel of an image.
 */
public class RedChannelFilter extends Filter {

    public RedChannelFilter(String name) {
        super("Red Channel Filter");
    }

    public void apply(OFImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixelColor = image.getRGB(x, y);  // Get the pixel color at (x, y)

                // Extract the red component
                int red = (pixelColor >> 16) & 0xFF; // Shift right 16 bits and mask with 0xFF
                
                // Set the pixel color to red only (set green and blue to the same value as red)
                image.setRGB(x, y, new Color(red, red, red).getRGB());
            }
        }
    }
}
