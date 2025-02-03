// Brenen Olson, ols00175

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        this.board = new Piece[8][8];
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) { return board[row][col]; }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not 
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece. 
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (verifySourceAndDestination(startRow, startCol, endRow, endCol, board[startRow][startCol].getIsBlack())) {
            if (board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
                Piece movingPiece = board[startRow][startCol];
                board[endRow][endCol] = movingPiece; // Move the piece to the destination
                movingPiece.setPosition(endRow, endCol); // Update the piece's position
                board[startRow][startCol] = null; // Clear the source cell
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int kingsOnBoard = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                // Check if piece is a king
                if (piece != null && (piece.getPieceUni() == '\u265a' || piece.getPieceUni() == '\u2654')) {
                    kingsOnBoard++;
                }
            }
        }
        // Return true if both kings are found on the board
        return kingsOnBoard != 2;
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null; // Set every cell to null
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if ((startRow > 7) || (startCol > 7) || (endRow > 7) || (endCol > 7) || (startRow < 0) || (startCol < 0) || (endRow < 0) || (endCol < 0)) {
            // Ensure move is not out of bounds
            return false;
        }

        // Create representation of start and end position
        Piece start = getPiece(startRow, startCol);
        Piece end = getPiece(endRow, endCol);

        if (start == null) {
            // Ensure there is a piece at start location
            return false;
        }

        if (start.getIsBlack() != isBlack) {
            // Ensure the correct color is being moved
            return false;
        }

        if (end != null) {
            // If piece is present at the end, ensure the end piece is of the opposite color
            return (end.getIsBlack() != isBlack);
        }

        // If nothing fails, return true
        return true;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        // Find range between rows and cols
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        // Two squares are adjacent if they have a maximum difference of 1 in both rows and columns.
        return rowDiff <= 1 && colDiff <= 1;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        // Ensure the move occurs on a single row
        if (endRow != startRow) {
            return false;
        }

        int increment; // Determine if row is moving left or right
        if (startCol < endCol) {
            increment = 1;
        } else {
            increment = -1;
        }

        // Return true if piece does not move
        if (startCol == endCol) {
            return true;
        }

        // Check for pieces in the path
        for (int i = startCol + increment; i != endCol; i += increment) {
            if (i < 0 || i >= 8) {
                return false; // Out of bounds
            } else if (getPiece(startRow, i) != null) {
                return false; // There's a piece in the path
            }
        }

        return true;  // The horizontal move is valid
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        // Ensure the move occurs on a single row
        if (endCol != startCol) {
            return false;
        }

        int increment; // Determine if col is moving up or down
        if (startRow < endRow) {
            increment = 1;
        } else {
            increment = -1;
        }

        // Return true if piece does not move
        if (startRow == endRow) {
            return true;
        }

        // Check for pieces in the path
        for (int i = startRow + increment; i != endRow; i += increment) {
            if (i < 0 || i >= 8){
                return false; // Out of bounds
            }
            if (getPiece(i, startCol) != null) {
                return false;  // If there's a piece in the path
            }
        }

        return true;  // The vertical move is valid
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowIncrement; // Determine whether the diagonal is moving up or down
        if (startRow < endRow) {
            rowIncrement = 1;
        } else {
            rowIncrement = -1;
        }

        int colIncrement; // Determine whether the diagonal is moving left or right
        if (startCol < endCol ) {
            colIncrement = 1;
        } else {
            colIncrement = -1;
        }

        // Move to next space in the diagonal to begin checking emptiness
        int currentRow = startRow + rowIncrement;
        int currentCol = startCol + colIncrement;

        // Return true if piece does not move
        if ((startRow == endRow) && (startCol == endCol)) {
            return true;
        }

        // Check for pieces in the path
        while (currentRow != endRow) {
            if (currentRow < 0 || currentRow >= 8){
                return false; // Out of bounds
            }
            if (currentCol < 0 || currentCol >= 8){
                return false; // out of bounds
            }
            if (getPiece(currentRow, currentCol) != null) {
                return false; // if there's a piece in the path
            }
            currentRow += rowIncrement;
            currentCol += colIncrement;
        }
        return endCol == currentCol; // Check that the end position of the piece is diagonal from the start
    }
}

// Written by Brenen Olson, ols00175