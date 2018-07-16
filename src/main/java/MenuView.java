import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;

public class MenuView extends JPanel implements Observer {

    private Model model;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem createMenuItem;
    private JMenuItem exitMenuItem;

    private static final int minWidth = 400, minHeight = 300;

    /**
     * Create a new View.
     */
    public MenuView(Model model) {
        // Set up the window.
        this.setMinimumSize(new Dimension(minWidth, minHeight));
        this.setLayout(new GridLayout(0, 1));

        // Hook up this observer so that it will be notified when the model changes.
        this.model = model;
        model.addObserver(this);

        setVisible(true);

        // Menu
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        saveMenuItem = new JMenuItem("Save doodle");
        loadMenuItem = new JMenuItem("Load doodle");
        createMenuItem = new JMenuItem("Create a new doodle");
        exitMenuItem = new JMenuItem("Exit");

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser c = new JFileChooser();
                int rVal = c.showSaveDialog(null);

                if (rVal == JFileChooser.APPROVE_OPTION) {
                    File file = c.getSelectedFile();
                    try(FileWriter fw = new FileWriter(file + ".doodle")) {
                        fw.write(model.getStrokes().toString());
                        fw.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (rVal == JFileChooser.CANCEL_OPTION) {

                }
            }
        });

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser c = new JFileChooser();
                int rVal = c.showOpenDialog(null);

                if (rVal == JFileChooser.APPROVE_OPTION) {
                    File file = c.getSelectedFile();
                } else if (rVal == JFileChooser.CANCEL_OPTION) {

                }
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(createMenuItem);
        fileMenu.add(exitMenuItem);

        this.add(menuBar);
    }



    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model changes.
        System.out.println("MenuView changed!");
    }
}
