/*
 * This class entails the method for handling the game's operations and running.
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.*;

public class Game {
    private static final int width = 800;
    private static final int height = 600;
    private GUI gui;
    private GameBoard gb;
    private AIPlayer ai;
    private boolean stop;   // Flag for finishing the game.

    // Ctor
    public Game(GUI gui) {
        this.stop = false;
        this.gui = gui;
        this.gb = new GameBoard();
        this.ai = new MyAIPlayer(this.gb);
    }

    // This method runs the game.
    public void run() {
        Sleeper sleeper = new Sleeper();
        Highlight hl = this.gb.getHl();
        KeyboardSensor kb = this.gui.getKeyboardSensor();
        this.draw();
        // For the board to be first viewed empty.
        sleeper.sleepFor(100);
        // Rival's first move
        Move rivalM = this.ai.makeMove(null);
        this.gb.addSymbol(rivalM);
        while (!stop) {
            // Slot choice animation
            if (kb.isPressed(KeyboardSensor.DOWN_KEY)) {
                hl.moveDown();
            }
            if (kb.isPressed(KeyboardSensor.UP_KEY)) {
                hl.moveUp();
            }
            if (kb.isPressed(KeyboardSensor.LEFT_KEY)) {
                hl.moveLeft();
            }
            if (kb.isPressed(KeyboardSensor.RIGHT_KEY)) {
                hl.moveRight();
            }
            // handle the move chosen by the player
            if (kb.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.handleMove();
            }
            // Ensure the player releases the key before continuing
            while (kb.isPressed(KeyboardSensor.DOWN_KEY) ||
                    kb.isPressed(KeyboardSensor.UP_KEY) ||
                    kb.isPressed(KeyboardSensor.LEFT_KEY) ||
                    kb.isPressed(KeyboardSensor.RIGHT_KEY) ||
                    kb.isPressed(KeyboardSensor.SPACE_KEY)) ;
            // Draw the sprites
            this.draw();
            sleeper.sleepFor(30);
        }
        // Wait before finishing the game
        sleeper.sleepFor(500);
    }

    // Handle the move chosen by the player
    public void handleMove() {
        // Check if the move is legal
        if (!validateMove())
            return;
        // Add move to the board
        int x = this.gb.getHl().getX(), y = this.gb.getHl().getY();
        Move m = new Move(y, x, Symbols.CIRCLE);
        this.gb.addSymbol(m);
        // if the board is full or the player has won, finish the game
        if (this.gb.fullBoard() || victory(Symbols.CIRCLE)) {
            this.stop = true;
            return;
        }
        // Add the rival's move
        Move rivalM = this.ai.makeMove(m);
        this.gb.addSymbol(rivalM);
        // if the board is full or the rival has won, finish the game
        if (this.victory(Symbols.CROSS) || this.gb.fullBoard()) {
            this.stop = true;
            return;
        }
    }

    // GET method
    public GUI getGui() {
        return this.gui;
    }

    // Validate a move to be legal
    public boolean validateMove() {
        int x = this.gb.getHl().getX(), y = this.gb.getHl().getY();
        return (this.gb.getBoard()[y][x] == Symbols.BLANK);
    }

    // Check if the game had been won by the player using the symbol s
    public boolean victory(Symbols s) {
        Symbols[][] arr = this.gb.getBoard();
        // Row or Column victory
        for (int i = 0; i < 3; i++) {
            if (arr[0][i] == s
                    && arr[1][i] == s
                    && arr[2][i] == s)
                return true;
            if (arr[i][0] == s
                    && arr[i][1] == s
                    && arr[i][2] == s)
                return true;
        }
        // First Diagonal victory
        if (arr[0][0] == s
                && arr[1][1] == s
                && arr[2][2] == s)
            return true;
        // Second Diagonal Victory
        if (arr[2][0] == s
                && arr[1][1] == s
                && arr[0][2] == s)
            return true;
        return false;
    }

    // Draw the game's sprites
    public void draw() {
        DrawSurface d = this.gui.getDrawSurface();
        d.setColor(Color.orange);
        d.fillRectangle(0, 0, width, height);
        this.gb.drawShape(d);
        if (this.stop) {
            d.setColor(Color.magenta);
            d.drawText(d.getWidth() / 3, d.getHeight() / 2,
                    "Game Over!", 50);
        }
        gui.show(d);
    }
}
