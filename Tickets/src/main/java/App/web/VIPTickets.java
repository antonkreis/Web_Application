package App.web;

import App.database.*;
import App.model.BusTicket;
import App.model.PlaneTicket;
import App.model.TrainTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class VIPTickets {
    @Autowired
    private BusTicketRepository busTicketRepository;
    @Autowired
    private PlaneTicketRepository planeTicketRepository;
    @Autowired
    private TrainTicketRepository trainTicketRepository;

    private Integer idCounter;
    private TicketsCollection ticketsCollection;

    public VIPTickets(){
        idCounter = 0;
        ticketsCollection = new TicketsCollection();
    }

    @Async
    //@Scheduled(fixedRate = 500)
    @Scheduled(cron = "30 * * * * 1-7") // every 30 seconds on MON-FRI
    public synchronized void createVIPTicket(){
        BusTicket busTicket = ticketsCollection.getBusVIPTicket();
        PlaneTicket planeTicket = ticketsCollection.getPlaneVIPTicket();
        TrainTicket trainTicket = ticketsCollection.getTrainVIPTicket();
        busTicket.setId("B-VIP" + idCounter.toString());
        planeTicket.setId("P-VIP" + idCounter.toString());
        trainTicket.setId("T-VIP" + idCounter.toString());
        //BusTicket busTicket = new BusTicket("VIP-"+idCounter.toString(),BusTicket.BusType.CITY, "Minsk", "Anton", 1000, LocalDate.of(2000,10,10));
        //PlaneTicket planeTicket = new PlaneTicket("VIP-"+idCounter.toString(), PlaneTicket.SeatClass.ECONOMY, "MSQ", true, "Anton", 10, LocalDate.of(2000, 10, 10));
        //TrainTicket trainTicket = new TrainTicket("VIP-"+ idCounter.toString(), TrainTicket.SeatType.SEAT, "Minsk", true, "Anton", 10, LocalDate.of(2020, 10, 10));
//        if(ticketsCollection.containsBusTicket("B-VIP" + idCounter.toString()) || ticketsCollection.containsPlaneTicket("P-VIP" + idCounter.toString())
//                || ticketsCollection.containsTrainTicket("T-VIP" + idCounter.toString())){
//            throw new AlreadyExistException();
//        }
        ticketsCollection.addBusTicket("B-VIP" + idCounter.toString(), busTicket);
        ticketsCollection.addPlaneTicket("P-VIP" + idCounter.toString(), planeTicket);
        ticketsCollection.addTrainTicket("T-VIP" + idCounter.toString(), trainTicket);
        if(busTicketRepository.existsById("B-VIP" + idCounter.toString()) || planeTicketRepository.existsById("P-VIP" + idCounter.toString())
                || trainTicketRepository.existsById("T-VIP" + idCounter.toString())){
            idCounter++;
            throw new AsyncAlreadyExistException();
        }else{
            busTicketRepository.save(busTicket);
            planeTicketRepository.save(planeTicket);
            trainTicketRepository.save(trainTicket);
        }


        idCounter++;
    }
}
