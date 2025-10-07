package kz.bitlab.springboot.springbootdemo.controllers;

import kz.bitlab.springboot.springbootdemo.controllers.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentList {

    private final List<Student> students = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Student> getAll() {
        return students;
    }

    public void addStudent(String name, String surname, int exam) {
        Long id = counter.incrementAndGet();
        students.add(new Student(id, name, surname, exam));
    }
}
