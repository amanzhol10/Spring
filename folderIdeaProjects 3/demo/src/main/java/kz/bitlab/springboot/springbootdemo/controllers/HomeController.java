package kz.bitlab.springboot.springbootdemo.controllers;
import kz.bitlab.springboot.springbootdemo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/tasks")
public class HomeController {
    private final TaskService service;

    public HomeController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("tasks",service.getAll());
        return "main";
    }



    @PostMapping
    public String addTask(@RequestParam String name,
                          @RequestParam String deadlineDate,
                          @RequestParam boolean isCompleted) {
        service.addTask(name, deadlineDate, isCompleted);
        return "redirect:/tasks";
    }



    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id,Model model){
        model.addAttribute("task",service.getById(id));
        return "details";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable Long id,@RequestParam String name,@RequestParam String deadlineDate,@RequestParam boolean isCompleted){
        service.updateTask(id,name,deadlineDate,isCompleted);
        return "redirect:/tasks";
    }



    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return "redirect:/tasks";
    }
}
