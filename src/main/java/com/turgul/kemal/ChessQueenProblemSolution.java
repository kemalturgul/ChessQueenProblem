package com.turgul.kemal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kemal Turgul on 17.10.2020
 * <p>
 * Sample Result:
 * (n=5) nxn board and Chess Queen at [4,3] position
 * Barriers positions:[4,2] [2,3] [5,5]
 * <p>
 * Total ChessQueen's Available Positions:10
 * <p>
 * 0 1 1 1 x
 * 0 x v 1 1
 * 0 1 1 1 0
 * 1 0 x 0 1
 * 0 0 0 0 0
 * <p>
 * Here;
 * 0:empty positions
 * 1:Positions that queen can go
 * v:Position of the queen on the chess board
 * x:Positions of the barriers on the chess board
 */
public class ChessQueenProblemSolution {

    private static final int POSITION_EMPTY = 0;
    private static final int POSITION_AVAILABLE = 1;
    private static final int POSITION_BARRIER = 2;

    public static void main(String[] args) {
        List<BarrierPoint> barrierPointList = new ArrayList<BarrierPoint>();

        Validator.checkMinimumInputParams(args);
        Validator.checkInputTypeInt(args);

        //These 3 input argument are required
        int boardSize = Validator.getIntValue(args[0]);
        int queenRow = Validator.getIntValue(args[1]);
        int queenColumn = Validator.getIntValue(args[2]);

        Validator.validateInputParamters(boardSize, queenRow, queenColumn);

        // Check if barrier exist or not from input
        if (args.length >= 4) {
            // Get number of the barriers
            int k = Validator.getIntValue(args[3]);
            Validator.checkBarrierInputs(k, args);

            // Validate and add barriers to barrier list
            for (int i = 1; i < args.length - 4; i += 2) {
                int barrierRow = Validator.getIntValue(args[i + 3]);
                int barrierColumn = Validator.getIntValue(args[i + 4]);

                // Validating barriers
                Validator.validateInputParamters(boardSize, barrierRow, barrierColumn);

                // Adding barriers to barrier list
                Validator.addToBarrierList(barrierPointList, barrierRow, barrierColumn);
            }
        }

        // This is for Queen
        Validator.addToBarrierList(barrierPointList, queenRow, queenColumn);

        calculateMovements(boardSize, queenRow - 1, queenColumn - 1, barrierPointList);

    }

    private static void calculateMovements(int n, int row, int column, List<BarrierPoint> barrierPointList) {
        int[][] board = prepareEmptyBoard(n);
        putBarriersOnBoard(board, barrierPointList);
        increaseBothRowAndColumn(n, board, row, column);
        decreaseBothRowAndColumn(board, row, column);
        increaseRowDecreaseColumn(n, board, row, column);
        decreaseRowIncreaseColumn(n, board, row, column);
        increaseAndDecreaseOnlyRow(n, board, row, column);
        increaseAndDecreaseOnlyColumn(n, board, row, column);

        printBoard(n, board, row, column);

        int result = countNumberOfAvailability(n, board);

        System.out.println("Total number of the chess queen's movements:" + result);
    }

