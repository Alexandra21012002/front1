package com.example.repit.repositories;

import com.example.repit.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select lesson.marklesson, lesson.comment, a.subject from lesson join advertisement a on a.id_advertisement = lesson.id_advertisement\n" +
            "where lesson.id_student in(select id_student from student where student.id_student = ?)", nativeQuery = true)
    List<Object[]> findSubByIdLesson(int id);

    @Query(value = "select tutor.fname, a.subject, date(l.startlesson) as date from tutor\n" +
            "            join advertisement a on tutor.id_tutor = a.tutor_id_tutor\n" +
            "            join lesson l on a.id_advertisement = l.id_advertisement\n" +
            "            join student s on s.id_student = l.id_student\n" +
            "            where now() < l.startlesson and l.accept = 'yes' and s.id_student = ?", nativeQuery = true)
    List<Object[]> ConfirmedLesson(int id);

    @Query(value = "select tutor.fname, a.subject, date(l.startlesson) as date, l.marklesson, l.comment from tutor\n" +
            "    join advertisement a on tutor.id_tutor = a.tutor_id_tutor\n" +
            "    join lesson l on a.id_advertisement = l.id_advertisement\n" +
            "    join student s on s.id_student = l.id_student\n" +
            "    where now() > l.startlesson and l.marklesson is not null and l.accept = 'yes' and s.id_student = ? ", nativeQuery = true)
    List<Object[]> CompletedLesson(int id);

    @Query(value = "select tutor.fname, a.subject, tutor.mobile, tutor.city, tutor.experience, tutor.workplace, a.price from tutor\n" +
            "            join advertisement a on tutor.id_tutor = a.tutor_id_tutor\n" +
            "            where a.id_advertisement not in(select id_advertisement from lesson)",nativeQuery = true)
    List<Object[]> AdsForStudent();


    @Query(value = "select tutor.fname, a.subject, l.startlesson, l.idlesson from tutor\n" +
            "            join advertisement a on tutor.id_tutor = a.tutor_id_tutor\n" +
            "            join lesson l on a.id_advertisement = l.id_advertisement\n" +
            "            join student s on s.id_student = l.id_student\n" +
            "            where l.comment is null and l.marklesson is null and now() > l.startlesson and s.id_student in(select id_student from student where id_student = ?)", nativeQuery = true)
    List<Object[]> LessonNotMarkNotComment(int id);

}
