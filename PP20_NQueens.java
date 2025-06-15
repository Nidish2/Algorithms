package daa;

import java.util.ArrayList;
import java.util.List;

// This is a classic example of backtracking to solve the N-Queens problem.
public class PP20_NQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> allBoards = new ArrayList<>();
        char[][] board = new char[n][n];
        // Initialize the board with '_' indicating empty spaces
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }
        helper(board, allBoards, 0);
        return allBoards;
    }

    // Recursive method to place queens using backtracking
    private void helper(char[][] board, List<List<String>> allBoards, int col) {
        if (col == board.length) {
            saveBoard(board, allBoards);
            return;
        }
        for (int row = 0; row < board.length; row++) {
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q';
                helper(board, allBoards, col + 1);
                board[row][col] = '-'; // Backtrack
            }
        }
    }

    // Check if placing a queen at (row, col) is safe
    private boolean isSafe(int row, int col, char[][] board) {
        int n = board.length;
        // Check row and column
        for (int j = 0; j < n; j++) {
            if (board[row][j] == 'Q')
                return false;
            if (board[j][col] == 'Q')
                return false;
        }
        // Check upper-left diagonal
        for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'Q')
                return false;
        }
        // Check upper-right diagonal
        for (int r = row, c = col; r >= 0 && c < n; r--, c++) {
            if (board[r][c] == 'Q')
                return false;
        }

        // Check lower-right diagonal
        for (int c = col, r = row; c >= 0 && r < n; c--, r++) {
            if (board[r][c] == 'Q')
                return false;
        }
        // Check lower-left diagonal
        for (int c = col, r = row; c < board.length && r < n; c++, r++) {
            if (board[r][c] == 'Q')
                return false;
        }
        return true;
    }

    // Save the board configuration as a list of strings
    private void saveBoard(char[][] board, List<List<String>> allBoards) {
        List<String> newBoard = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            newBoard.add(new String(board[i]));
        }
        allBoards.add(newBoard);
    }

    public static void main(String[] args) {
        PP20_NQueens nQ = new PP20_NQueens();
        List<List<String>> soln = nQ.solveNQueens(8);
        System.out.println("Number of soln: " + soln.size() + "\n");
        for (List<String> sol : soln) {
            for (String row : sol) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
