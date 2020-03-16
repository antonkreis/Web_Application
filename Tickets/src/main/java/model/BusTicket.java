package model;

import java.time.LocalDate;

public class BusTicket extends Ticket{
    public enum BusType{
        CITY,
        REGIONAL,
        INTERNATIONAL
    }
    private BusType busType;
    private String busStopName;
    public BusTicket(){

    }
    public BusTicket(String id, BusType busType, String busStopName, String clientName, int amountOfTickets, LocalDate date){
        super("B"+id, clientName, amountOfTickets, date);
        this.busType=busType;
        this.busStopName=busStopName;
    }
    public void buy(){
        System.out.println("Bus_Ticket::buy was called");
    }
    public void cancel(){
        System.out.println("Bus_Ticket::cancel was called");
    }
    public void show(){
        System.out.println("Bus_Ticket::show was called");
        System.out.println(busType.name());
        System.out.println(busStopName);
        System.out.println(getClientName());
        System.out.println(getAmountOfTickets());
        System.out.println(getDate().toString());
    }
    public String toString(){
        return "Bus ticket: ID: " + getId() + "; Type: " + busType.name() + "; Bus stop name: " + busStopName + "; Client name: " + getClientName() + "; Amount: " + String.valueOf(getAmountOfTickets()) + "; Date: " + date.toString();
    }
}
