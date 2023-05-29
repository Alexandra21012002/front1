package com.example.repit.controllers;

import com.example.repit.entities.Advertisement;
import com.example.repit.entities.Lesson;
import com.example.repit.entities.Student;
import com.example.repit.methods.AdvertisementMethods;
import com.example.repit.methods.LessonMethods;
import com.example.repit.methods.StudentMethods;
import com.example.repit.security.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class StudProfileController {
    @Autowired
    StudentMethods studentMethods;

    @Autowired
    LessonMethods lessonMethods;

    @Autowired
    AdvertisementMethods advertisementMethods;

    Student student;

    @GetMapping("/studProfile")
    public ModelAndView StudProfile() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthDetails authDetails = (AuthDetails) auth.getPrincipal();
        com.example.repit.entities.Authentication stud = authDetails.getAuthentication();
        student = stud.getStudent();


        ModelAndView modelAndView = new ModelAndView("/studProfile");

        modelAndView.addObject("fName", student.getfName());
        modelAndView.addObject("yearsOld", student.getYearsOld());
        modelAndView.addObject("mobile", student.getMobile());
        modelAndView.addObject("city", student.getCity());

//        model.addAttribute("fName", student.getfName());
//        model.addAttribute("yearsOld", student.getYearsOld());
//        model.addAttribute("mobile", student.getMobile());
//        model.addAttribute("city", student.getCity());


        List<Object[]> ConfirmedLessonList = studentMethods.ConfirmedLesson(student.getId_student());
//        model.addAttribute("ConfirmedLessonList", ConfirmedLessonList);
        modelAndView.addObject("ConfirmedLessonList", ConfirmedLessonList);

        List<Object[]> CompletedLessonList = studentMethods.CompletedLesson(student.getId_student());
//        model.addAttribute("CompletedLessonList", CompletedLessonList);
        modelAndView.addObject("CompletedLessonList", CompletedLessonList);


//        model.addAttribute("txt", "student.getfName()");


//        return "studProfile";
        return modelAndView;
    }

    @PostMapping ("/studProfile")
    @ResponseBody
    public List<Object[]> acctp(@RequestParam Integer mark, @RequestParam String comment){
//        ModelAndView modelAndView = new ModelAndView("/acceptfom");
        Advertisement advertisement = advertisementMethods.getByID(1);
        Student student1 = studentMethods.getByID(1);

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(1212121212121L);

        Lesson lesson = new Lesson("yes",   new Date(), date, mark, comment);
        lesson.setStudent(student1);
        lesson.setAdvertisement(advertisement);
        lesson.setIdLesson(9);

        lessonMethods.save(lesson);
        List<Object[]> CompletedLesson = studentMethods.CompletedLesson(student.getId_student());

        return CompletedLesson;
    }

//    @GetMapping("/acceptfom")
//    public ModelAndView advt(){
//
//        ModelAndView modelAndView = new ModelAndView("/acceptfom");
//
//        List<Object[]> ConfirmedLessonList = studentMethods.ConfirmedLesson(student.getId_student());
////        model.addAttribute("ConfirmedLessonList", ConfirmedLessonList);
//        modelAndView.addObject("ConfirmedLessonList", ConfirmedLessonList);
//
//        return modelAndView;
//    }


}
