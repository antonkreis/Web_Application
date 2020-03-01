package controller;

import model.BusTicket;
import model.PlaneTicket;
import model.TrainTicket;
import model.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        Date date = new Date(9, 7, 2020);
        BusTicket bus = new BusTicket(BusTicket.BusType.CITY, "Central", "Anton", 5, date);
        bus.show();
        bus.buy();
        bus.cancel();
        PlaneTicket plane = new PlaneTicket(PlaneTicket.SeatClass.ECONOMY, "MSQ", true, "Anton", 5, date);
        plane.show();
        plane.buy();
        plane.cancel();
        TrainTicket train = new TrainTicket(TrainTicket.SeatType.SEAT,  "Minsk-Passenger",true, "Anton", 5, date);
        train.show();
        train.buy();
        train.cancel();
        System.out.println(train.toString());
        model.addAttribute("name", name);
        return "greeting";
    }
}



