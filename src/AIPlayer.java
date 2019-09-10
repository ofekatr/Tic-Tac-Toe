/*
 * This Class entails the Abstract AI Player algorithms.
 */

import java.util.Random;

public abstract class AIPlayer {
    private GameBoard gb;   // The game board

    // Ctor
    public AIPlayer(GameBoard gb) {
        this.gb = gb;
    }
    // Get method
    public GameBoard getGb() {
        return gb;
    }
    // Returns the rival's move in response to the player's move
    public Move makeMove(Move pMove) {
        // if it is the first move in the game
        if (pMove == null) {
            return defaultMove();
        }
        Move vicMove = victoryMove();
        // if the game can be won in this turn
        if (vicMove != null)
            return vicMove;
        // if the player's move was a center move
        if (isCenter(pMove)) {
            return centerMove();
        }
        // if the player's move was a corner move
        if (isCorner(pMove)) {
            return cornerMove(pMove);
        }
        // if the player's move was a side move
        return sideMove(pMove);
    }

    // Returns a default rival move - return the first blank cell
    public Move defaultMove() {
        Random rnd = new Random();
        Move m = null;
        Symbols[][] arr = this.gb.getBoard();
        int row, col;
        do {
            row = rnd.nextInt(3);
            col = rnd.nextInt(3);
        } while (this.gb.getBoard()[row][col] != Symbols.BLANK);
        m = new Move(row, col, Symbols.CROSS);
        return m;
    }

    public abstract Move centerMove();

    public abstract Move cornerMove(Move pMove);

    public abstract Move sideMove(Move pMove);

    // Validates if the move was set in a corner slot.
    public boolean isCorner(Move pMove) {
        int pRow = pMove.getRow(), pCol = pMove.getColumn();
        return ((pRow == 0 && pCol == 0) ||
                (pRow == 2 && pCol == 0) ||
                (pRow == 0 && pCol == 2) ||
                (pRow == 2 && pCol == 2));
    }

    // Validates if the move was set in a center slot.
    public boolean isCenter(Move pMove) {
        return (pMove.getRow() == 1 && pMove.getColumn() == 1);
    }

    // Returns a move that'd win the game if such one exists.
    public Move victoryMove() {
        // Row victory case
        for (int i = 0; i < 3; i++) {
            int crossCounter = 0, blankIndex = -1;
            for (int j = 0; j < 3; j++) {
                if (this.gb.getBoard()[i][j] == Symbols.CROSS)
                    crossCounter++;
                if (this.gb.getBoard()[i][j] == Symbols.BLANK)
                    blankIndex = j;
            }
            if (crossCounter == 2 && blankIndex != -1) {
                return new Move(i, blankIndex, Symbols.CROSS);
            }
        }

        // Column victory case
        for (int i = 0; i < 3; i++) {
            int crossCounter = 0, blankIndex = -1;
            for (int j = 0; j < 3; j++) {
                if (this.gb.getBoard()[j][i] == Symbols.CROSS)
                    crossCounter++;
                if (this.gb.getBoard()[j][i] == Symbols.BLANK)
                    blankIndex = j;
            }
            if (crossCounter == 2 && blankIndex != -1) {
                return new Move(blankIndex, i, Symbols.CROSS);
            }
        }

        // Diagonal victory case
        int diagCount1 = 0, diagBlank1 = -1, diagCount2 = 0, diagBlank2 = -1;
        for (int i = 0; i < 3; i++) {
            if (this.gb.getBoard()[i][i] == Symbols.CROSS)
                diagCount1++;
            if (this.gb.getBoard()[i][i] == Symbols.BLANK)
                diagBlank1 = i;
            if (this.gb.getBoard()[2 - i][2 - i] == Symbols.CROSS)
                diagCount1++;
            if (this.gb.getBoard()[i][2 - i] == Symbols.BLANK)
                diagBlank2 = i;
        }
        // first diagonal case
        if (diagCount1 == 2 && diagBlank1 != -1) {
            return new Move(diagBlank1, diagBlank1, Symbols.CROSS);
        }
        // second diagonal case
        if (diagCount2 == 2 && diagBlank2 != -1) {
            return new Move(diagBlank2, 2 - diagCount2, Symbols.CROSS);
        }

        // Default: no victory move case
        return null;
    }
}
