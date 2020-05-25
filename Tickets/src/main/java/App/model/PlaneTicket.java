package App.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
//import com.sun.org.apache.bcel.internal.generic.PUSH;

@Entity
public class PlaneTicket extends Ticket implements Serializable {
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
    public PlaneTicket(String id, SeatClass seatClass, String airportName, boolean luggageIncluded, String clientName, int amountOfTickets, LocalDate date){
        super("P"+id, clientName, amountOfTickets, date);
        this.seatClass = seatClass;
        this.luggageIncluded = luggageIncluded;
        this.airportName = airportName;
    }
    public void setInfo(String id, SeatClass seatClass, String airportName, boolean luggageIncluded, String clientName, int amountOfTickets, LocalDate date){
        super.setInfo(id, clientName, amountOfTickets, date);
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
        System.out.println(getDate().toString());
    }
    public String toString(){
        return "Plane ticket: ID: " + getId()
                + "; Type: " + seatClass.toString() +
                "; Airport name: " + airportName + "; Luggage included: " +
                String.valueOf(luggageIncluded) + "; Client name: " + getClientName() +
                "; Amount of tickets: " + String.valueOf(getAmountOfTickets()) +
                "; Date: " + date.toString();
    }
}
