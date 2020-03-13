package model;

//import com.sun.org.apache.bcel.internal.generic.PUSH;

public class PlaneTicket extends Ticket{
    public enum SeatClass{
        ECONOMY,
        BUSINESS,
        FIRST
    }
    private SeatClass seatClass;
    private String airportName;
    private boolean luggageIncluded;
    public PlaneTicket(){

    }
    public PlaneTicket(SeatClass seatClass, String airportName, boolean luggageIncluded, String clientName, int amountOfTickets, Date date){
        super(clientName, amountOfTickets, date);
        this.seatClass = seatClass;
        this.luggageIncluded = luggageIncluded;
        this.airportName = airportName;
    }
    public void buy(){
        System.out.println("Plane_Ticket::buy was called");
    }
    public void cancel(){
        System.out.println("Plane_Ticket::cancel was called");
    }
    public void show(){
        System.out.println("Plane_Ticket::show was called");
        System.out.println(seatClass.name());
        System.out.println(airportName);
        System.out.println(luggageIncluded);
        System.out.println(getClientName());
        System.out.println(getAmountOfTickets());
        System.out.println(getDate().getDay());
        System.out.println(getDate().getMonth());
        System.out.println(getDate().getYear());
    }
    public String toString(){
        return "Plane ticket: Type: " + seatClass.toString() + airportName + String.valueOf(luggageIncluded)+getClientName()+String.valueOf(getAmountOfTickets());
    }
}
