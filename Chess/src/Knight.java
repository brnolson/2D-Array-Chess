// Brenen Olson, ols00175

public class Knight {

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - this.row);
        int colDiff = Math.abs(endCol - this.col);

        // Check for the knight's L-like movement
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            if (board.getPiece(endRow, endCol) == null) {
                // Case 1: L-shaped movement
                return true;
            } else if (board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
                // Case 2: Capturing a piece
                return true;
            }
        }
        // Illegal move
        return false;
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}

// Written by Brenen Olson, ols00175