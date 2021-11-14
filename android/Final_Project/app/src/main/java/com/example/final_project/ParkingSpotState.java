package com.example.final_project;

import java.sql.Timestamp;

public class ParkingSpotState implements Comparable<ParkingSpotState>{

    Boolean NewState;
    int SID;
    int PID;
    Timestamp date;
    int min;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public ParkingSpotState() {
    }



    public Boolean getNewState() {
        return NewState;
    }

    public void setNewState(Boolean newState) {
        NewState = newState;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    @Override
    public int compareTo(ParkingSpotState ps) {
        //this method to compare for sort
        return getDate().compareTo(ps.getDate());
    }
}
