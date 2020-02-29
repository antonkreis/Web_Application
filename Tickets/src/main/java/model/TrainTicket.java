package model;

public class TrainTicket extends Ticket {
    enum SeatType{
        seat,
        compartment
    }
    boolean meals_included;
    public String railway_station_name;
    public void buy(){
        System.out.println("Train_Ticket::buy was called");
    }
    public void cancel(){
        System.out.println("Train_Ticket::cancel was called");
    }
    public void show(){
        System.out.println("Train_Ticket::show was called");
    }
}
