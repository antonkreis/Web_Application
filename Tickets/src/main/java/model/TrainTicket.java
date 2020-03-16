package model;

import java.time.LocalDate;

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
    public TrainTicket(String id, SeatType seatType, String railwayStationName, boolean mealsIncluded, String clientName, int amountOfTickets, LocalDate date){
        super("T"+id, clientName, amountOfTickets, date);
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
        System.out.println(getDate().toString());
    }
    public String toString(){
        return "Train ticket: ID: " + getId() + "; Type: " +seatType.name() + "; Meals included: " + String.valueOf(mealsIncluded) + "; Railway station name: " + railwayStationName + "; Client name: " + getClientName() + "; Amount of tickets: " + getAmountOfTickets() + "; Date: " + date.toString();
    }
}
