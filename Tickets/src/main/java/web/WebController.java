package web;

import model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.HashMap;

@RestController
public class WebController {
    public  HashMap<String, BusTicket> busTicketsList = new HashMap<String, BusTicket>();
    public  HashMap<String, PlaneTicket> planeTicketsList = new HashMap<String, PlaneTicket>();
    public  HashMap<String, TrainTicket> trainTicketsList = new HashMap<String, TrainTicket>();

    @GetMapping("/all")
    public String all() {
        return "Bus tickets: " + busTicketsList.toString() + " \nPlane tickets: " +  planeTicketsList.toString() + " \nTrain tickets: " + trainTicketsList.toString();
    }

    @GetMapping("/all/bus")
    public String allBus() {
        return busTicketsList.toString();
    }

    @GetMapping("/all/plane")
    public String allPlane() {
        System.out.println(planeTicketsList.toString());
        return planeTicketsList.toString();
    }

    @GetMapping("/all/train")
    public String allTrain() {
        return trainTicketsList.toString();
    }

    @GetMapping("/bus/{id}")
    public String bus(@PathVariable String id) {
        if(busTicketsList.containsKey(id)) {
            return busTicketsList.get(id).toString();
        }
        return "The bus ticket was not found.";
    }

    @GetMapping("/plane/{id}")
    public String plane(@PathVariable String id) {
        if(planeTicketsList.containsKey(id)) {
            return planeTicketsList.get(id).toString();
        }
        return "The plane ticket was not found.";
    }

    @GetMapping("/train/{id}")
    public String train(@PathVariable String id) {
        if(trainTicketsList.containsKey(id)) {
            return trainTicketsList.get(id).toString();
        }
        return "The train ticket was not found.";
    }

    @PostMapping("/create/bus/{id}")
    public String createBusDefault(@PathVariable String id) {
        if(!busTicketsList.containsKey(id)) {
            BusTicket newBusTicket = new BusTicket(id, BusTicket.BusType.CITY, "Central", "Anton", 5, LocalDate.of(2020, 7, 9));
            busTicketsList.put(id, newBusTicket);
            return "Created.";
        }
        return "The ticket with this ID already exists";
    }

    @PostMapping("/create/bus/{id}/{busType}/{busStopName}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public String createBus(@PathVariable String id,@PathVariable BusTicket.BusType busType,@PathVariable String busStopName,@PathVariable String clientName,@PathVariable Integer amountOfTickets,@PathVariable Integer day,@PathVariable Integer month,@PathVariable Integer year) {
        if(!busTicketsList.containsKey(id)) {
            try {
                BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
                busTicketsList.put(id, newBusTicket);
            }
            catch(Exception e){
                System.out.println(e.toString());
                return "Time error.";
            }
            return "Created.";
        }
        return "The ticket with this ID already exists";
    }

    @PostMapping("/create/plane/{id}")
    public String createPlaneDefault(@PathVariable String id) {
        if(!planeTicketsList.containsKey(id)) {
            PlaneTicket newPlaneTicket = new PlaneTicket(id, PlaneTicket.SeatClass.BUSINESS, "MSQ", true, "Anton", 56, LocalDate.of(2020, 7, 9));
            planeTicketsList.put(id, newPlaneTicket);
            return "Created.";
        }
        return "The ticket with this ID already exists";
    }

    @PostMapping("/create/plane/{id}/{seatClass}/{airportName}/{luggageIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public String createPlane(@PathVariable String id,@PathVariable PlaneTicket.SeatClass seatClass, @PathVariable String airportName, @PathVariable boolean luggageIncluded, @PathVariable String clientName,@PathVariable Integer amountOfTickets,@PathVariable Integer day,@PathVariable Integer month,@PathVariable Integer year) {
        if(!planeTicketsList.containsKey(id)) {
            try {
                PlaneTicket newPlaneTicket = new PlaneTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
                planeTicketsList.put(id, newPlaneTicket);
            }
            catch(Exception e){
                System.out.println(e.toString());
                return "Time error.";
            }
            return "Created.";
        }
        return "The ticket with this ID already exists";
    }

    @PostMapping("/create/train/{id}")
    public String createTrainDefault(@PathVariable String id) {
        if(!trainTicketsList.containsKey(id)) {
            TrainTicket newTrainTicket = new TrainTicket(id, TrainTicket.SeatType.COMPARTMENT, "Minsk-Passenger", true, "Anton", 5, LocalDate.of(2020, 7, 9));
            trainTicketsList.put(id, newTrainTicket);
            return "Created.";
        }
        return "The ticket with this ID already exists";
    }

