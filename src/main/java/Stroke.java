import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Stroke {
    private List<Point> stroke;
    private Color color;
    private int width;

    public Stroke(Color color, int width) {
        this.stroke = new ArrayList<>();
        this.color = color;
        this.width = width;
    }

    public void addPoint(Point point) {
        this.stroke.add(point);
    }

    public void removePoint() {
        this.stroke.remove(stroke.size());
    }

    public void paintStroke(Graphics2D g2d) {
        g2d.setColor(this.color);
        g2d.setStroke(new BasicStroke(this.width));

        for (int i = 0; i < this.stroke.size() - 1; i++) {
            g2d.drawLine(
                    this.stroke.get(i).x,
                    this.stroke.get(i).y,
                    this.stroke.get(i+1).x,
                    this.stroke.get(i+1).y
            );
        }
    }

    public String toString() {
        return stroke.toString();
    }

    /**
    * Getters
    */
    public List<Point> getStroke() {
        return stroke;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }
}
