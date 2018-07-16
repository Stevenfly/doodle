import java.awt.*;
import java.util.*;
import java.util.List;

public class Model {
    /** The observers that are watching this model for changes. */
    private List<Observer> observers;

    /**
     * Data
     */
    private Color curColor;
    private int curStrokeWidth;
    private Point curMousePoint;
    private Stroke curStroke;
    private List<Stroke> strokes;

    /**
     * Create a new model.
     */
    public Model() {
        this.observers = new ArrayList<>();
        this.strokes = new ArrayList<>();

        // hard-code shit
        this.curColor = Color.BLACK;
        this.curStrokeWidth = 1;
    }

    public void startDrawStroke(Point point) {
        this.curStroke = new Stroke(curColor, curStrokeWidth);
        this.curStroke.addPoint(point);
    }

    public void endDrawStroke() {
        this.strokes.add(this.curStroke);
        notifyObservers();
    }

    public void drawingStroke(Point point) {
        this.curMousePoint = point;
        this.curStroke.addPoint(point);
        notifyObservers();
    }

    public void paintStrokes(Graphics2D g2d) {
        for (Stroke s: strokes) {
            s.paintStroke(g2d);
        }
        if (curStroke != null) curStroke.paintStroke(g2d);
    }

    /**
     * Getters
     */
    public Color getCurColor() {
        return curColor;
    }

    public int getCurStrokeWidth() {
        return curStrokeWidth;
    }

    public Point getCurMousePoint() {
        return curMousePoint;
    }

    public List<Stroke> getStrokes() {
        return strokes;
    }

    public Stroke getCurStroke() {
        return curStroke;
    }

    /**
     * Setters
     */
    public void setCurColor(Color curColor) {
        this.curColor = curColor;
        notifyObservers();
    }

    public void setCurStrokeWidth(int curStrokeWidth) {
        this.curStrokeWidth = curStrokeWidth;
        notifyObservers();
    }

    public void setStrokes(List<Stroke> strokes) {
        this.strokes = strokes;
    }

    /**
     * Add an observer to be notified when this model changes.
     */
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Remove an observer from this model.
     */
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * Notify all observers that the model has changed.
     */
    public void notifyObservers() {
        for (Observer observer: this.observers) {
            observer.update(this);
        }
    }
}
