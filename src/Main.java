import javax.swing.*;

/**
 * The Mantis color picker tool is just another color picker that translates a color of a pixel to its hexadecimal
 * value. Why Mantis? Mantis prawns are colorful...
 *
 * The main class initializes the main frame and specifies the instructions on how to use this tool.
 *
 * @author Tse Qin
 * @version 9/24/2016
 */
public class Main {
    private static final String PROGRAM_NAME = "Mantis Color Picker";
    private static final String PROGRAM_INSTRUCTIONS = "<html>Hold CTRL + left click on any area on the " +
            "screen to select a color.<br>The hexadecimal value will be saved to your clipboard automatically.</html>";
    private JFrame frame;
    private JPanel mainPanel;
    private boolean hasTranslucentFrame;

    /**
     * Start the program.
     */
    public void start() {
        // Initialize settings for frame & panel
        init();
        // Set key manager
        frame.addKeyListener(new KeyManager(this));
        // Set hasTranslucentFrame to false
        hasTranslucentFrame = false;
    }

    /**
     * Initialize the frame with instructions.
     */
    private void init() {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // Initialize frame settings.
        frame = new JFrame(PROGRAM_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Initialize panel with instructions
        mainPanel = new JPanel();
        JLabel label = new JLabel(PROGRAM_INSTRUCTIONS); // Create instructions label
        mainPanel.add(label); // Add instructions to panel
        frame.add(mainPanel); // Add instructions to frame
        // Show
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method is called the first time control button is pressed. A call to captureScreen() is made in order to
     * save the screenshot before the launch of the color picker frame, where the key algorithm resides.
     */
    public void launchColorPickerFrame() {
        GraphicsManager.captureScreen();
        ColorPickerFrame translucentFrame = new ColorPickerFrame();
        translucentFrame.setVisible(true);
    }

    /**
     * Main method to start program.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
