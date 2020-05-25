package App.web;

import App.database.*;
import App.model.BusTicket;
import App.model.PlaneTicket;
import App.model.TrainTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.logging.Logger;

@RestController
public class WebController {
    @Autowired
    private BusTicketRepository busTicketRepository;
    @Autowired
    private PlaneTicketRepository planeTicketRepository;
    @Autowired
    private TrainTicketRepository trainTicketRepository;



    //public  HashMap<String, BusTicket> busTicketsList = new HashMap<String, BusTicket>();
    //public  HashMap<String, PlaneTicket> planeTicketsList = new HashMap<String, PlaneTicket>();
    //public  HashMap<String, TrainTicket> trainTicketsList = new HashMap<String, TrainTicket>();
    TicketsCollection ticketsCollection = new TicketsCollection();
    //VIPTickets status = new VIPTickets();


    @DeleteMapping("/delete/all")
    public synchronized ResponseEntity<Object> deleteAllTickets() {
        busTicketRepository.deleteAll();
        planeTicketRepository.deleteAll();
        trainTicketRepository.deleteAll();
        return new ResponseEntity<Object>("Deleted.", HttpStatus.OK);
    }


    @GetMapping("/all")
    public synchronized ResponseEntity<Object> showAllTickets() {
        return new ResponseEntity<Object>("Bus tickets: " + busTicketRepository.findAll().toString() + " \nPlane tickets: " +   planeTicketRepository.findAll().toString() + " \nTrain tickets: " +  trainTicketRepository.findAll().toString(), HttpStatus.OK);
    }


    @GetMapping("/all/bus")
    public synchronized ResponseEntity<Object> showAllBusTickets() {
        return new ResponseEntity<>(busTicketRepository.findAll().toString(), HttpStatus.OK);
    }


    @GetMapping("/all/plane")
    public synchronized ResponseEntity<Object> showAllPlaneTickets() {
        return new ResponseEntity<>(planeTicketRepository.findAll().toString(), HttpStatus.OK);
    }


    @GetMapping("/all/train")
    public synchronized ResponseEntity<Object> showAllTrainTickets() {
        return new ResponseEntity<>(trainTicketRepository.findAll().toString(), HttpStatus.OK);
    }


    @GetMapping("/bus/{id}")
    public synchronized ResponseEntity<Object> showBusTicket(@PathVariable String id) {
        if(!busTicketRepository.existsById("B"+id)) {
            throw new NotFoundException("B"+id);
        }
        return new ResponseEntity<>(busTicketRepository.findById("B"+id).toString(), HttpStatus.OK);
    }


    @GetMapping("/plane/{id}")
    public synchronized ResponseEntity<Object> showPlaneTicket(@PathVariable String id) {
        if(!planeTicketRepository.existsById("P"+id)) {
            throw new NotFoundException("P"+id);
        }
        return new ResponseEntity<>(planeTicketRepository.findById("P"+id).toString(), HttpStatus.OK);
    }

    @GetMapping("/train/{id}")
    public synchronized ResponseEntity<Object> showTrainTicket(@PathVariable String id) {
        if(!trainTicketRepository.existsById("T"+id)) {
            throw new NotFoundException("T"+id);
        }
        return new ResponseEntity<>(trainTicketRepository.findById("T"+id).toString(), HttpStatus.OK);
    }


    @PostMapping("/create/bus/{id}/{busType}/{busStopName}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> createBusTicket(@PathVariable String id, @PathVariable BusTicket.BusType busType, @PathVariable String busStopName, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!busTicketRepository.existsById("B"+id)) {
            try {
                BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
                busTicketRepository.save(newBusTicket);
            }
            catch(DateTimeException e){
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
            return new ResponseEntity<>("Created.", HttpStatus.OK);
        }
        throw new AlreadyExistException();
    }


