package model;

public class PlaneTicket extends Ticket{
    enum SeatClass{
        economy,
        business,
        first
    }
    public String airport_name;
    boolean luggage_included;
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
