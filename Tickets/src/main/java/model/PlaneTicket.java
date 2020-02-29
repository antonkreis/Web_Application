package model;

public class PlaneTicket extends Ticket{
    private enum SeatClass{
        ECONOMY,
        BUSINESS,
        FIRST
    }
    SeatClass seatClass;
    private String airportName;
    private boolean luggageIncluded;
    public void buy(){
        System.out.println("Plane_Ticket::buy was called");
    }
    public void cancel(){
        System.out.println("Plane_Ticket::cancel was called");
    }
    public void show(){
        System.out.println("Plane_Ticket::show was called");
    }
}
