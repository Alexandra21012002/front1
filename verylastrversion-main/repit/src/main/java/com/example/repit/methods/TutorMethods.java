package com.example.repit.methods;

import com.example.repit.entities.Student;
import com.example.repit.entities.Tutor;
import com.example.repit.repositories.StudentRepository;
import com.example.repit.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TutorMethods {
    private final TutorRepository tutorRepository;
    @Autowired
    public TutorMethods(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Transactional(readOnly = true)
    public List<Tutor> getAll(){
        return tutorRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Tutor getByID(int id){
        Optional<Tutor> tutor = tutorRepository.findById(id);
        return tutor.orElse(null);
    }
    @Transactional
    public void save(Tutor tutor){
        tutorRepository.save(tutor);
    }

    @Transactional
    public void update(int id, Tutor NewTutor){
        Tutor old = getByID(id);

        old.setCity(NewTutor.getCity());
        old.setWorkPlace(NewTutor.getWorkPlace());
        old.setFName(NewTutor.getFName());
        old.setMobile(NewTutor.getMobile());

        tutorRepository.save(old);
    }

    @Transactional
    public void delete(int id){
        tutorRepository.deleteById(id);
    }

    @Transactional
    public  List<Object[]> CompletedLessonForTutor(int id){
        return tutorRepository.CompletedLessonForTutor(id); }
}
