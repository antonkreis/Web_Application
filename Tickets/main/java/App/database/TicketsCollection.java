package App.database;

import App.model.BusTicket;
import App.model.PlaneTicket;
import App.model.TrainTicket;

import java.time.LocalDate;
import java.util.HashMap;

public class TicketsCollection {
    private static HashMap<String, BusTicket> busTicketsList;
    private static HashMap<String, PlaneTicket> planeTicketsList;
    private static HashMap<String, TrainTicket> trainTicketsList;
    private static BusTicket busVIPTicket;
    private static PlaneTicket planeVIPTicket;
    private static TrainTicket trainVIPTicket;

    public TicketsCollection(){
        busTicketsList = new HashMap<String, BusTicket>();
        planeTicketsList = new HashMap<String, PlaneTicket>();
        trainTicketsList = new HashMap<String, TrainTicket>();
        busVIPTicket = new BusTicket("VIP-default",BusTicket.BusType.CITY, "Minsk", "Anton", 1000, LocalDate.of(2000,10,10));
        planeVIPTicket = new PlaneTicket("VIP-default", PlaneTicket.SeatClass.ECONOMY, "MSQ", true, "Anton", 10, LocalDate.of(2000, 10, 10));
        trainVIPTicket = new TrainTicket("VIP-default", TrainTicket.SeatType.SEAT, "Minsk", true, "Anton", 10, LocalDate.of(2020, 10, 10));
    }

    public void addBusTicket(String id, BusTicket ticket){
        busTicketsList.put(id, ticket);
    }

    public void addPlaneTicket(String id, PlaneTicket ticket){
        planeTicketsList.put(id, ticket);
    }

    public void addTrainTicket(String id, TrainTicket ticket){
        trainTicketsList.put(id, ticket);
    }

    public BusTicket getBusTicket(String id){
        return busTicketsList.get(id);
    }

    public PlaneTicket getPlaneTicket(String id){
        return planeTicketsList.get(id);
    }

    public TrainTicket getTrainTicket(String id){
        return trainTicketsList.get(id);
    }

    public String getBusTicketsList(){
        return busTicketsList.toString();
    }

    public String getPlaneTicketsList(){
        return planeTicketsList.toString();
    }

    public String getTrainTicketsList(){
        return trainTicketsList.toString();
    }

    public BusTicket getBusVIPTicket(){ return busVIPTicket; }

    public PlaneTicket getPlaneVIPTicket(){ return planeVIPTicket; }

    public TrainTicket getTrainVIPTicket(){ return trainVIPTicket; }

    public void setBusVIPTicket(String id, BusTicket.BusType busType, String busStopName, String clientName, int amountOfTickets, LocalDate date){ busVIPTicket.setInfo(id, busType, busStopName, clientName, amountOfTickets, date); }

    public void setPlaneVIPTicket(String id, PlaneTicket.SeatClass seatClass, String airportName, boolean luggageIncluded, String clientName, int amountOfTickets, LocalDate date){ planeVIPTicket.setInfo(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, date); }

    public void setTrainVIPTicket(String id, TrainTicket.SeatType seatType, String railwayStationName, boolean mealsIncluded, String clientName, int amountOfTickets, LocalDate date){ trainVIPTicket.setInfo(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, date); }

    public Boolean containsBusTicket(String id){
        return busTicketsList.containsKey(id);
    }

    public Boolean containsPlaneTicket(String id){
        return planeTicketsList.containsKey(id);
    }

    public Boolean containsTrainTicket(String id){
        return trainTicketsList.containsKey(id);
    }

    public void removeBusTicket(String id){
        busTicketsList.remove(id);
    }

    public void removePlaneTicket(String id){
        planeTicketsList.remove(id);
    }

    public void removeTrainTicket(String id){
        trainTicketsList.remove(id);
    }
}
