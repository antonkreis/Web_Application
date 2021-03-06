package App.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public abstract class Ticket implements TicketActions{
    @Id
    private String id;
    private String clientName;
    private int amountOfTickets;
//    private class Date {
//        public int day;
//        public int month;
//        public int year;
//    }
    /*LocalDate*/ LocalDate date;
    public Ticket(){

    }
    public Ticket(String id, String clientName, int amountOfTickets, LocalDate date){
        this.id=id;
        this.clientName=clientName;
        this.amountOfTickets=amountOfTickets;
        this.date = date;
    }
    public void setInfo(String id, String clientName, int amountOfTickets, LocalDate date){
        this.id=id;
        this.clientName=clientName;
        this.amountOfTickets=amountOfTickets;
        this.date = date;
    }
    public String getClientName(){
        return clientName;
    }
    public String getId(){ return id; }
    public void  setId(String id){ this.id = id; }
    public int getAmountOfTickets(){
        return amountOfTickets;
    }
    public LocalDate getDate(){
        return date;
    }
}
