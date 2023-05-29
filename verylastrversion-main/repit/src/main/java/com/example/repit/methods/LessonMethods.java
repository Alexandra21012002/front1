package com.example.repit.methods;

import com.example.repit.entities.Advertisement;
import com.example.repit.entities.Lesson;
import com.example.repit.repositories.AdvertisementRepository;
import com.example.repit.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class LessonMethods {

    private final LessonRepository lessonRepository;
    @Autowired
    public LessonMethods(LessonRepository lessonRepository){
        this.lessonRepository = lessonRepository;
    }

    @Transactional(readOnly = true)
    public List<Lesson> getAll(){
        return lessonRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Lesson getByID(int id){
        Optional<Lesson> lesson = lessonRepository.findById(id);
        return lesson.orElse(null);
    }
    @Transactional
    public void save(Lesson lesson){
        lessonRepository.save(lesson);
    }

    @Transactional
    public void update(int id, Lesson newLesson){
        Lesson old = getByID(id);

        old.setAccept(newLesson.getAccept());
        old.setStartLesson(newLesson.getStartLesson());
        old.setComment(newLesson.getComment());
        old.setMarkLesson(newLesson.getMarkLesson());

        lessonRepository.save(old);
    }

    @Transactional
    public void delete(int id){
        lessonRepository.deleteById(id);
    }



}
