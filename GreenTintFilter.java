import java.awt.Color;

public class GreenTintFilter extends Filter {

    public GreenTintFilter(String name) {
        super("Green Tint Filter");
    }

    @Override
    public void apply(OFImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixelColor = image.getRGB(x, y);
                Color color = new Color(pixelColor);

                // Increase the green channel (with a cap at 255)
                int green = Math.min(255, color.getGreen() + 50);
                int red = color.getRed();
                int blue = color.getBlue();

                // Apply the new color
                image.setRGB(x, y, new Color(red, green, blue).getRGB());
            }
        }
    }
}
