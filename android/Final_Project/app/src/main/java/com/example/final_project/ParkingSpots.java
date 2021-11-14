package com.example.final_project;

public class ParkingSpots implements Comparable{
    int column,ParkingAreaID,Row;
    boolean emtpy = true;
    int ID = 0;

    public ParkingSpots(int column, int parkingAreaID ,int Row) {
        this.column = column;
        this.ParkingAreaID = parkingAreaID;
        this.Row = Row;

    }

    public ParkingSpots() {

    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getParkingAreaID() {
        return ParkingAreaID;
    }

    public void setParkingAreaID(int parkingAreaID) {
        ParkingAreaID = parkingAreaID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isEmtpy() {
        return emtpy;
    }

    public void setEmtpy(boolean emtpy) {
        this.emtpy = emtpy;
    }

    public int getRow() {
        return Row;
    }

    public void setRow(int row) {
        Row = row;
    }



    @Override
    public int compareTo(Object ps) {
        int comparerow=((ParkingSpots)ps).getRow();

        return this.Row-comparerow;
    }
}
