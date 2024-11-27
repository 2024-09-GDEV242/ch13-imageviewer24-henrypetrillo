import java.awt.Color;
import java.awt.Graphics2D;

public class FlippedWarholFilter extends Filter {

    public FlippedWarholFilter(String name) {
        super("Flipped Warhol Filter");
    }

    @Override
    public void apply(OFImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new image to hold the Flipped Warhol-style transformation
        OFImage warholImage = new OFImage(width, height);

        // Draw the original image in the top-left quadrant (same as before)
        Graphics2D g = warholImage.createGraphics();
        g.drawImage(image, 0, 0, width / 2, height / 2, 0, 0, image.getWidth(), image.getHeight(), null);

        // Apply red tint to top-right quadrant and mirror horizontally
        applyTintToQuadrant(image, warholImage, Color.RED, width / 2, 0, width / 2, height / 2, true, false);

        // Apply green tint to bottom-left quadrant and mirror vertically
        applyTintToQuadrant(image, warholImage, Color.GREEN, 0, height / 2, width / 2, height / 2, false, true);

        // Apply blue tint to bottom-right quadrant and mirror both horizontally and vertically
        applyTintToQuadrant(image, warholImage, Color.BLUE, width / 2, height / 2, width / 2, height / 2, true, true);

        // Copy the pixels from warholImage back to image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setPixel(x, y, warholImage.getPixel(x, y));
            }
        }
    }

    // Helper function to apply a tint to a given quadrant of the image, with mirroring options
    private void applyTintToQuadrant(OFImage originalImage, OFImage warholImage, Color tint, 
                                     int xOffset, int yOffset, int width, int height,
                                     boolean mirrorHorizontal, boolean mirrorVertical) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Calculate the actual coordinates for mirroring
                int mirrorX = mirrorHorizontal ? width - x - 1 : x;
                int mirrorY = mirrorVertical ? height - y - 1 : y;

                // Get the pixel from the original image in the specific quadrant
                Color originalColor = originalImage.getPixel(x + xOffset, y + yOffset);

                // Apply the tint
                Color tintedColor = applyTint(originalColor, tint);

                // Set the tinted pixel to the Warhol image with mirroring applied
                warholImage.setPixel(mirrorX + xOffset, mirrorY + yOffset, tintedColor);
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
