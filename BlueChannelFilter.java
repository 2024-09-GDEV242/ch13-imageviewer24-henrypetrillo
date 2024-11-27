import java.awt.Color;

public class BlueChannelFilter extends Filter {

    public BlueChannelFilter(String name) {
        super("Blue Channel Filter");
    }

    @Override
    public void apply(OFImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixelColor = image.getRGB(x, y);

                // Extract the blue component
                int blue = pixelColor & 0xFF; // Mask with 0xFF to get the blue component

                // Set the pixel color to blue only (set red and green to the same value as blue)
                image.setRGB(x, y, new Color(blue, blue, blue).getRGB());
            }
        }
    }
}
