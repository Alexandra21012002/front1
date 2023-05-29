package com.example.repit.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlesson")
    int idLesson;
    @Column(name = "accept")
    String accept;
    @Column(name = "endlesson")
    Date endLesson;
    @Column(name = "startlesson")
    Date startLesson;
    @Column(name = "marklesson")
    Integer  MarkLesson;
    @Column(name = "comment")
    String Comment;

    public Lesson(String accept, Date endLesson, Date startLesson, Integer markLesson, String comment) {
        this.accept = accept;
        this.endLesson = endLesson;
        this.startLesson = startLesson;
        this.MarkLesson = markLesson;
        this.Comment = comment;
    }

    public Lesson() {
    }

    public int getIdLesson() {
        return idLesson;
    }

    public String getAccept() {
        return accept;
    }

    public Date getEndLesson() {
        return endLesson;
    }

    public Date getStartLesson() {
        return this.startLesson;
    }

    public int getMarkLesson() {
        return MarkLesson;
    }

    public String getComment() {
        return Comment;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public void setEndLesson(Date endLesson) {
        this.endLesson = endLesson;
    }

    public void setStartLesson(Date startLesson) {
        this.startLesson = startLesson;
    }

    public void setMarkLesson(int markLesson) {
        MarkLesson = markLesson;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_student")
    private Student student;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_advertisement")
    private Advertisement advertisement;

    public Advertisement getAdvertisement() {
        return advertisement;
    }
    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "idLesson=" + idLesson +
                ", accept='" + accept + '\'' +
                ", endLesson=" + endLesson +
                ", startLesson=" + startLesson +
                ", MarkLesson=" + MarkLesson +
                ", Comment='" + Comment + '\'' +
                '}';
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
