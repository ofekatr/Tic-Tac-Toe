/*
 * An implementation for the AI player methods.
 */
public class MyAIPlayer extends AIPlayer {
    // Ctor.
    public MyAIPlayer(GameBoard gb) {
        super(gb);
    }

    // Algorithm for handling a center move from the player.
    @Override
    public Move centerMove() {
        Symbols[][] arr = super.getGb().getBoard();
        // Check if there exists a triad that enables to player to win, and block it.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == Symbols.CIRCLE) {
                    if (arr[2 - i][2 - j] == Symbols.BLANK) {
                        return new Move(2 - i, 2 - j, Symbols.CROSS);
                    }
                }
            }
        }
        // If there is no such triad, make a default move.
        return super.defaultMove();
    }

    @Override
    // Algorithm for handling a corner move from the player.
    public Move cornerMove(Move pMove) {
        Symbols[][] arr = super.getGb().getBoard();
        int r = pMove.getRow(), c = pMove.getColumn();
        // Check if there exists a row triad that enables the player to win, and block it.
        for (int i = 1; i < 3; i++) {
            if (arr[(r + i) % 3][c] == Symbols.CIRCLE) {
                if (arr[(r + (i % 2) + 1) % 3][c] == Symbols.BLANK)
                    return new Move((r + (i % 2) + 1) % 3, c, Symbols.CROSS);
            }
        }
        // Check if there exists a column triad that enables the player to win, and block it.
        for (int i = 1; i < 3; i++) {
            if (arr[r][(c + i) % 3] == Symbols.CIRCLE) {
                if (arr[r][(c + (i % 2) + 1) % 3] == Symbols.BLANK)
                    return new Move(r, (c + (i % 2) + 1) % 3, Symbols.CROSS);
            }
        }
        // Check if there exists a diagonal triad that enables the player to win, and block it.
        boolean right = (c == 2), bottom = (r == 2);
        int deltaR = (bottom) ? -1 : 1, deltaC = (right) ? -1 : 1;
        for (int i = 1; i < 3; i++) {
            if (arr[r + i * deltaR][c + i * deltaC] == Symbols.CIRCLE) {
                if (arr[r + ((i % 2) + 1) * deltaR][c + ((i % 2) + 1) * deltaC] == Symbols.BLANK) {
                    return new Move(r + ((i % 2) + 1) * deltaR, c + ((i % 2) + 1) * deltaC, Symbols.CROSS);
                }
            }
        }
        // Fill an empty corner on the board if there exists such one.
        Move corner = freeCorner();
        if (corner != null)
            return corner;
        // If none of the above happens, make a default move.
        return super.defaultMove();
    }

    // Returns a free corner on the board if such one exists.
    public Move freeCorner() {
        Symbols[][] arr = super.getGb().getBoard();
        if (arr[0][0] == Symbols.BLANK)
            return new Move(0, 0, Symbols.CROSS);
        if (arr[0][2] == Symbols.BLANK)
            return new Move(0, 2, Symbols.CROSS);
        if (arr[2][0] == Symbols.BLANK)
            return new Move(2, 0, Symbols.CROSS);
        if (arr[2][2] == Symbols.BLANK)
            return new Move(2, 2, Symbols.CROSS);
        return null;
    }

    @Override
    // Algorithm for handling a side move from the player.
    public Move sideMove(Move pMove) {
        Symbols[][] arr = super.getGb().getBoard();
        int r = pMove.getRow(), c = pMove.getColumn();
        // Check if there exists a row or column triad that enables the player to win, and block it.
        for (int i = 1; i < 3; i++) {
            if (arr[(r + i) % 3][c] == Symbols.CIRCLE) {
                if (arr[(r + (i % 2) + 1) % 3][c] == Symbols.BLANK)
                    return new Move((r + (i % 2) + 1) % 3, c, Symbols.CROSS);
            }
            if (arr[r][(c + i) % 3] == Symbols.CIRCLE) {
                if (arr[r][(c + (i % 2) + 1) % 3] == Symbols.BLANK)
                    return new Move(r, (c + (i % 2) + 1) % 3, Symbols.CROSS);
            }
        }
        // If no such triad exists, make a default move.
        return super.defaultMove();
    }
}
