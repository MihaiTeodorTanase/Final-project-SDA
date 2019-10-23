package com.proiectfinal.entities.users;

import javax.persistence.Embeddable;


@Embeddable
public class Info {

    private String bandName;

    private int noMembers;

    private String city;

    private int noConcerts;

    private String name;

    private String first_name;

    public Info() {
    }

    public Info(String bandName, int noMembers, String city, int noConcerts) {
        this.bandName = bandName;
        this.noMembers = noMembers;
        this.city = city;
        this.noConcerts = noConcerts;
    }

    public Info(String name, String first_name) {
        this.name = name;
        this.first_name = first_name;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getNoMembers() {
        return noMembers;
    }

    public void setNoMembers(int noMembers) {
        this.noMembers = noMembers;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNoConcerts() {
        return noConcerts;
    }

    public void setNoConcerts(int noConcerts) {
        this.noConcerts = noConcerts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
