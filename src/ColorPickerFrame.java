import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * This class is only launched when the control button is pressed. It is a customized JFrame with an alpha value of 1
 * that is placed above the Main object in order to intercept mouse clicks on the screen. It utilizes MouseManager as
 * its mouseListener in order to obtain the coordinates of the pixel selected. Once the position is obtained, calls
 * are made to the functions in GraphicsManager to obtain the color and hexadecimal values of the pixel at position
 * (X, Y). These values are translated into visual aids and displayed to the user.
 *
 * @author Tse Qin
 * @version 9/24/2016
 */
public class ColorPickerFrame extends JFrame {
    private int prevPosX, prevPosY;
    private static final int RECT_WIDTH = 100, RECT_HEIGHT = 100;

    /**
     * Constructor for ColorPickerFrame. Initializes the look and listeners.
     */
    public ColorPickerFrame() {
        super();
        customizeLook();
        addListener();
    }

    /**
     * Set background with alpha value of 1. This creates an almost transparent window that intercepts mouse clicks.
     * An alpha value of 0 does not intercept mouse clicks. Also remove all decorations for the frame.
     */
    private void customizeLook() {
        setUndecorated(true);
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        setSize((int) screenRect.getWidth(), (int) screenRect.getHeight());
        setBackground(new Color(255, 255, 255, 1));
        setLayout(new BorderLayout());
    }

    /**
     * Add key and mouse listeners.
     */
    private void addListener() {
        MouseManager mouseManager = new MouseManager(this);
        addMouseMotionListener(mouseManager);
        addMouseListener(mouseManager);
        addKeyListener(new KeyManager(this));
    }

    /**
     * Display the hexadecimal value and the color of the pixel at position X and Y.
     * @param posX x-coordinate of pixel
     * @param posY y-coordinate of pixel
     */
    public void displayHexValue(int posX, int posY) {
        // Clear JFrame if previousX or previousY is different. Otherwise, do not draw a new JPanel.
        if (posX != prevPosX || posY != prevPosY) {
            getContentPane().removeAll();
            revalidate();
            repaint();
        } else {
            return;
        }
        // Get color and compute hex value
        Color color = GraphicsManager.getColor(posX, posY);
        String hexValue = GraphicsManager.colorToHex(color);
        Color contrast = getContrast(color);
        // Create panel
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Fill rectangle
                g.setColor(color);
                g.fillRect(posX, posY, RECT_WIDTH, RECT_HEIGHT);
                // Label rectangle with hexValue
                g.setColor(contrast);
                g.setFont(new Font("default", Font.BOLD, 16));
                FontMetrics fontMetrics = g.getFontMetrics();
                int stringWidth = fontMetrics.stringWidth(hexValue) / 2;
                g.drawString(hexValue, (posX + (RECT_WIDTH / 2) - stringWidth) , (posY + (RECT_HEIGHT / 2)));
            }
        };
        panel.setBackground(new Color(0, 0, 0, 0));
        add(panel);
        // Save hex value to clipboard
        saveToClipboard(hexValue);
        // Set visibility
        setVisible(true);
        // Save previous X & Y
        prevPosX = posX;
        prevPosY = posY;
    }

    /**
     * Save the hexadecimal value to clipboard.
     *
     * @param hexValue value to be saved to clipboard
     */
    private void saveToClipboard(String hexValue) {
        StringSelection selection = new StringSelection(hexValue);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    /**
     * Compute the contrasting color. Algorithm obtained from:
     * http://stackoverflow.com/questions/1855884/determine-font-color-based-on-background-color
     *
     * @param color color for which contrast is computed
     * @return contrast
     */
    public Color getContrast(Color color) {
        int rgbVal = 0;
        // Computing the perceptive luminance - human eye favors green color.
        double luminance = 1 - (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        // Compare luminance.
        if (luminance < 0.5)
            rgbVal = 0; // Bright colors - black font
        else
            rgbVal = 255; // Dark colors - white font
        return new Color(rgbVal, rgbVal, rgbVal);
    }

    /**
     * Dispose the frame. This function is called when the user releases the control key.
     */
    public void finish() {
        dispose();
    }
}