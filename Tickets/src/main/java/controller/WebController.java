package controller;

import model.BusTicket;
import model.PlaneTicket;
import model.TrainTicket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        BusTicket bus = new BusTicket();
        bus.show();
        bus.buy();
        bus.cancel();
        PlaneTicket plane = new PlaneTicket();
        plane.show();
        plane.buy();
        plane.cancel();
        TrainTicket train = new TrainTicket();
        train.show();
        train.buy();
        train.cancel();
        model.addAttribute("name", name);
        return "greeting";
    }
}



