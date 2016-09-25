import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listen for mouse events. The coordinates of the mouse are returned in callbacks that trigger the ColorPickerFrame
 * to compute the color of the pixel at position (X, Y).
 *
 * @author Tse Qin
 * @version 9/24/2016
 */
public class MouseManager extends MouseAdapter {
    private ColorPickerFrame frame;

    /**
     * The constructor accepts the ColorPickerFrame object that utilizes it in order to make callbacks.
     * @param frame ColorPickerFrame object that utilizes the listener.
     */
    public MouseManager(ColorPickerFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        frame.displayHexValue(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        frame.displayHexValue(e.getX(), e.getY());
    }
}
