import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Handle graphics functions.
 *
 * @author Tse Qin
 * @version 9/24/2016
 */
public class GraphicsManager {
    private static BufferedImage screenCapture;

    /**
     * Create and save the screen capture.
     */
    public static void captureScreen() {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        screenCapture = null;
        try {
            // Create screen capture.
            screenCapture = new Robot().createScreenCapture(screenRect);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compute the hexadecimal value of a pixel at position (X, Y).
     * @param posX x-coordinate of pixel
     * @param posY y-coordinate of pixel
     * @return null if there is an error, the color object of a pixel if there are no errors.
     */
    public static Color getColor(int posX, int posY) {
        // Perform error checking
        if (screenCapture == null)
            return null;
        // Return the color of the pixel
        return new Color(screenCapture.getRGB(posX, posY));
    }

    /**
     * Parse color object into hexadecimal.
     * @param color color object ot be parsed into hexadecimal
     * @return hexadecimal value prepended with #.
     */
    public static String colorToHex(Color color) {
        // Perform error checking
        if (color == null)
            return null;
        // Convert red, green, blue values to # + hexRed + hexGreen + hexBlue
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
