import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class PaletteView extends JPanel implements Observer {

    private Model model;

    private JPanel buttonPanel;

    private JLabel colorLabel;
    private JButton colorButton1;
    private JButton colorButton2;
    private JButton colorButton3;
    private JButton colorButton4;
    private JButton colorButton5;
    private JButton colorButton6;

    private JButton colorChooserButton;
    private JLabel widthLabel;
    private JSlider widthSlider;

    private JLabel showLabel;
    private JPanel showPanel;
    private JLabel showColor;

    /**
     * Constants.
     */
    private static final int minWidth = 100, minHeight = 300;
    private static final int WIDTH_MIN = 1;
    private static final int WIDTH_MAX = 10;
    private static final int WIDTH_INIT = 1;

    /**
     * Create a new View.
     */
    public PaletteView(Model model) {
        // Set up the window.
        this.setMinimumSize(new Dimension(minWidth, minHeight));
        this.setSize(100, 400);
//        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridLayout(0, 1));

        // Hook up this observer so that it will be notified when the model changes.
        this.model = model;
        model.addObserver(this);

        setVisible(true);

        // Color Buttons
        colorLabel = new JLabel("Select stroke color:");

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2));

        colorButton1 = new JButton();
        colorButton1.setOpaque(true);
        colorButton1.setBorderPainted(false);
        colorButton1.setBackground(Color.black);

        colorButton2 = new JButton();
        colorButton2.setOpaque(true);
        colorButton2.setBorderPainted(false);
        colorButton2.setBackground(Color.green);

        colorButton3 = new JButton();
        colorButton3.setOpaque(true);
        colorButton3.setBorderPainted(false);
        colorButton3.setBackground(Color.blue);

        colorButton4 = new JButton();
        colorButton4.setOpaque(true);
        colorButton4.setBorderPainted(false);
        colorButton4.setBackground(Color.yellow);

        colorButton5 = new JButton();
        colorButton5.setOpaque(true);
        colorButton5.setBorderPainted(false);
        colorButton5.setBackground(Color.red);

        colorButton6 = new JButton();
        colorButton6.setOpaque(true);
        colorButton6.setBorderPainted(false);
        colorButton6.setBackground(Color.orange);

        buttonPanel.add(colorButton1);
        buttonPanel.add(colorButton2);
        buttonPanel.add(colorButton3);
        buttonPanel.add(colorButton4);
        buttonPanel.add(colorButton5);
        buttonPanel.add(colorButton6);

        colorButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurColor(Color.BLACK);
            }
        });
        colorButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurColor(Color.GREEN);
            }
        });
        colorButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurColor(Color.BLUE);
            }
        });
        colorButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurColor(Color.YELLOW);
            }
        });
        colorButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurColor(Color.RED);
            }
        });
        colorButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurColor(Color.ORANGE);
            }
        });

        this.add(colorLabel);
        this.add(buttonPanel);

        // Color Chooser
        colorChooserButton = new JButton("Choose another color");

        colorChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(
                        null,
                        "Change Stroke Color",
                        model.getCurColor()
                );
                model.setCurColor(selectedColor);
            }
        });

        this.add(colorChooserButton);

        // Width Slider
        widthLabel = new JLabel("Select stroke width:");

        widthSlider = new JSlider(JSlider.HORIZONTAL, WIDTH_MIN, WIDTH_MAX, WIDTH_INIT);
        widthSlider.setMajorTickSpacing(9);
        widthSlider.setMinorTickSpacing(1);
        widthSlider.setPaintTicks(true);
        widthSlider.setPaintLabels(true);
        widthSlider.setSnapToTicks(true);

        widthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                model.setCurStrokeWidth((int) source.getValue());
            }
        });

        this.add(widthLabel);
        this.add(widthSlider);

        // Show current color and width
        showLabel = new JLabel("Current color:");

        showPanel = new JPanel();
        showPanel.setLayout(new GridLayout(0, 1));

        showColor = new JLabel();
        showColor.setBackground(model.getCurColor());
        showColor.setOpaque(true);

        showPanel.add(showColor);

        this.add(showLabel);
        this.add(showPanel);
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model changes.
        showColor.setBackground(model.getCurColor());
        System.out.println("PaletteView changed!");
        System.out.println("Color: " + model.getCurColor() + " " + "Width: " + model.getCurStrokeWidth());
    }
}
