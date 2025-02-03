// Brenen Olson, ols00175

import java.util.Scanner;

public class Piece {
    // Create Instance Variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;

    public char getPieceUni() {
        return character;
    }

    /**
     * Constructor.
     *
     * @param character The character representing the piece.
     * @param row       The row on the board the piece occupies.
     * @param col       The column on the board the piece occupies.
     * @param isBlack   The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     *
     * @param board  The current state of the board.
     * @param endRow The destination row of the move.
     * @param endCol The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     *
     * @param row The row to move the piece to.
     * @param col The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Return the color of the piece.
     *
     * @return The color of the piece.
     */
    public boolean getIsBlack() {
        return this.isBlack;
    }

    /**
     * Handle promotion of a pawn.
     *
     * @param row     Current row of the pawn
     * @param isBlack Color of the pawn
     */
    public void promotePawn(int row, boolean isBlack) {
        if (((row == 0) && !isBlack) || ((row == 7) && isBlack)) {
            System.out.println("Pawn Promoted! Indicate what piece to change to\n"
                    + "q: Queen\n" + "b: Bishop\n" + "k: Knight\n" + "r: Rook\n");
            Scanner input = new Scanner(System.in);

            // Change pawn to piece selected by user
            while (true) {
                String choice = input.nextLine().toLowerCase(); // Convert the input to lowercase for comparison

                switch (choice) {
                    case "q": // Queen
                        this.character = isBlack ? '\u265B' : '\u2655'; // Queen
                        return;
                    case "b": // Bishop
                        this.character = isBlack ? '\u265D' : '\u2657'; // Bishop
                        return;
                    case "k": // Knight
                        this.character = isBlack ? '\u265E' : '\u2658'; // Knight
                        return;
                    case "r": // Rook
                        this.character = isBlack ? '\u265C' : '\u2656'; // Rook
                        return;
                    default: // Invalid input
                        System.out.println("Invalid input. Please enter 'q', 'b', 'k', or 'r'.");
                }
            }
        }
    }

    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return String.valueOf(this.character);
    }
}

// Written by Brenen Olson, ols00175