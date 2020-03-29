package web;

import model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
    public ResponseEntity<Object> allBus() {
        //return busTicketsList.toString();
        return new ResponseEntity<>(busTicketsList.toString(), HttpStatus.OK);
    }

    @GetMapping("/all/plane")
    public ResponseEntity<Object> allPlane() {
        System.out.println(planeTicketsList.toString());
        //return planeTicketsList.toString();
        return new ResponseEntity<>(planeTicketsList.toString(), HttpStatus.OK);
    }

    @GetMapping("/all/train")
    public ResponseEntity<Object> allTrain() {
        //return trainTicketsList.toString();
        return new ResponseEntity<>(trainTicketsList.toString(), HttpStatus.OK);
    }

    @GetMapping("/bus/{id}")
    public ResponseEntity<Object> bus(@PathVariable String id) {
        if(!busTicketsList.containsKey(id)) {
            throw new NotFoundException(id);
        }
        return new ResponseEntity<>(busTicketsList.get(id).toString(), HttpStatus.OK);
    }

    @GetMapping("/plane/{id}")
    public ResponseEntity<Object> plane(@PathVariable String id) {
        if(!planeTicketsList.containsKey(id)) {
            throw new NotFoundException(id);
        }
        return new ResponseEntity<>(planeTicketsList.get(id).toString(), HttpStatus.OK);
    }

    @GetMapping("/train/{id}")
    public ResponseEntity<Object> train(@PathVariable String id) {
        if(trainTicketsList.containsKey(id)) {
            throw new NotFoundException(id);
        }
        return new ResponseEntity<>(trainTicketsList.get(id).toString(), HttpStatus.OK);
    }

    @PostMapping("/create/bus/{id}/{busType}/{busStopName}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public ResponseEntity<Object> createBus(@PathVariable String id,@PathVariable BusTicket.BusType busType,@PathVariable String busStopName,@PathVariable String clientName,@PathVariable Integer amountOfTickets,@PathVariable Integer day,@PathVariable Integer month,@PathVariable Integer year) {
        if(!busTicketsList.containsKey(id)) {
            try {
                BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
                busTicketsList.put(id, newBusTicket);
            }
            catch(Exception e){
                throw new DateException();
            }
            return new ResponseEntity<>("Created.", HttpStatus.OK);
        }
        throw new AlreadyExistException();
    }

    @PostMapping("/create/plane/{id}/{seatClass}/{airportName}/{luggageIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public ResponseEntity<Object> createPlane(@PathVariable String id,@PathVariable PlaneTicket.SeatClass seatClass, @PathVariable String airportName, @PathVariable boolean luggageIncluded, @PathVariable String clientName,@PathVariable Integer amountOfTickets,@PathVariable Integer day,@PathVariable Integer month,@PathVariable Integer year) {
        if(!planeTicketsList.containsKey(id)) {
            try {
                PlaneTicket newPlaneTicket = new PlaneTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
                planeTicketsList.put(id, newPlaneTicket);
            }
            catch(Exception e){
                throw new DateException();
                //System.out.println(e.toString());
                //return "Time error.";
            }
            return new ResponseEntity<>("Created.", HttpStatus.OK);
        }
        throw new AlreadyExistException();
    }

    @PostMapping("/create/train/{id}/{seatType}/{railwayStationName}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public ResponseEntity<Object> createTrain(@PathVariable String id, @PathVariable TrainTicket.SeatType seatType, @PathVariable String railwayStationName, @PathVariable boolean mealsIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!trainTicketsList.containsKey(id)) {
            try {
                TrainTicket newTrainTicket = new TrainTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
                trainTicketsList.put(id, newTrainTicket);
            }
            catch(Exception e){
                throw new DateException();
               // System.out.println(e.toString());
              //  return "Time error.";
            }
            return new ResponseEntity<>("Created.", HttpStatus.OK);
        }
        throw new AlreadyExistException();
    }


    @PutMapping("/update/bus/{id}/{busType}/{busStopName}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public ResponseEntity<Object> updateBus(@PathVariable String id,@PathVariable BusTicket.BusType busType,@PathVariable String busStopName,@PathVariable String clientName,@PathVariable Integer amountOfTickets,@PathVariable Integer day,@PathVariable Integer month,@PathVariable Integer year) {
        String response = "Created.";
        if(busTicketsList.containsKey(id)) {
            busTicketsList.remove(id);
            response= "Updated.";
        }
        try {
            BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
            busTicketsList.put(id, newBusTicket);
        }
        catch(Exception e){
            throw new DateException();
           // System.out.println(e.toString());
          //  return "Time error.";
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PutMapping("/update/plane/{id}/{seatClass}/{airportName}/{luggageIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public ResponseEntity<Object> updatePlane(@PathVariable String id,@PathVariable PlaneTicket.SeatClass seatClass, @PathVariable String airportName, @PathVariable boolean luggageIncluded, @PathVariable String clientName,@PathVariable Integer amountOfTickets,@PathVariable Integer day,@PathVariable Integer month,@PathVariable Integer year) {
        String response = "Created.";
        if(planeTicketsList.containsKey(id)) {
            planeTicketsList.remove(id);
            response = "Updated.";
        }
        try {
            PlaneTicket newPlaneTicket = new PlaneTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
            planeTicketsList.put(id, newPlaneTicket);
        }
        catch(Exception e){
            throw new DateException();
           // System.out.println(e.toString());
          //  return "Time error.";
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/update/train/{id}/{seatType}/{railwayStationName}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public ResponseEntity<Object> updateTrain(@PathVariable String id, @PathVariable TrainTicket.SeatType seatType, @PathVariable String railwayStationName, @PathVariable boolean mealsIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Created.";
        if(trainTicketsList.containsKey(id)) {
            trainTicketsList.remove(id);
            response = "Updated.";
        }
        try {
            TrainTicket newTrainTicket = new TrainTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
            trainTicketsList.put(id, newTrainTicket);
        }
        catch(Exception e){
            throw new DateException();
            //System.out.println(e.toString());
           // return "Time error.";
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/bus/{id}")
    public ResponseEntity<Object> deleteBus(@PathVariable String id){
        if(!busTicketsList.containsKey(id)){
            throw new NotFoundException(id);
        }
        busTicketsList.remove(id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }



    @DeleteMapping("/delete/plane/{id}")
    public ResponseEntity<Object> deletePlane(@PathVariable String id){
        if(!planeTicketsList.containsKey(id)){
            throw new NotFoundException(id);
        }
        planeTicketsList.remove(id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/train/{id}")
    public ResponseEntity<Object> deleteTrain(@PathVariable String id){
        if(!trainTicketsList.containsKey(id)){
            throw new NotFoundException(id);
        }
        trainTicketsList.remove(id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}



