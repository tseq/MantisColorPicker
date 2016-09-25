import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Listen for key events (only for the control button, actually). When the control button is pressed, the
 * ColorPickerFrame object is launched. The key pressed event carries over the the ColorPickerFrame object until the
 * control button is released. At this event, the ColorPickerFrame is disposed.
 *
 * @author Tse Qin
 * @version 9/24/2016
 */
public class KeyManager extends KeyAdapter {
    private Object object;

    /**
     * The constructor accepts the object that utilizes it in order to make callbacks.
     * @param object object that utilizes the listener.
     */
    public KeyManager(Object object) {
        this.object = object;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            // If the object is an instance of Main, launch the ColorPickerFrame.
            if (object instanceof Main)
                ((Main) object).launchColorPickerFrame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            // If the object is an instance of the ColorPickerFrame, dispose the ColorPickerFrame
            if (object instanceof ColorPickerFrame)
                ((ColorPickerFrame) object).finish();
        }
    }
}
