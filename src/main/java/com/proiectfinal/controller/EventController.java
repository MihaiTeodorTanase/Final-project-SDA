package com.proiectfinal.controller;

import com.proiectfinal.entities.Event.EventModel;
import com.proiectfinal.entities.Event.EventService;
import com.proiectfinal.entities.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {
    private EventService eventService;
    private UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/events")
    public String events(Model model) {
        List<EventModel> allEvents = eventService.getAll();

        List<EventModel> goodEvents = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("NOW: "+now.toString());
        EventModel currentEvent;
        for (int i = 0; i < allEvents.size(); i++) {
            currentEvent=allEvents.get(i);
            System.out.println(currentEvent.getDate().toString());
            if (    currentEvent.getCity() !=null && !currentEvent.getCity().isEmpty() &&
                    currentEvent.getCountry() !=null && !currentEvent.getCountry().isEmpty() &&
                    (currentEvent.getDate() !=null &&
                            currentEvent.getDate().isAfter(now)) &&
                    currentEvent.getImage() !=null &&
                    currentEvent.getName() !=null && !currentEvent.getName().isEmpty() &&
                    currentEvent.getNoTickets() >=0 &&
                    currentEvent.getPlace() !=null &&!currentEvent.getPlace().isEmpty() ){
                goodEvents.add(currentEvent);
            }
        }
        model.addAttribute("goodEvents" , goodEvents);
        return "events";
    }

    @GetMapping("/events/createEvent")
    public String createEvent (Model model){
        model.addAttribute("event",new EventModel());
        return "createEvent";
    }


    @PostMapping("/events/createEvent")
    public String addUser(@ModelAttribute("event")@Valid EventModel eventModel, BindingResult result, @RequestParam("eventPicture") MultipartFile file ) {
//        System.out.println(date);
        if (result.hasErrors()) {
            return "createEvent";
        }
        try {
            byte[] photo = file.getBytes();
            eventModel.setImage(photo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        eventModel.setUserModel(userService.getByUsername(auth.getName()).get());
        eventService.add(eventModel);
        return "events";
    }
}
