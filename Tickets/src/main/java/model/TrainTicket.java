package model;

public class TrainTicket extends Ticket {
    private enum SeatType{
        SEAT,
        COMPARTMENT
    }
    private SeatType seatType;
    private boolean mealsIncluded;
    private String railwayStationName;
    public void buy(){
        System.out.println("Train_Ticket::buy was called");
        //seat_type = SeatType.SEAT;
    }
    public void cancel(){
        System.out.println("Train_Ticket::cancel was called");
    }
    public void show(){
        System.out.println("Train_Ticket::show was called");
    }
}
