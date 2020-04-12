package database;

import model.BusTicket;
import model.PlaneTicket;
import model.TrainTicket;

import java.util.HashMap;

public class TicketsCollection {
    private static HashMap<String, BusTicket> busTicketsList;
    private static HashMap<String, PlaneTicket> planeTicketsList;
    private static HashMap<String, TrainTicket> trainTicketsList;

    public TicketsCollection(){
        busTicketsList = new HashMap<String, BusTicket>();
        planeTicketsList = new HashMap<String, PlaneTicket>();
        trainTicketsList = new HashMap<String, TrainTicket>();
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
