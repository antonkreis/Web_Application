package model;

public class BusTicket extends Ticket{
    private enum BusType{
        CITY,
        REGIONAL,
        INTERNATIONAL
    }
    BusType busType;
    private String busStopName;
    public void buy(){
        System.out.println("Bus_Ticket::buy was called");
    }
    public void cancel(){
        System.out.println("Bus_Ticket::cancel was called");
    }
    public void show(){
        System.out.println("Bus_Ticket::show was called");
    }
}
