/*
 * This class entails the methods related to a move.
 */
public class Move {
    private int row;
    private int column;
    private Symbols symbol;

    // Ctor.
    public Move(int row, int column, Symbols symbol) {
        this.row = row;
        this.column = column;
        this.symbol = symbol;
    }

    // GET method.
    public int getRow() {
        return this.row;
    }

    // GET method.
    public int getColumn() {
        return this.column;
    }

    // GET method.
    public Symbols getSymbol() {
        return this.symbol;
    }
}
