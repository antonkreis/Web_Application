package web;

import database.*;
import model.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@EnableAsync
public class VIPTickets {
    private Integer idCounter;
    private TicketsCollection ticketsCollection;

    public VIPTickets(){
        idCounter = 0;
        ticketsCollection = new TicketsCollection();
    }

    @Async
    //@Scheduled(fixedRate = 500)
    @Scheduled(cron = "30 * * * * 1-5") // every 30 seconds on MON-FRI
    public synchronized void createVIPTicket(){

        BusTicket busTicket = new BusTicket("VIP-"+idCounter.toString(),BusTicket.BusType.CITY, "Minsk", "Anton", 1000, LocalDate.of(2000,10,10));
        PlaneTicket planeTicket = new PlaneTicket("VIP-"+idCounter.toString(), PlaneTicket.SeatClass.ECONOMY, "MSQ", true, "Anton", 10, LocalDate.of(2000, 10, 10));
        TrainTicket trainTicket = new TrainTicket("VIP-"+ idCounter.toString(), TrainTicket.SeatType.SEAT, "Minsk", true, "Anton", 10, LocalDate.of(2020, 10, 10));
        if(ticketsCollection.containsBusTicket("B-VIP" + idCounter.toString()) || ticketsCollection.containsPlaneTicket("P-VIP" + idCounter.toString())
                || ticketsCollection.containsTrainTicket("T-VIP" + idCounter.toString())){
            throw new AlreadyExistException();
        }
        ticketsCollection.addBusTicket("B-VIP" + idCounter.toString(), busTicket);
        ticketsCollection.addPlaneTicket("P-VIP" + idCounter.toString(), planeTicket);
        ticketsCollection.addTrainTicket("T-VIP" + idCounter.toString(), trainTicket);
        idCounter++;
    }
}
