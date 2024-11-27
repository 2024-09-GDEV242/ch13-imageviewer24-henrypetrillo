import java.awt.Color;

public class BlueTintFilter extends Filter {

    public BlueTintFilter(String name) {
        super("Blue Tint Filter");
    }

    @Override
    public void apply(OFImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixelColor = image.getRGB(x, y);
                Color color = new Color(pixelColor);

                // Increase the blue channel (with a cap at 255)
                int blue = Math.min(255, color.getBlue() + 50);
                int red = color.getRed();
                int green = color.getGreen();

                // Apply the new color
                image.setRGB(x, y, new Color(red, green, blue).getRGB());
            }
        }
    }
}
