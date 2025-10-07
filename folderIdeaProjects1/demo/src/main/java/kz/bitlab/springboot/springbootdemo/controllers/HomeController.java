package kz.bitlab.springboot.springbootdemo.controllers;

import kz.bitlab.springboot.springbootdemo.controllers.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class HomeController {

    private final  StudentList service;


    public HomeController(StudentList service) {
        this.service = service;

    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", service.getAll());
        return "list";
    }




    @GetMapping("/add")
    public String showAddForm() {
        return "index";
    }

    @PostMapping
    public String addStudent(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam int exam) {
        service.addStudent(name, surname, exam);
        return "redirect:/students";
    }


}
