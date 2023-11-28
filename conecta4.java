import java.util.Arrays;
import java.util.Scanner;

public class conecta4 {
    private static final int HEIGHT = 6;
    private static final int WIDTH = 7;
    private static final char EMPTY = '-';
    private static final char[] PLAYERS = { 'X', 'O' };

    public static void main(String[] args) {
        char[][] board = new char[HEIGHT][WIDTH];
        for (char[] row : board)
            Arrays.fill(row, EMPTY);

        Scanner in = new Scanner(System.in);
        boolean gameOn = true;
        int currentPlayer = 0;

        while (gameOn) {
            printBoard(board);
            int col;
            do {
                System.out.printf("Player %s, choose a column: ", PLAYERS[currentPlayer]);
                col = in.nextInt();
            } while (!isLegalMove(board, col));

            makeMove(board, col, PLAYERS[currentPlayer]);

            if (hasWon(board, PLAYERS[currentPlayer])) {
                gameOn = false;
                printBoard(board);
                System.out.printf("Player %s wins!\n", PLAYERS[currentPlayer]);
            } else {
                currentPlayer = 1 - currentPlayer; // Switch player
            }
        }
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board)
            System.out.println(Arrays.toString(row));
    }

    private static boolean isLegalMove(char[][] board, int col) {
        return col >= 1 && col <= WIDTH && board[0][col-1] == EMPTY;
    }

    private static void makeMove(char[][] board, int col, char player) {
        for (int row = HEIGHT - 1; row >= 0; row--) {
            if (board[row][col-1] == EMPTY) {
                board[row][col-1] = player;
                break;
            }
        }
    }

    private static boolean hasWon(char[][] board, char player) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (j + 3 < WIDTH &&
                        player == board[i][j] &&
                        player == board[i][j + 1] &&
                        player == board[i][j + 2] &&
                        player == board[i][j + 3]) {
                    return true;
                }
                if (i + 3 < HEIGHT) {
                    if (player == board[i][j] &&
                            player == board[i + 1][j] &&
                            player == board[i + 2][j] &&
                            player == board[i + 3][j]) {
                        return true;
                    }
                    if (j + 3 < WIDTH &&
                            player == board[i][j] &&
                            player == board[i + 1][j + 1] &&
                            player == board[i + 2][j + 2] &&
                            player == board[i + 3][j + 3]) {
                        return true;
                    }
                    if (j - 3 >= 0 &&
                            player == board[i][j] &&
                            player == board[i + 1][j - 1] &&
                            player == board[i + 2][j - 2] &&
                            player == board[i + 3][j - 3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}