package controller;

import model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.HashMap;

@RestController
public class WebController {
    public  HashMap<String, BusTicket> busTicketsList = new HashMap<String, BusTicket>();
    public  HashMap<String, PlaneTicket> planeTicketsList = new HashMap<String, PlaneTicket>();
    public  HashMap<String, TrainTicket> trainTicketsList = new HashMap<String, TrainTicket>();

    @GetMapping("/all")
    public String all() {
        return busTicketsList.get("bus").toString() + " " +  planeTicketsList.get("bus").toString() + " " + trainTicketsList.get("bus").toString();
    }

    @PutMapping("/create/bus/{id}")
    public void createBus(@PathVariable String id) {
        BusTicket newBusTicket = new BusTicket(BusTicket.BusType.CITY, "Central", "Anton", 5, new Date(9,7,2020));
        busTicketsList.put("bus", newBusTicket);
    }

    @DeleteMapping("/delete/bus/{id}")
    public void deleteBus(@PathVariable String id){
        if(busTicketsList.containsKey(id)){
            busTicketsList.remove(id);
        }
    }
}



