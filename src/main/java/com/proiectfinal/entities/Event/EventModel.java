package com.proiectfinal.entities.Event;

import com.proiectfinal.entities.users.UserModel;
import org.hibernate.mapping.List;

import javax.persistence.*;
import java.util.Date;


@Entity
public class EventModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 70)
    private String name;

    private String place;

    private Date date;

    private String city;

    private String country;

    private int noTickets;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserModel userModel;

    private List registeredSpectators;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNoTickets() {
        return noTickets;
    }

    public void setNoTickets(int noTickets) {
        this.noTickets = noTickets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List getRegisteredSpectators() {
        return registeredSpectators;
    }

    public void setRegisteredSpectators(List registeredSpectators) {
        this.registeredSpectators = registeredSpectators;
    }
}
