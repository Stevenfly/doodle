import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);

        CanvasView canvasView = new CanvasView(model);
        PaletteView paletteView = new PaletteView(model);
        MenuView menuView = new MenuView(model);
        PlaybackView playbackView = new PlaybackView(model);

        view.add(canvasView, BorderLayout.CENTER);
        view.add(paletteView, BorderLayout.WEST);
        view.add(menuView, BorderLayout.NORTH);
//        view.add(playbackView, BorderLayout.SOUTH);

        model.notifyObservers();
    }
}
