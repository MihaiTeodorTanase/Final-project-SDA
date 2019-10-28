package com.proiectfinal.entities.users;

import org.hibernate.type.descriptor.sql.LobTypeMappings;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.sql.Blob;


@Embeddable
public class Info {

    private String bandName;

    private int noMembers;

    private String city;

    private int noConcerts;

    private String name;

    private String first_name;

    @Lob
    @Column(name="Avatar",columnDefinition = "BLOB")
    private byte[] image;

    public Info() {
    }

    public Info(String bandName, int noMembers, String city, int noConcerts, byte[] avatar) {
        this.bandName = bandName;
        this.noMembers = noMembers;
        this.city = city;
        this.noConcerts = noConcerts;
        this.image = avatar;
    }

    public Info(String name, String first_name,byte[] avatar) {
        this.name = name;
        this.first_name = first_name;
        this.image = avatar;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
