package com.example.repit.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tutor")
public class Tutor {
    @Id
    @Column(name = "id_tutor")
    int id_tutor;
    @Column(name = "fname")
    String FName;
    @Column(name = "mobile")
    String mobile;
    @Column(name = "city")
    String city;
    @Column(name = "experience")
    int experience;
    @Column(name = "workplace")
    String workPlace;

    public Tutor(int id_tutor, String FName, String mobile, String city, Integer experience, String workPlace) {
        this.id_tutor = id_tutor;
        this.FName = FName;
        this.mobile = mobile;
        this.city = city;
        this.experience = experience;
        this.workPlace = workPlace;
    }

    public Tutor() {
    }

    public int getId_tutor() {
        return id_tutor;
    }

    public String getFName() {
        return FName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCity() {
        return city;
    }

    public int getExperience() {
        return experience;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_tutor", referencedColumnName = "id_user")
    private Authentication authentication;

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Advertisement> advertisements = new ArrayList<>();

    public Authentication getAuthentication() {
        return authentication;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id_tutor=" + id_tutor +
                ", FName='" + FName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", city='" + city + '\'' +
                ", experience=" + experience +
                ", workPlace='" + workPlace + '\'' +
                '}';
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
