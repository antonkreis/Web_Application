package model;

public abstract class Ticket implements TicketActions{
    private String clientName;
    private int amountOfTickets;
    private class Date {
        public int day;
        public int month;
        public int year;
    }
}
