package com.proiectfinal.entities.users;

import org.springframework.stereotype.Controller;

import javax.persistence.*;


@Entity
@Table(name = "info")
@Embeddable
public class Info {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bandName;

    private int noMembers;

    private String city;

    private int noConcerts;

    public int getNoConcerts() {
        return noConcerts;
    }

    public void setNoConcerts(int noConcerts) {
        this.noConcerts = noConcerts;
    }
//spectator

    private String name;

    private String first_name;

//    @OneToOne(mappedBy = "info",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//
//    )
    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
