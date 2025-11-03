package kz.bitlab.springboot.springbootdemo.service;

import kz.bitlab.springboot.springbootdemo.entities.Courses;
import kz.bitlab.springboot.springbootdemo.repositories.CoursesRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor

public class CoursesService {

    private CoursesRepository coursesRepository;




    public List<Courses> courses(){
        List<Courses> courses=coursesRepository.findAll();
        return courses;

    }

    public Courses addCourse(Courses course){
        Courses createdCourse=coursesRepository.save(course);
        return createdCourse;
    }

    public boolean deleteCourse(Long id){
        Courses checkCourse=coursesRepository.findById(id).orElse(null);
        if (Objects.isNull(checkCourse)){
            return false;
        }
        coursesRepository.deleteById(id);
        return true;
    }


    public List<Courses> getCourses(){
        List<Courses> courses=coursesRepository.findAll();
        return courses;
    }
}
