/*
 * This class entails the methods related to the cross sprite.
 */

import biuoop.DrawSurface;

import java.awt.*;

public class Cross {
    public static void drawShape(int x, int y, int radius, DrawSurface d) {
        d.setColor(Color.red);
        d.drawLine(x - radius, y - radius, x + radius, y + radius);
        d.drawLine(x - radius, y + radius, x + radius, y - radius);
    }
}
