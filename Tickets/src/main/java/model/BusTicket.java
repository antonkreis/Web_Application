package model;

public class BusTicket extends Ticket{
    enum BusType{
        city,
        regional,
        international
    }
    public String bus_stop_name;
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
