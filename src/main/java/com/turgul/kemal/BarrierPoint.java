package com.turgul.kemal;

/**
 * @author Kemal Turgul on 17.10.2020
 */
public class BarrierPoint {
    private int row;
    private int column;

    public BarrierPoint(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
