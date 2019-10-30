package com.proiectfinal.entities.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("eventService")
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventModel add(EventModel eventModel){
        return eventRepository.saveAndFlush(eventModel);
    }

    public List<EventModel> getAll(){
        return eventRepository.findAll();
    }

    public Optional<EventModel> getById(Long id){
        Optional<EventModel> existing = eventRepository.findById(id);
        if(existing.isPresent()){
            return existing;
        }
        else {
            return Optional.empty();
        }
    }
}
