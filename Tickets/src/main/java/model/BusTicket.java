package model;

public class BusTicket extends Ticket{
    private String id;
    public enum BusType{
        CITY,
        REGIONAL,
        INTERNATIONAL
    }
    private BusType busType;
    private String busStopName;
    public BusTicket(){

    }
    public BusTicket(BusType busType, String busStopName, String clientName, int amountOfTickets, Date date){
        super(clientName, amountOfTickets, date);
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
        System.out.println(getDate().getDay());
        System.out.println(getDate().getMonth());
        System.out.println(getDate().getYear());
    }
    public String toString(){
        return "Bus ticket: Type: " + busType.name() + "; Bus stop name: " + busStopName + "; Client name: " + getClientName() + "; Amount: " + String.valueOf(getAmountOfTickets());
    }
}
