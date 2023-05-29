package com.example.repit.entities;

import com.example.repit.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "authentication")
public class Authentication {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_user;

    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;


    public Authentication(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Authentication() {

    }
    public String getPassword() {
        return password;
    }
    public String get_login() {
        return login;
    }
    public int id_user() {
        return id_user;
    }
    public Role getRole() {
       return role;
   }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(Role role) {
       this.role = role;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }



    @OneToOne(mappedBy = "authentication", cascade=CascadeType.ALL)
    private Tutor tutor;

    public Tutor getTutor() {
        return tutor;
    }

    public Student getStudent() {
        return student;
    }

    @OneToOne(mappedBy = "authentication", cascade=CascadeType.ALL)
    private Student student;


    @Override
    public String toString() {
        return "Authentication{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", id_user=" + id_user +
                '}';
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
