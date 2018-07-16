import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class CanvasView extends JPanel implements Observer {

    private Model model;

    private static final int minWidth = 400, minHeight = 300;

    /**
     * Create a new View.
     */
    public CanvasView(Model model) {
        // Set up the window.
        this.setMinimumSize(new Dimension(minWidth, minHeight));
        this.setSize(500, 400);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));

        // Hook up this observer so that it will be notified when the model changes.
        this.model = model;
        model.addObserver(this);

        setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                model.startDrawStroke(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                model.endDrawStroke();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                model.drawingStroke(e.getPoint());
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        model.paintStrokes(g2d);
    }



    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model changes.
        System.out.println("CanvasView changed!");
        System.out.println(model.getCurMousePoint());
    }
}
