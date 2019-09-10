/*
 * This class entails the methods related to the game board.
 */

import biuoop.DrawSurface;

import java.awt.*;

public class GameBoard {
    private Symbols board[][];
    private Highlight hl;
    private int blanks;

    // Ctor.
    public GameBoard() {
        this.blanks = 9;
        this.hl = new Highlight(0, 1);
        this.board = new Symbols[3][3];
        // Initialize board to be all blank.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                this.board[i][j] = Symbols.BLANK;
        }
    }
    // Validate if the board is full.
    public boolean fullBoard() {
        return blanks == 0;
    }

    // Add a move's symbol to the board
    public void addSymbol(Move m) {
        int y = m.getRow(), x = m.getColumn();
        this.board[y][x] = m.getSymbol();
        blanks--;
    }
    // GET method.
    public Highlight getHl() {
        return hl;
    }

    // GET method.
    public Symbols[][] getBoard() {
        return this.board;
    }

    // Draw the game board.
    public void drawShape(DrawSurface d) {
        int height = d.getHeight(), width = d.getWidth();
        d.setColor(Color.black);
        // Draw the board's table
        for (int i = 100; i < 700; i += (height / 3)) {
            for (int j = 0; j < height; j += (height / 3)) {
                d.drawRectangle(i, j, height / 3, height / 3);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Draw the highlight sprite
                if (hl.getX() == j && hl.getY() == i) {
                    this.hl.drawShape(j, i, d);
                }
                int midX = 100 + j * (height / 3) + (height / 3 / 2);
                int midY = i * (height / 3) + (height / 3 / 2);
                // Draw the Symbols in their appropriate positions.
                if (this.board[i][j] == Symbols.CROSS) {
                    Cross.drawShape(midX, midY, (height / 4 / 2) - 5, d);
                    continue;
                }
                if (this.board[i][j] == Symbols.CIRCLE) {
                    Circle.drawShape(midX, midY, (height / 3 / 2) - 10, d);
                    continue;
                }
            }
        }
    }
}