    @PostMapping("/create/plane/{id}/{seatClass}/{airportName}/{luggageIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> createPlaneTicket(@PathVariable String id, @PathVariable PlaneTicket.SeatClass seatClass, @PathVariable String airportName, @PathVariable boolean luggageIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!planeTicketRepository.existsById("P"+id)) {
            try {
                PlaneTicket newPlaneTicket = new PlaneTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
                planeTicketRepository.save(newPlaneTicket);
            }
            catch(DateTimeException e){
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
            return new ResponseEntity<>("Created.", HttpStatus.OK);
        }
        throw new AlreadyExistException();
    }

    @PostMapping("/create/train/{id}/{seatType}/{railwayStationName}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> createTrainTicket(@PathVariable String id, @PathVariable TrainTicket.SeatType seatType, @PathVariable String railwayStationName, @PathVariable boolean mealsIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!trainTicketRepository.existsById("T"+id)) {
            try {
                TrainTicket newTrainTicket = new TrainTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
                trainTicketRepository.save(newTrainTicket);
            }
            catch(DateTimeException e){
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
            return new ResponseEntity<>("Created.", HttpStatus.OK);
        }
        throw new AlreadyExistException();
    }


    @PutMapping("/update/bus/{id}/{busType}/{busStopName}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> updateBusTicket(@PathVariable String id, @PathVariable BusTicket.BusType busType, @PathVariable String busStopName, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Created.";
        if(busTicketRepository.existsById("B"+id)) {
            busTicketRepository.deleteById("B"+id);
            response= "Updated.";
        }
        try {
            BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
            busTicketRepository.save(newBusTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/update/plane/{id}/{seatClass}/{airportName}/{luggageIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> updatePlaneTicket(@PathVariable String id, @PathVariable PlaneTicket.SeatClass seatClass, @PathVariable String airportName, @PathVariable boolean luggageIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Created.";
        if(planeTicketRepository.existsById("P"+id)) {
            planeTicketRepository.deleteById("P"+id);
            response = "Updated.";
        }
        try {
            PlaneTicket newPlaneTicket = new PlaneTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
            planeTicketRepository.save(newPlaneTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/update/train/{id}/{seatType}/{railwayStationName}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> updateTrainTicket(@PathVariable String id, @PathVariable TrainTicket.SeatType seatType, @PathVariable String railwayStationName, @PathVariable boolean mealsIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Created.";
        if(trainTicketRepository.existsById("T"+id)) {
            trainTicketRepository.deleteById("T"+id);
            response = "Updated.";
        }
        try {
            TrainTicket newTrainTicket = new TrainTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
            trainTicketRepository.save(newTrainTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/bus/{id}")
    public synchronized ResponseEntity<Object> deleteBusTicket(@PathVariable String id){
        if(!trainTicketRepository.existsById("B"+id)){
            throw new NotFoundException("B"+id);
        }
        trainTicketRepository.deleteById("B"+id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }



    @DeleteMapping("/delete/plane/{id}")
    public synchronized ResponseEntity<Object> deletePlaneTicket(@PathVariable String id){
        if(!planeTicketRepository.existsById("P"+id)){
            throw new NotFoundException("P"+id);
        }
        planeTicketRepository.deleteById("P"+id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/train/{id}")
    public synchronized ResponseEntity<Object> deleteTrainTicket(@PathVariable String id){
        if(!trainTicketRepository.existsById("T"+id)){
            throw new NotFoundException("T"+id);
        }
        trainTicketRepository.deleteById("T"+id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

    @PutMapping("/setVIPInfo/bus/{id}/{busType}/{busStopName}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> setVIPBusTicketInfo(@PathVariable String id, @PathVariable BusTicket.BusType busType, @PathVariable String busStopName, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        try {
            ticketsCollection.setBusVIPTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        return new ResponseEntity<>("Information added", HttpStatus.OK);
    }

    @PutMapping("/setVIPInfo/plane/{id}/{seatClass}/{airportName}/{luggageIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> setVIPPlaneTicketInfo(@PathVariable String id, @PathVariable PlaneTicket.SeatClass seatClass, @PathVariable String airportName, @PathVariable boolean luggageIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        try {
            ticketsCollection.setPlaneVIPTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        return new ResponseEntity<>("Information added", HttpStatus.OK);
    }

    @PutMapping("/setVIPInfo/train/{id}/{seatType}/{railwayStationName}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> setVIPTrainTicketInfo(@PathVariable String id, @PathVariable TrainTicket.SeatType seatType, @PathVariable String railwayStationName, @PathVariable boolean mealsIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        try {
            ticketsCollection.setTrainVIPTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        return new ResponseEntity<>("Information added", HttpStatus.OK);
    }


    @Transactional(rollbackFor = RuntimeException.class)
    @PostMapping("/create/three_types/{id}/{busType}/{seatClass}/{seatType}/{busStopName}/{airportName}/{railwayStationName}/{luggageIncluded}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> createBusPlaneTrainTicket(@PathVariable String id, @PathVariable BusTicket.BusType busType, @PathVariable PlaneTicket.SeatClass seatClass,
                                                                         @PathVariable TrainTicket.SeatType seatType, @PathVariable String busStopName,
                                                                         @PathVariable String airportName, @PathVariable String railwayStationName,
                                                                         @PathVariable boolean luggageIncluded, @PathVariable boolean mealsIncluded,
                                                                         @PathVariable String clientName, @PathVariable Integer amountOfTickets,
                                                                         @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!busTicketRepository.existsById("B"+id)) {
            try {
                BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
                busTicketRepository.save(newBusTicket);
            } catch (DateTimeException e) {
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
        }
        else{
            throw new AlreadyExistException();
        }
        if(!planeTicketRepository.existsById("P"+id)) {
            try {
                PlaneTicket newPlaneTicket = new PlaneTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
                planeTicketRepository.save(newPlaneTicket);
            } catch (DateTimeException e) {
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
        }
        else {
            throw new AlreadyExistException();
        }
        if(!trainTicketRepository.existsById("T"+id)) {
            try {
                TrainTicket newTrainTicket = new TrainTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
                trainTicketRepository.save(newTrainTicket);
            } catch (DateTimeException e) {
                throw new DateException();
            }
            catch(DataIntegrityViolationException e){
                throw new DatabaseException();
            }
        }
        else {
            throw new AlreadyExistException();
        }
        return new ResponseEntity<>("Created.", HttpStatus.OK);
    }


    @PostMapping("/update/three_types/{id}/{busType}/{seatClass}/{seatType}/{busStopName}/{airportName}/{railwayStationName}/{luggageIncluded}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> updateBusPlaneTrainTicket(@PathVariable String id, @PathVariable BusTicket.BusType busType, @PathVariable PlaneTicket.SeatClass seatClass,
                                                                         @PathVariable TrainTicket.SeatType seatType, @PathVariable String busStopName,
                                                                         @PathVariable String airportName, @PathVariable String railwayStationName,
                                                                         @PathVariable boolean luggageIncluded, @PathVariable boolean mealsIncluded,
                                                                         @PathVariable String clientName, @PathVariable Integer amountOfTickets,
                                                                         @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {


        String busTicketResponse = "Created.";
        String planeTicketResponse = "Created.";
        String trainTicketResponse = "Created.";
        if(busTicketRepository.existsById("B"+id)) {
            busTicketRepository.deleteById("B"+id);
            busTicketResponse = "Updated.";
        }
        try {
            BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
            busTicketRepository.save(newBusTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        if(planeTicketRepository.existsById("P"+id)) {
            planeTicketRepository.deleteById("P"+id);
            planeTicketResponse = "Updated.";
        }
        try {
            PlaneTicket newPlaneTicket = new PlaneTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
            planeTicketRepository.save(newPlaneTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        if(trainTicketRepository.existsById("T"+id)) {
            trainTicketRepository.deleteById("T"+id);
            trainTicketResponse = "Updated.";
        }
        try {
            TrainTicket newTrainTicket = new TrainTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
            trainTicketRepository.save(newTrainTicket);
        }
        catch(DateTimeException e){
            throw new DateException();
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException();
        }
        return new ResponseEntity<>("Bus Ticket: " + busTicketResponse + "Plane Ticket: "
                + planeTicketResponse + "Train Ticket: " + trainTicketResponse, HttpStatus.OK);
    }
}