    @PostMapping("/create/train/{id}/{seatType}/{railwayStationName}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public String createTrain(@PathVariable String id, @PathVariable TrainTicket.SeatType seatType, @PathVariable String railwayStationName, @PathVariable boolean mealsIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!trainTicketsList.containsKey(id)) {
            try {
                TrainTicket newTrainTicket = new TrainTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
                trainTicketsList.put(id, newTrainTicket);
            }
            catch(Exception e){
                System.out.println(e.toString());
                return "Time error.";
            }
            return "Created.";
        }
        return "The ticket with this ID already exists";
    }

    @PutMapping("/update/bus/{id}")
    public String updateBusDefault(@PathVariable String id) {
        String response = "Updated.";
        if(!busTicketsList.containsKey(id)) {
            busTicketsList.remove(id);
            response= "Created.";
        }
            BusTicket newBusTicket = new BusTicket(id, BusTicket.BusType.CITY, "Central", "Anton", 5, LocalDate.of(2020, 7, 9));
            busTicketsList.put(id, newBusTicket);
            return response;
    }

    @PutMapping("/update/bus/{id}/{busType}/{busStopName}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public String updateBus(@PathVariable String id,@PathVariable BusTicket.BusType busType,@PathVariable String busStopName,@PathVariable String clientName,@PathVariable Integer amountOfTickets,@PathVariable Integer day,@PathVariable Integer month,@PathVariable Integer year) {
        String response = "Updated.";
        if(!busTicketsList.containsKey(id)) {
            busTicketsList.remove(id);
            response= "Created.";
        }
        try {
            BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
            busTicketsList.put(id, newBusTicket);
        }
        catch(Exception e){
            System.out.println(e.toString());
            return "Time error.";
        }
        return response;
    }

    @PutMapping("/update/plane/{id}")
    public String updatePlaneDefault(@PathVariable String id) {
        String response = "Updated.";
        if(!planeTicketsList.containsKey(id)) {
            planeTicketsList.remove(id);
            response = "Created.";
        }
            PlaneTicket newPlaneTicket = new PlaneTicket(id, PlaneTicket.SeatClass.BUSINESS, "MSQ", true, "Anton", 56, LocalDate.of(2020, 7, 9));
            planeTicketsList.put(id, newPlaneTicket);
            System.out.println(planeTicketsList.get(id));
            return response;
    }

    @PutMapping("/update/plane/{id}/{seatClass}/{airportName}/{luggageIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public String updatePlane(@PathVariable String id,@PathVariable PlaneTicket.SeatClass seatClass, @PathVariable String airportName, @PathVariable boolean luggageIncluded, @PathVariable String clientName,@PathVariable Integer amountOfTickets,@PathVariable Integer day,@PathVariable Integer month,@PathVariable Integer year) {
        String response = "Updated.";
        if(!planeTicketsList.containsKey(id)) {
            planeTicketsList.remove(id);
            response = "Created.";
        }
        try {
            PlaneTicket newPlaneTicket = new PlaneTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
            planeTicketsList.put(id, newPlaneTicket);
        }
        catch(Exception e){
            System.out.println(e.toString());
            return "Time error.";
        }
        return response;
    }

    @PutMapping("/update/train/{id}")
    public String updateTrainDefault(@PathVariable String id) {
        String response = "Updated.";
        if(!trainTicketsList.containsKey(id)) {
            trainTicketsList.remove(id);
            response = "Created.";
        }
        TrainTicket newTrainTicket = new TrainTicket(id, TrainTicket.SeatType.COMPARTMENT, "Minsk-Passenger", true, "Anton", 5, LocalDate.of(2020, 7, 9));
        trainTicketsList.put(id, newTrainTicket);
        return response;
    }

    @PutMapping("/update/train/{id}/{seatType}/{railwayStationName}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public String updateTrain(@PathVariable String id, @PathVariable TrainTicket.SeatType seatType, @PathVariable String railwayStationName, @PathVariable boolean mealsIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Updated.";
        if(!trainTicketsList.containsKey(id)) {
            trainTicketsList.remove(id);
            response = "Created.";
        }
        try {
            TrainTicket newTrainTicket = new TrainTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
            trainTicketsList.put(id, newTrainTicket);
        }
        catch(Exception e){
            System.out.println(e.toString());
            return "Time error.";
        }
        return response;
    }

    @DeleteMapping("/delete/bus/{id}")
    public String deleteBus(@PathVariable String id){
        if(busTicketsList.containsKey(id)){
            busTicketsList.remove(id);
            return "Deleted.";
        }
        return "The bus ticket was not found.";
    }



    @DeleteMapping("/delete/plane/{id}")
    public String deletePlane(@PathVariable String id){
        if(planeTicketsList.containsKey(id)){
            planeTicketsList.remove(id);
            return "Deleted.";
        }
        return "The plane ticket was not found.";
    }

    @DeleteMapping("/delete/train/{id}")
    public String deleteTrain(@PathVariable String id){
        if(trainTicketsList.containsKey(id)){
            trainTicketsList.remove(id);
            return "Deleted.";
        }
        return "The train ticket was not found.";
    }
}



