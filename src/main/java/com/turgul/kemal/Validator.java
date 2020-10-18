package com.turgul.kemal;

import java.util.List;

/**
 * @author Kemal Turgul on 17.10.2020
 */
public class Validator {

    private static final String USAGE_INFO = "\nUSAGE: java -jar ChessQueenProblem.jar n r c k barriers\n" +
                                             "Input parameters: n r c k barriers\n" +
                                             "Input Parameters definitions:\n" +
                                             "\tn: One side length of the chess board for nxn (n>=2)\n" +
                                             "\tr: Horizontal (Row) position of the queen on the chess board (r<=n)\n" +
                                             "\tc: Vertical (Column) position of the queen on the chess board (c<=n)\n" +
                                             "\tk: Number of the barriers (k>=0) \n" +
                                             "\tbarriers: List of the barrier pair as row and column i.e 3 5 4 2\n" +
                                             "Sample inputs:\n" +
                                             "\t8 4 3  Here: n=8 and position of the queen is row=4 and column=3 (4,3) and no input for barriers\n" +
                                             "\t6 4 3 1 5 6  Here:n=6 r=4 c=3 k=1 and barrier point is row=5 and column=6 (5,6)\n" +
                                             "\t5 2 4 2 3 2 4 1 Here:n=5 r=2 c=4 k=2 and barrier points are (3,2)(4,1)\n";

    protected static void checkInputTypeInt(String[] inputs) {
        for (String input : inputs) {
            try {
                Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("This input:'" + input + "' is not an integer. All parameter should be an Integer data type!!");
                printUsageAndExit();
            }
        }
    }

    protected static void checkMinimumInputParams(String[] inputArray) {
        if (inputArray.length < 3) {
            System.err.println("At least 3 parameters needed. Input parameters n r c are required !!");
            printUsageAndExit();
        }
    }

    protected static int getIntValue(String val) {
        return Integer.parseInt(val);
    }

    protected static void validateInputParamters(int n, int row, int column) {
        if (n < 2) {
            System.err.println("Board size (n) must be greater than 1");
            printUsageAndExit();
        } else if (row > n || row <= 0 || column > n || column <= 0) {
            System.err.println("All rows and columns must be greater than 0 and less than or equal to board size (n:" + n + ") !!");
            printUsageAndExit();
        }
    }

    protected static void addToBarrierList(List<BarrierPoint> barrierPointList, int row, int column) {
        BarrierPoint point = new BarrierPoint(row, column);
        barrierPointList.add(point);
    }

    protected static void checkBarrierInputs(int k, String[] inputArray) {
        if (k > 0 && inputArray.length % 2 != 0) {
            System.err.println("Barrier point pairs do not match, please check them !!");
            printUsageAndExit();
        }
        if ((inputArray.length - 4) != 2 * k) {
            System.err.println("Barrier point pairs do not match number(k:" + k + "), please check them !!");
            Validator.printUsageAndExit();
        }
    }

    protected static void printUsageAndExit() {
        System.out.println(USAGE_INFO);
        System.exit(1);
    }

}
