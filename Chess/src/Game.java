// Brenen Olson, ols00175

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        // Initiate board with pieces provided by FEN code
        Board board = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        Scanner scanner = new Scanner(System.in);
        String currentPlayer = "white"; // Initialize starting turn color

        while (!board.isGameOver()) {
            // Print board and input instructions
            System.out.println(board);
            System.out.println("It is currently " + currentPlayer + "'s turn to play.");
            System.out.println("Enter your move (format: [start row] [start col] [end row] [end col]) ");

            // User input variables
            int startRow;
            int startCol;
            int endRow;
            int endCol;

            // Error handling
            try {
                startRow = scanner.nextInt();
                startCol = scanner.nextInt();
                endRow = scanner.nextInt();
                endCol = scanner.nextInt();

            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid integers.");
                scanner.nextLine(); // Consume the invalid input
                continue;
            }

            // Ensure input is range of board
            if ((startRow > 7) || (startCol > 7) || (endRow > 7) || (endCol > 7) || (startRow < 0) || (startCol < 0) || (endRow < 0) || (endCol < 0)) {
                System.out.println("Input out of bounds");
                continue;
            }

            // Ensure there is a piece present at start location
            if (board.getPiece(startRow, startCol) == null) {
                System.out.println("No piece at the specified location. Try again.");
                continue;
            }

            // Ensure that the correct color is being played
            if (board.getPiece(startRow,startCol).getIsBlack() != currentPlayer.equals("black")) {
                System.out.println("Only move " + currentPlayer + " pieces for this turn. Try again.");
                continue;
            }

            // Check if the move is valid and update currentPlayer
            boolean isValidMove = board.movePiece(startRow, startCol, endRow, endCol);

            if (isValidMove) {
                // Check for Pawn Promotion
                if ((board.getPiece(endRow, endCol).getPieceUni() == '\u2659') || (board.getPiece(endRow, endCol).getPieceUni() == '\u265f')) {
                    board.getPiece(endRow, endCol).promotePawn(endRow, (currentPlayer.equals("black")));
                }
                // Switch players
                currentPlayer = (currentPlayer.equals("white")) ? "black" : "white";
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();

        // Print out winner
        System.out.println(board);
        if (currentPlayer.equals("white")) { // Flip player turn before returning winner
            System.out.println("Black has won the game!");
        } else {
            System.out.println("White has won the game!");
        }
    }
}

// Written by Brenen Olson, ols00175