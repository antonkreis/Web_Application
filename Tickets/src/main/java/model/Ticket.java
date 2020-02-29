package model;

public abstract class Ticket implements TicketActions{
    public String client_name;
    public int amount_of_tickets;
    public class Date {
        public int day;
        public int month;
        public int year;
    }
}
