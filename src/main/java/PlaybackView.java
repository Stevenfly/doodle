import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class PlaybackView extends JPanel implements Observer {

    private Model model;

    private static final int minWidth = 400, minHeight = 300;

    /**
     * Create a new View.
     */
    public PlaybackView(Model model) {
        // Set up the window.
        this.setMinimumSize(new Dimension(minWidth, minHeight));
        this.setSize(500, 400);
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

        // Hook up this observer so that it will be notified when the model changes.
        this.model = model;
        model.addObserver(this);

        setVisible(true);

    }



    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model changes.
        System.out.println("PlaybackView changed!");
    }
}
