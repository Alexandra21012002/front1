package com.example.repit.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
@Embeddable
public class Student {
    @Id
    @Column(name = "id_student")
    int id_student;
    @Column(name = "fname")
    String fName;
    @Column(name = "yearsold")
    int yearsOld;
    @Column(name = "city")
    String city;
    @Column(name = "mobile")
    String mobile;

    public Student(int id_student, String fName, int yearsOld, String city, String mobile) {
        this.id_student = id_student;
        this.fName = fName;
        this.yearsOld = yearsOld;
        this.city = city;
        this.mobile = mobile;
    }

    public Student() {

    }


    public int getId_student() {
        return id_student;
    }

    public String getfName() {
        return fName;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public String getCity() {
        return city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_student",referencedColumnName = "id_user")
    private Authentication authentication;

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @OneToMany(mappedBy = "student", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Lesson> lessons = new ArrayList<>();

    public Authentication getAuthentication() {
        return authentication;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id_student=" + id_student +
                ", fName='" + fName + '\'' +
                ", yearsOld=" + yearsOld +
                ", city='" + city + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