    /**
     * Creating a nxn chess board with initial value of 0:means empty position
     *
     * @param n One side length of the chess board for nxn
     * @return two dimensional array as chess board nxn
     */
    private static int[][] prepareEmptyBoard(int n) {
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = POSITION_EMPTY;
            }
        }
        return board;
    }

    /**
     * Putting all barriers on the ches board
     *
     * @param board            chess board two dimensional array
     * @param barrierPointList list of the all barriers
     */
    private static void putBarriersOnBoard(int[][] board, List<BarrierPoint> barrierPointList) {
        for (BarrierPoint point : barrierPointList) {
            board[point.getRow() - 1][point.getColumn() - 1] = POSITION_BARRIER;
        }
    }

    /**
     * @param n      length of the one side of the chess board
     * @param board
     * @param row
     * @param column
     */
    private static void increaseBothRowAndColumn(int n, int[][] board, int row, int column) {
        for (int i = row; i < n; i++) {
            row = row + 1;
            column = column + 1;
            if (row >= n || column >= n || board[row][column] == POSITION_BARRIER) {
                break;
            }
            board[row][column] = POSITION_AVAILABLE;
        }
    }

    /**
     * @param board
     * @param row
     * @param column
     */
    private static void decreaseBothRowAndColumn(int[][] board, int row, int column) {
        for (int i = row; 0 < i; i--) {
            row = row - 1;
            column = column - 1;
            if (row < 0 || column < 0 || board[row][column] == POSITION_BARRIER) {
                break;
            }
            board[row][column] = POSITION_AVAILABLE;
        }
    }

    /**
     * @param n      length of the one side of the chess board
     * @param board
     * @param row
     * @param column
     */
    private static void increaseRowDecreaseColumn(int n, int[][] board, int row, int column) {
        for (int i = row; i < n; i++) {
            row = row + 1;
            column = column - 1;
            if (row >= n || column < 0 || board[row][column] == POSITION_BARRIER) {
                break;
            }
            board[row][column] = POSITION_AVAILABLE;
        }
    }

    /**
     * @param n      length of the one side of the chess board
     * @param board
     * @param row
     * @param column
     */
    private static void decreaseRowIncreaseColumn(int n, int[][] board, int row, int column) {
        for (int i = row; 0 < i; i--) {
            row = row - 1;
            column = column + 1;
            if (row < 0 || column >= n || board[row][column] == POSITION_BARRIER) {
                break;
            }
            board[row][column] = POSITION_AVAILABLE;
        }
    }

    /**
     * @param n      length of the one side of the chess board
     * @param board
     * @param row
     * @param column
     */
    private static void increaseAndDecreaseOnlyRow(int n, int[][] board, int row, int column) {
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][column] == POSITION_BARRIER) {
                break;
            }
            board[i][column] = POSITION_AVAILABLE;
        }
        for (int j = row + 1; j < n; j++) {
            if (board[j][column] == POSITION_BARRIER) {
                break;
            }
            board[j][column] = POSITION_AVAILABLE;
        }
    }

    /**
     * @param n      length of the one side of the chess board
     * @param board
     * @param row
     * @param column
     */
    private static void increaseAndDecreaseOnlyColumn(int n, int[][] board, int row, int column) {
        for (int i = column - 1; i >= 0; i--) {
            if (board[row][i] == POSITION_BARRIER) {
                break;
            }
            board[row][i] = POSITION_AVAILABLE;
        }
        for (int j = column + 1; j < n; j++) {
            if (board[row][j] == POSITION_BARRIER) {
                break;
            }
            board[row][j] = POSITION_AVAILABLE;
        }
    }

    /**
     * Counting all chess queen's available position by counting value of the 1 on board
     *
     * @param n     length of the one side of the chess board
     * @param board chess board
     * @return
     */
    private static int countNumberOfAvailability(int n, int[][] board) {
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != POSITION_BARRIER) {
                    counter += board[i][j];
                }
            }
        }
        return counter;
    }

    /**
     * Printing result of the chess board on screen
     *
     * @param n      length of the one side of the chess board
     * @param board  chess board
     * @param row    Horizontal (Row) position of the queen on the chess board
     * @param column Vertical (Column) position of the queen on the chess board
     */
    private static void printBoard(int n, int[][] board, int row, int column) {
        System.out.println("CHESS BOARD VIEW:");
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                //System.out.println("board[" + i + "][" + j + "]=" + board[i][j]);
                if (i == row && j == column) {
                    System.out.print(" v");
                } else if (board[i][j] == POSITION_BARRIER) {
                    System.out.print(" x");
                } else {
                    System.out.print(" " + board[i][j]);
                }
            }
            System.out.println("");
        }
    }

}
