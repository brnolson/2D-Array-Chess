// Brenen Olson, ols00175

public class Bishop {

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public Bishop(int row, int col, boolean isBlack) {
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
        if (board.verifyDiagonal(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Case 1: Diagonal movement
            return true;

        } else if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
            // Case 2: Capturing a piece
            return board.verifyDiagonal(this.row, this.col, endRow, endCol);

        } else {
            // Illegal move
            return false;
        }
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}

// Written by Brenen Olson, ols00175