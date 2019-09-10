/*
 *  This class entails the methods related to the highlight sprite
 */

import biuoop.DrawSurface;

import java.awt.*;

public class Highlight {
    private int x;
    private int y;

    // Ctor.
    public Highlight(int y, int x) {
        this.x = x;
        this.y = y;
    }

    // SET method.
    public void setX(int x1) {
        this.x = x1;
    }

    // SET method.
    public void setY(int y1) {
        this.y = y1;
    }

    // GET method.
    public int getX() {
        return this.x;
    }

    // GET method.
    public int getY() {
        return this.y;
    }

    // Draw the highlight sprite.
    public void drawShape(int x, int y, DrawSurface d) {
        int height = d.getHeight();
        d.setColor(Color.cyan);
        d.fillRectangle(110 + x * (height / 3), 10 + y * (height / 3), height / 3 - 20,
                (height / 3) - 20);
    }

    // Handle moving down.
    public void moveDown() {
        y = (this.y == 2) ? y : y + 1;
    }

    // Handle moving up.
    public void moveUp() {
        y = (this.y == 0) ? y : y - 1;
    }

    // Handle moving left.
    public void moveLeft() {
        x = (this.x == 0) ? x : x - 1;
    }

    // Handle moving right.
    public void moveRight() {
        x = (this.x == 2) ? x : x + 1;
    }

}
