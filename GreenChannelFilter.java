import java.awt.Color;

public class GreenChannelFilter extends Filter {

    public GreenChannelFilter(String name) {
        super("Green Channel Filter");
    }

    @Override
    public void apply(OFImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixelColor = image.getRGB(x, y);

                // Extract the green component
                int green = (pixelColor >> 8) & 0xFF; // Shift right 8 bits and mask with 0xFF

                // Set the pixel color to green only (set red and blue to the same value as green)
                image.setRGB(x, y, new Color(green, green, green).getRGB());
            }
        }
    }
}
