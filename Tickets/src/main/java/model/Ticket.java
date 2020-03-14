package model;

import java.time.LocalDate;

public abstract class Ticket implements TicketActions{
    private String id;
    private String clientName;
    private int amountOfTickets;
//    private class Date {
//        public int day;
//        public int month;
//        public int year;
//    }
    /*LocalDate*/ Date date;
    public Ticket(){

    }
    public Ticket(String id, String clientName, int amountOfTickets, Date date){
        this.id=id;
        this.clientName=clientName;
        this.amountOfTickets=amountOfTickets;
        this.date = date;
    }
    public String getClientName(){
        return clientName;
    }
    public String getId(){ return id; }
    public int getAmountOfTickets(){
        return amountOfTickets;
    }
    public Date getDate(){
        return date;
    }
}
