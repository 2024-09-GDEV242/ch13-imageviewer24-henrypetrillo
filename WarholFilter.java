import java.awt.Color;
import java.awt.Graphics2D;

public class WarholFilter extends Filter {

    public WarholFilter(String name) {
        super("Warhol Filter");
    }

    @Override
    public void apply(OFImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new image to hold the Warhol-style transformation
        OFImage warholImage = new OFImage(width, height);

        // Draw the original image in the top-left quadrant
        Graphics2D g = warholImage.createGraphics();
        g.drawImage(image, 0, 0, width / 2, height / 2, 0, 0, image.getWidth(), image.getHeight(), null);

        // Apply a red tint to the top-right quadrant
        applyTintToQuadrant(image, warholImage, Color.RED, width / 2, 0, width / 2, height / 2);

        // Apply a green tint to the bottom-left quadrant
        applyTintToQuadrant(image, warholImage, Color.GREEN, 0, height / 2, width / 2, height / 2);

        // Apply a blue tint to the bottom-right quadrant
        applyTintToQuadrant(image, warholImage, Color.BLUE, width / 2, height / 2, width / 2, height / 2);

        // Copy the pixels from warholImage back to image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setPixel(x, y, warholImage.getPixel(x, y));
            }
        }
    }

    // Helper function to apply a tint to a given quadrant of the image
    private void applyTintToQuadrant(OFImage originalImage, OFImage warholImage, Color tint, int xOffset, int yOffset, int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Get the pixel from the original image in the specific quadrant
                Color originalColor = originalImage.getPixel(x + xOffset, y + yOffset);

                // Apply the tint
                Color tintedColor = applyTint(originalColor, tint);

                // Set the tinted pixel to the Warhol image
                warholImage.setPixel(x + xOffset, y + yOffset, tintedColor);
            }
        }
    }

    // Helper function to apply a tint to a given color
    private Color applyTint(Color originalColor, Color tint) {
        int red = Math.min(255, (originalColor.getRed() + tint.getRed()) / 2);
        int green = Math.min(255, (originalColor.getGreen() + tint.getGreen()) / 2);
        int blue = Math.min(255, (originalColor.getBlue() + tint.getBlue()) / 2);
        return new Color(red, green, blue);
    }
}
