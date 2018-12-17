package tanneryost.flightreservation;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FlightItem {
    private UUID IDnum;
    private String number;
    private String departure;
    private String arrival;
    private String time;
    private int capacity;
    private double price;

    private SimpleDateFormat ft = new SimpleDateFormat("M-dd-yyyy @ HH:mm:ss");
    private Date dateAdded;

    private int sqlLogId;


    public FlightItem() {
        this.IDnum = UUID.randomUUID();
        this.dateAdded = new Date();
    }

    public FlightItem(UUID IDnum) {
        this.IDnum = IDnum;
        this.dateAdded = new Date();
    }

    public FlightItem(String number, String departure, String arrival, String time, int capacity, double price) {
        this.IDnum = UUID.randomUUID();
        this.number = number;
        this.departure = departure;
        this.arrival = arrival;
        this.time = time;
        this.capacity = capacity;
        this.price = price;
        this.dateAdded = new Date();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        sb.append("Number: ").append(this.number).append("\n");
        sb.append("Departure: ").append(this.departure).append("\n");
        sb.append("Arrival: ").append(this.arrival).append("\n");
        sb.append("Time: ").append(this.time).append("\n");
        sb.append("Capacity: ").append(this.capacity).append("\n");
        sb.append("Price: ").append(this.price).append("\n");

        return sb.toString();
    }

//    public String getDateString() {
//        return ft.format(dateAdded);
//    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDeparture() {
        return departure;
    }

    public UUID getIDnum() {
        return IDnum;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public SimpleDateFormat getFt() {
//        return ft;
//    }
//
//    public void setFt(SimpleDateFormat ft) {
//        this.ft = ft;
//    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getSqlLogId() {
        return sqlLogId;
    }

    public void setSqlLogId(int sqlLogId) {
        this.sqlLogId = sqlLogId;
    }
}
