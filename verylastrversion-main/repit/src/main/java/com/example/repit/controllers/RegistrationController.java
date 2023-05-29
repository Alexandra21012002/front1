package com.example.repit.controllers;

import com.example.repit.entities.Authentication;
import com.example.repit.entities.Student;
import com.example.repit.entities.Tutor;
import com.example.repit.enums.Role;
import com.example.repit.methods.AuthenticationMethods;
import com.example.repit.methods.StudentMethods;
import com.example.repit.methods.TutorMethods;
import com.example.repit.repositories.AuthenticationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;

@Controller
public class RegistrationController {
    @Autowired
    private AuthenticationMethods authenticationMethods;

    @Autowired
    private StudentMethods studentMethods;

    @Autowired
    private TutorMethods tutorMethods;

    private Authentication authentication;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Authentication authentication, @RequestParam("radioName")Role role){
//        UserDetails UserFromDb = authenticationMethods.loadUserByUsername(authentication.get_login());
//        Authentication userFromDb = OptUserFromDb.get();
//        if(!UserFromDb.equals(null)){
//            model.addAttribute("error", "Can't find username. Check login/password");
//            throw new RuntimeException();
//        }
//        authentication.setRole("student");

//        Enumeration<String> enumeration = request.getParameterNames();
//        enumeration;
//        System.out.println("asd");


          this.authentication = authentication;
          authentication.setRole(role);
          authenticationMethods.save(authentication);

          if(role == Role.ROLE_STUDENT)return "redirect:/regStudent";

          if(role == Role.ROLE_TUTOR)return "redirect:/regTutor";

          return " ";
    }

    @GetMapping("/regStudent")
    public String UserStudent(){
        return "regStudent";
    }

    @PostMapping("/regStudent")
    public String addUserStudent(Student student, Model model){
        student.setId_student(authentication.id_user());
        studentMethods.save(student);

        return "redirect:/login";
    }

    @GetMapping("/regTutor")
    public String TutorStudent(){
        return "regTutor";
    }

    @PostMapping("/regTutor")
    public String addUserTutor(Tutor tutor, Model model){
        tutor.setId_tutor(authentication.id_user());
        tutorMethods.save(tutor);

        return "redirect:/login";
    }

}
