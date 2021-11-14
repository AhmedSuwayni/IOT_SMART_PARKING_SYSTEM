package com.example.final_project;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class ParkingArea implements Parcelable {


    int ID,Column,NumberOfParkingSlots,Rows;
    double lat1;
    double long1;
    double lat2;
    double long2;
    double lat3;
    double long3;

    public int getRows() {
        return Rows;
    }

    public void setRows(int rows) {
        Rows = rows;
    }

    double lat4;
    double long4;
    List <ParkingSpots> PsList = new ArrayList<>();
    public ParkingArea(int ID) {
        this.ID = ID;
    }

    public ParkingArea() {
    }

    public ParkingArea(int ID, double lat1, double long1, double lat2, double long2, double lat3, double long3, double lat4, double long4) {
        this.ID = ID;
        this.lat1 = lat1;
        this.long1 = long1;
        this.lat2 = lat2;
        this.long2 = long2;
        this.lat3 = lat3;
        this.long3 = long3;
        this.lat4 = lat4;
        this.long4 = long4;
    }


    protected ParkingArea(Parcel in) {
        ID = in.readInt();
        Column = in.readInt();
        NumberOfParkingSlots = in.readInt();
        lat1 = in.readDouble();
        long1 = in.readDouble();
        lat2 = in.readDouble();
        long2 = in.readDouble();
        lat3 = in.readDouble();
        long3 = in.readDouble();
        lat4 = in.readDouble();
        long4 = in.readDouble();
    }

    public static final Creator<ParkingArea> CREATOR = new Creator<ParkingArea>() {
        @Override
        public ParkingArea createFromParcel(Parcel in) {
            return new ParkingArea(in);
        }

        @Override
        public ParkingArea[] newArray(int size) {
            return new ParkingArea[size];
        }
    };

    public int getID() {
        return ID;
    }



    public int getColumn() {
        return Column;
    }

    public void setColumn(int column) {

        Column = column;
    }

    public int getNumberOfParkingSlots() {
        return NumberOfParkingSlots;
    }

    public void setNumberOfParkingSlots(int numberOfParkingSlots) {
        NumberOfParkingSlots = numberOfParkingSlots;
    }

    public String getLat1() {
        String s = String.valueOf(lat1);
        return s;
    }

    public String getLong1() {
        String s = String.valueOf(long1);
        return s;
    }

    public String getLat2() {
        String s = String.valueOf(lat2);
        return s;
    }

    public String getLong2() {
        String s = String.valueOf(long2);
        return s;
    }

    public String getLat3() {
        String s = String.valueOf(lat3);
        return s;
    }

    public String getLong3() {
        String s = String.valueOf(long3);
        return s;
    }

    public String getLat4() {
        String s = String.valueOf(lat4);
        return s;
    }

    public String getLong4() {
        String s = String.valueOf(long4);
        return s;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void fillSpots() {
        int PsPerC = NumberOfParkingSlots / Column;
        //Warn i should pass the Parking Area ID by creating it in DB and get the ID given to me from sequnce DB
        int PaID = 1;
        for(int i = 1; i <= Column; i++){
            for(int j = 1; j <= PsPerC; j++){

//                ParkingSpots ps = new ParkingSpots(i,PaID);
//                PsList.add(ps);



            }



        }

    }


    public String Row(){
        double Rows = NumberOfParkingSlots / Column;
        int intRows = (int) Math.ceil(Rows);
        return String.valueOf(intRows);
    }

    public int NumOfTaps(int c){

        double tabs = Column / 2;
        int inttabs = (int) Math.ceil(tabs);

        return inttabs;
    }

    public void setLat1(double lat1) {
        this.lat1 = lat1;
    }

    public void setLong1(double long1) {
        this.long1 = long1;
    }

    public void setLat2(double lat2) {
        this.lat2 = lat2;
    }

    public void setLong2(double long2) {
        this.long2 = long2;
    }

    public void setLat3(double lat3) {
        this.lat3 = lat3;
    }

    public void setLong3(double long3) {
        this.long3 = long3;
    }

    public void setLat4(double lat4) {
        this.lat4 = lat4;
    }

    public void setLong4(double long4) {
        this.long4 = long4;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeInt(Column);
        parcel.writeInt(NumberOfParkingSlots);
        parcel.writeDouble(lat1);
        parcel.writeDouble(long1);
        parcel.writeDouble(lat2);
        parcel.writeDouble(long2);
        parcel.writeDouble(lat3);
        parcel.writeDouble(long3);
        parcel.writeDouble(lat4);
        parcel.writeDouble(long4);
    }
}
