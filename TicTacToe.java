package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    private static Scanner scanner = new Scanner(System.in);
    private static char[][] gameBoard = new char[3][3];
    private static int row;
    private static int column;

    protected static void start () {
        rowLoop: for (int i = 0; i < gameBoard.length; i++) {
            colLoop: for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = '_';
            }
        }
    }

    protected static void printGameBoard () {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    protected static void play () {
        char turn = 'X';
        int moveCounter = 0;
        boolean gameOver = false;
        boolean draw = false;

        while (true) {
            System.out.print("Enter the coordinates: ");
            String inputRow = scanner.next();
            String inputColumn = scanner.next();
            if (isValid(inputRow, inputColumn)) {
                gameBoard[column][row] = turn;
                ++moveCounter;
            } else {
                continue;
            }
            if (isGameOver(row, column)) {
                gameOver = true;
                printGameBoard();
                break;
            }
            else if (moveCounter == 9) {
                draw = true;
                printGameBoard();
                break;
            }
            printGameBoard();
            if (turn == 'X') {
                turn = 'O';
            } else {
                turn = 'X';
            }
        }
        if (gameOver) {
            System.out.print(turn + " wins");
        } else if (draw) {
            System.out.println("Draw");
        }

    }

    protected static boolean isValid (String inputRow, String inputColumn) {

        try {
            row = Integer.parseInt(inputRow) - 1;
            column = Integer.parseInt(inputColumn) - 1;
            switch (column){
                case 0:
                    column = 2;
                    break;
                case 2:
                    column = 0;
                    break;
            }
        }
        catch (NumberFormatException e) {
            System.out.println ("You should enter numbers!");
            return false;
        }
        if ((row > 2 || row < 0) || (column > 2 || column < 0)) {
            System.out.println ("Coordinates should be from 1 to 3!");
            return false;

        }

        if (gameBoard[column][row] != ('_')) {
            System.out.println ("This cell is occupied! Choose another one!");
            return false;
        }
        return true;

    }

    protected static boolean isGameOver (int row, int column) {
        //check row winning
        if ((gameBoard[column][0] == gameBoard[column][1])
                && (gameBoard[column][1] == gameBoard[column][2])) {
            return true;
        }
        // check column winning
        else if (gameBoard[0][row] == gameBoard[1][row]
                && gameBoard[1][row] == gameBoard[2][row]) {
            return true;
        }
        // check diagonal winning
        else if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]
                && gameBoard [1][1] != '_') {
            return true;
        }
        else if (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]
                && gameBoard[1][1] != '_' ) {
            return true;
        }

        return false;
    }
}
