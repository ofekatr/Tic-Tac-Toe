import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import javax.swing.text.JTextComponent;
import java.awt.*;

public class GameMain {
    public static final int width = 800;
    public static final int height = 600;

    public static void main(String[] args) {
        GUI gui = new GUI("Tic Tac Toe!", width, height);
        KeyboardSensor sensor = gui.getKeyboardSensor();
        while (!sensor.isPressed(KeyboardSensor.ENTER_KEY)) {
            drawMenu(gui);
        }

        while (startGame(gui)) ;
        gui.close();

    }

    public static boolean startGame(GUI gui) {
        Game game = new Game(gui);
        DrawSurface d = game.getGui().getDrawSurface();
        game.run();
        d.setColor(Color.ORANGE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.magenta);
        d.drawText(d.getWidth() / 4, d.getHeight() / 4,
                "Lost Again!", 50);
        d.drawText(d.getWidth() / 4, d.getHeight() / 4 + 100,
                "Press Enter to continue", 50);
        d.drawText(d.getWidth() / 4, d.getHeight() / 4 + 200,
                "Press X to exit", 50);
        game.getGui().show(d);
        while (!(game.getGui().getKeyboardSensor().isPressed("x") &&
                ! game.getGui().getKeyboardSensor().isPressed("X"))&&
                !game.getGui().getKeyboardSensor().isPressed(KeyboardSensor.ENTER_KEY)) ;
        if (game.getGui().getKeyboardSensor().isPressed("x") ||
                game.getGui().getKeyboardSensor().isPressed("X"))
            return false;
        return true;
    }

    public static void drawMenu(GUI gui) {
        DrawSurface d = gui.getDrawSurface();
        Sleeper sleeper = new Sleeper();
            d.setColor(Color.orange);
            d.fillRectangle(0, 0, width, height);
            d.setColor(Color.magenta);
            d.drawText(d.getWidth() / 3, d.getHeight() / 2,
                    "Tic-Tac-Toe!", 50);
            d.drawText(d.getWidth() / 4, d.getHeight() / 2 + 100,
                    "Press Enter to Start!", 50);
            gui.show(d);
            sleeper.sleepFor(50);
    }
}
