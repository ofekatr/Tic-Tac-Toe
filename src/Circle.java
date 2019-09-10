/*
 * This Class entails the methods related to the circle sprite.
 */

import biuoop.DrawSurface;

import java.awt.*;

public class Circle {
    public static void drawShape(int x, int y, int radius, DrawSurface d) {
        d.setColor(Color.blue);
        d.drawCircle(x, y, radius);
    }
}
