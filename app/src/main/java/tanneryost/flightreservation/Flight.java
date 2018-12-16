package tanneryost.flightreservation;

public class Flight {
    private String flightNumber;
    private String departure;
    private String arrival;
    private String departureTime;
    private String flightCapacity;
    private double price;

    public Flight(String flightNumber, String departure, String arrival, String departureTime, String flightCapacity, double price) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.flightCapacity = flightCapacity;
        this.price = price;
    }
}
