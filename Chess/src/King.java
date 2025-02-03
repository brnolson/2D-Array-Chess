// Brenen Olson, ols00175

public class King {

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public King(int row, int col, boolean isBlack) {
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
        int rowDistance = Math.abs(endRow - this.row);
        int colDistance = Math.abs(endCol - this.col);

        // Check if the move is within one space in any direction (including diagonals).
        if (rowDistance <= 1 && colDistance <= 1) {
            return board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).getIsBlack() != this.isBlack;
        } else {
            return false; // Illegal move
        }
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}

// Written by Brenen Olson, ols00175