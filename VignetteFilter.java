import java.awt.Color;

/**
 * The Vignette Filter is designed with a darker look
 * around the edges to create a spotlight effect in the middle
 * of the image.
 */

public class VignetteFilter extends Filter {

    public VignetteFilter(String name) {
        super(name);
    }

    @Override
    public void apply(OFImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Calculate the center of the image
        int centerX = width / 2;
        int centerY = height / 2;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Calculate the distance from the center of the image
                double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));

                // Apply a vignette effect, darkening the edges
                double factor = Math.min(1.0, distance / Math.max(centerX, centerY));

                // Get the original pixel color
                Color originalColor = image.getPixel(x, y);

                // Reduce the brightness based on the distance
                int red = (int)(originalColor.getRed() * (1 - factor));
                int green = (int)(originalColor.getGreen() * (1 - factor));
                int blue = (int)(originalColor.getBlue() * (1 - factor));

                // Set the new pixel color
                image.setPixel(x, y, new Color(red, green, blue));
            }
        }
    }
}
