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
    private SetVIPInfo setVIPInfo;

    public VIPTickets(){
        idCounter = 0;
        setVIPInfo = new SetVIPInfo();
    }

    @Async
    //@Scheduled(fixedRate = 500)
    @Scheduled(cron = "30 * * * * 1-7") // every 30 seconds on MON-SUN
    public synchronized void createVIPTicket(){
        BusTicket busTicket = setVIPInfo.getBusVIPTicket();
        PlaneTicket planeTicket = setVIPInfo.getPlaneVIPTicket();
        TrainTicket trainTicket = setVIPInfo.getTrainVIPTicket();
        busTicket.setId("B-VIP" + idCounter.toString());
        planeTicket.setId("P-VIP" + idCounter.toString());
        trainTicket.setId("T-VIP" + idCounter.toString());
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
