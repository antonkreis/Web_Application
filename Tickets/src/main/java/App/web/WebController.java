package App.web;

import App.database.*;
import App.model.BusTicket;
import App.model.PlaneTicket;
import App.model.TrainTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class WebController {
    //@Autowired
   // private JPARepository repository;
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

    @GetMapping("/all")
    public synchronized ResponseEntity<Object> showAllTickets() {
        return new ResponseEntity<>("Bus tickets: " + ticketsCollection.getBusTicketsList() + " \nPlane tickets: " +  ticketsCollection.getPlaneTicketsList() + " \nTrain tickets: " + ticketsCollection.getTrainTicketsList(), HttpStatus.OK);
    }

    @GetMapping("/all/bus")
    public synchronized ResponseEntity<Object> showAllBusTickets() {
        return new ResponseEntity<>(ticketsCollection.getBusTicketsList(), HttpStatus.OK);
    }

    @GetMapping("/all/plane")
    public synchronized ResponseEntity<Object> showAllPlaneTickets() {
        return new ResponseEntity<>(ticketsCollection.getPlaneTicketsList(), HttpStatus.OK);
    }

    @GetMapping("/all/train")
    public synchronized ResponseEntity<Object> showAllTrainTickets() {
        return new ResponseEntity<>(ticketsCollection.getTrainTicketsList(), HttpStatus.OK);
    }

    @GetMapping("/bus/{id}")
    public synchronized ResponseEntity<Object> showBusTicket(@PathVariable String id) {
        if(!ticketsCollection.containsBusTicket(id)) {
            throw new NotFoundException(id);
        }
        return new ResponseEntity<>(ticketsCollection.getBusTicket(id).toString(), HttpStatus.OK);
    }

    @GetMapping("/plane/{id}")
    public synchronized ResponseEntity<Object> showPlaneTicket(@PathVariable String id) {
        if(!ticketsCollection.containsPlaneTicket(id)) {
            throw new NotFoundException(id);
        }
        return new ResponseEntity<>(ticketsCollection.getPlaneTicket(id).toString(), HttpStatus.OK);
    }

//    @GetMapping("/all/bla")
//    public @ResponseBody Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return repository.findAll();
//    }

    @GetMapping("/train/{id}")
    public synchronized ResponseEntity<Object> showTrainTicket(@PathVariable String id) {
        if(!ticketsCollection.containsTrainTicket(id)) {
            throw new NotFoundException(id);
        }
        return new ResponseEntity<>(ticketsCollection.getTrainTicket(id).toString(), HttpStatus.OK);
    }

//    @PostMapping("/create/bus/{id}/{busType}/{busStopName}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
//    public synchronized ResponseEntity<Object> createBusTicket(@PathVariable String id, @PathVariable BusTicket.BusType busType, @PathVariable String busStopName, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
//        if(!ticketsCollection.containsBusTicket(id)) {
//            try {
//                BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
//                ticketsCollection.addBusTicket(id, newBusTicket);
//            }
//            catch(Exception e){
//                throw new DateException();
//            }
//            return new ResponseEntity<>("Created.", HttpStatus.OK);
//        }
//        throw new AlreadyExistException();
//    }

    @PostMapping("/create/bus/{id}/{busType}/{busStopName}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> createBusTicket(@PathVariable String id, @PathVariable BusTicket.BusType busType, @PathVariable String busStopName, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
       // User user = new User();
      // user.setName("a");
       // user.setId(1);
     //   user.setEmail("a");
        if(!ticketsCollection.containsBusTicket(id)) {
            try {
                BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
                ticketsCollection.addBusTicket(id, newBusTicket);
               // repository.save(user);
               busTicketRepository.save(newBusTicket);
            }
            catch(Exception e){
                throw new DateException();
            }
            return new ResponseEntity<>("Created.", HttpStatus.OK);
        }
        throw new AlreadyExistException();
    }




    @PostMapping("/create/plane/{id}/{seatClass}/{airportName}/{luggageIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> createPlaneTicket(@PathVariable String id, @PathVariable PlaneTicket.SeatClass seatClass, @PathVariable String airportName, @PathVariable boolean luggageIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!ticketsCollection.containsPlaneTicket(id)) {
            try {
                PlaneTicket newPlaneTicket = new PlaneTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
                ticketsCollection.addPlaneTicket(id, newPlaneTicket);
                planeTicketRepository.save(newPlaneTicket);
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
    public synchronized ResponseEntity<Object> createTrainTicket(@PathVariable String id, @PathVariable TrainTicket.SeatType seatType, @PathVariable String railwayStationName, @PathVariable boolean mealsIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        if(!ticketsCollection.containsTrainTicket(id)) {
            try {
                TrainTicket newTrainTicket = new TrainTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
                ticketsCollection.addTrainTicket(id, newTrainTicket);
                trainTicketRepository.save(newTrainTicket);
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
    public synchronized ResponseEntity<Object> updateBusTicket(@PathVariable String id, @PathVariable BusTicket.BusType busType, @PathVariable String busStopName, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Created.";
        if(ticketsCollection.containsBusTicket(id)) {
            ticketsCollection.removeBusTicket(id);
            response= "Updated.";
        }
        try {
            BusTicket newBusTicket = new BusTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
            ticketsCollection.addBusTicket(id, newBusTicket);
        }
        catch(Exception e){
            throw new DateException();
           // System.out.println(e.toString());
          //  return "Time error.";
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PutMapping("/update/plane/{id}/{seatClass}/{airportName}/{luggageIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> updatePlaneTicket(@PathVariable String id, @PathVariable PlaneTicket.SeatClass seatClass, @PathVariable String airportName, @PathVariable boolean luggageIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Created.";
        if(ticketsCollection.containsPlaneTicket(id)) {
            ticketsCollection.removePlaneTicket(id);
            response = "Updated.";
        }
        try {
            PlaneTicket newPlaneTicket = new PlaneTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
            ticketsCollection.addPlaneTicket(id, newPlaneTicket);
        }
        catch(Exception e){
            throw new DateException();
           // System.out.println(e.toString());
          //  return "Time error.";
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/update/train/{id}/{seatType}/{railwayStationName}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> updateTrainTicket(@PathVariable String id, @PathVariable TrainTicket.SeatType seatType, @PathVariable String railwayStationName, @PathVariable boolean mealsIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        String response = "Created.";
        if(ticketsCollection.containsTrainTicket(id)) {
            ticketsCollection.removeTrainTicket(id);
            response = "Updated.";
        }
        try {
            TrainTicket newTrainTicket = new TrainTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
            ticketsCollection.addTrainTicket(id, newTrainTicket);
        }
        catch(Exception e){
            throw new DateException();
            //System.out.println(e.toString());
           // return "Time error.";
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/bus/{id}")
    public synchronized ResponseEntity<Object> deleteBusTicket(@PathVariable String id){
        if(!ticketsCollection.containsBusTicket(id)){
            throw new NotFoundException(id);
        }
        ticketsCollection.removeBusTicket(id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }



    @DeleteMapping("/delete/plane/{id}")
    public synchronized ResponseEntity<Object> deletePlaneTicket(@PathVariable String id){
        if(!ticketsCollection.containsPlaneTicket(id)){
            throw new NotFoundException(id);
        }
        ticketsCollection.removePlaneTicket(id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/train/{id}")
    public synchronized ResponseEntity<Object> deleteTrainTicket(@PathVariable String id){
        if(!ticketsCollection.containsTrainTicket(id)){
            throw new NotFoundException(id);
        }
        ticketsCollection.removeTrainTicket(id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

    @PutMapping("/setVIPInfo/bus/{id}/{busType}/{busStopName}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> setVIPBusTicketInfo(@PathVariable String id, @PathVariable BusTicket.BusType busType, @PathVariable String busStopName, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        try {
            ticketsCollection.setBusVIPTicket(id, busType, busStopName, clientName, amountOfTickets, LocalDate.of(year, month, day));
        }
        catch(Exception e){
            throw new DateException();
            // System.out.println(e.toString());
            //  return "Time error.";
        }
        return new ResponseEntity<>("Information added", HttpStatus.OK);
    }

    @PutMapping("/setVIPInfo/plane/{id}/{seatClass}/{airportName}/{luggageIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> setVIPPlaneTicketInfo(@PathVariable String id, @PathVariable PlaneTicket.SeatClass seatClass, @PathVariable String airportName, @PathVariable boolean luggageIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        try {
            ticketsCollection.setPlaneVIPTicket(id, seatClass, airportName, luggageIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
        }
        catch(Exception e){
            throw new DateException();
            // System.out.println(e.toString());
            //  return "Time error.";
        }
        return new ResponseEntity<>("Information added", HttpStatus.OK);
    }

    @PutMapping("/setVIPInfo/train/{id}/{seatType}/{railwayStationName}/{mealsIncluded}/{clientName}/{amountOfTickets}/{day}/{month}/{year}")
    public synchronized ResponseEntity<Object> setVIPTrainTicketInfo(@PathVariable String id, @PathVariable TrainTicket.SeatType seatType, @PathVariable String railwayStationName, @PathVariable boolean mealsIncluded, @PathVariable String clientName, @PathVariable Integer amountOfTickets, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
        try {
            ticketsCollection.setTrainVIPTicket(id, seatType, railwayStationName, mealsIncluded, clientName, amountOfTickets, LocalDate.of(year, month, day));
        }
        catch(Exception e){
            throw new DateException();
            // System.out.println(e.toString());
            //  return "Time error.";
        }
        return new ResponseEntity<>("Information added", HttpStatus.OK);
    }
}



