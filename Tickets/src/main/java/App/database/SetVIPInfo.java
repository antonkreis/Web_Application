package App.database;

import App.model.BusTicket;
import App.model.PlaneTicket;
import App.model.TrainTicket;

import java.time.LocalDate;
import java.util.HashMap;

public class SetVIPInfo {
    private static BusTicket busVIPTicket;
    private static PlaneTicket planeVIPTicket;
    private static TrainTicket trainVIPTicket;

    public SetVIPInfo(){
        busVIPTicket = new BusTicket("VIP-default",BusTicket.BusType.CITY, "Minsk", "Anton", 1000, LocalDate.of(2000,10,10));
        planeVIPTicket = new PlaneTicket("VIP-default", PlaneTicket.SeatClass.ECONOMY, "MSQ", true, "Anton", 10, LocalDate.of(2000, 10, 10));
        trainVIPTicket = new TrainTicket("VIP-default", TrainTicket.SeatType.SEAT, "Minsk", true, "Anton", 10, LocalDate.of(2020, 10, 10));
    }

    public BusTicket getBusVIPTicket(){ return busVIPTicket; }

    public PlaneTicket getPlaneVIPTicket(){ return planeVIPTicket; }

    public TrainTicket getTrainVIPTicket(){ return trainVIPTicket; }

    public void setBusVIPTicket(String id, BusTicket.BusType busType, String busStopName, String clientName, int amountOfTickets, LocalDate date){ busVIPTicket.setInfo(id, busType, busStopName, clientName, amountOfTickets, date); }

    public void setPlaneVIPTicket(String id, PlaneTicket.SeatClass seatClass, String airportName, boolean luggageIncluded, String clientName, int amountOfTickets, LocalDate date){ planeVIPTicket.setInfo(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, date); }

    public void setTrainVIPTicket(String id, TrainTicket.SeatType seatType, String railwayStationName, boolean mealsIncluded, String clientName, int amountOfTickets, LocalDate date){ trainVIPTicket.setInfo(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, date); }
}
