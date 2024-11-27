import java.awt.Color;

public class SepiaFilter extends Filter {

    public SepiaFilter(String name) {
        super(name);
    }

    @Override
    public void apply(OFImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color originalColor = image.getPixel(x, y);

                // Get the RGB components
                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();

                // Apply the sepia transformation
                int newRed = Math.min(255, (int)(red * 0.393 + green * 0.769 + blue * 0.189));
                int newGreen = Math.min(255, (int)(red * 0.349 + green * 0.686 + blue * 0.168));
                int newBlue = Math.min(255, (int)(red * 0.272 + green * 0.534 + blue * 0.131));

                // Set the new sepia color
                image.setPixel(x, y, new Color(newRed, newGreen, newBlue));
            }
        }
    }
}
