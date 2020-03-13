package model;

public class TrainTicket extends Ticket {
    public enum SeatType{
        SEAT,
        COMPARTMENT
    }
    private SeatType seatType;
    private boolean mealsIncluded;
    private String railwayStationName;
    public TrainTicket(){

    }
    public TrainTicket(SeatType seatType, String railwayStationName, boolean mealsIncluded, String clientName, int amountOfTickets, Date date){
        super(clientName, amountOfTickets, date);
        this.seatType=seatType;
        this.mealsIncluded=mealsIncluded;
        this.railwayStationName=railwayStationName;
    }
    public void buy(){
        System.out.println("Train_Ticket::buy was called");
    }
    public void cancel(){
        System.out.println("Train_Ticket::cancel was called");
    }
    public void show(){
        System.out.println("Train_Ticket::show was called");
        System.out.println(seatType.name());
        System.out.println(mealsIncluded);
        System.out.println(railwayStationName);
        System.out.println(getClientName());
        System.out.println(getAmountOfTickets());
        System.out.println(getDate().getDay());
        System.out.println(getDate().getMonth());
        System.out.println(getDate().getYear());
    }
    public String toString(){
        return "T ticket: Type: " +seatType.name() + String.valueOf(mealsIncluded) + railwayStationName + getClientName() + getAmountOfTickets();
    }
}
