import java.awt.Color;

public class RedTintFilter extends Filter {

    public RedTintFilter(String name) {
        super("Red Tint Filter");
    }

    @Override
    public void apply(OFImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixelColor = image.getRGB(x, y);
                Color color = new Color(pixelColor);

                // Increase the red channel (with a cap at 255)
                int red = Math.min(255, color.getRed() + 50);
                int green = color.getGreen();
                int blue = color.getBlue();

                // Apply the new color
                image.setRGB(x, y, new Color(red, green, blue).getRGB());
            }
        }
    }
}
