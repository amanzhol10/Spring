package kz.bitlab.springboot.springbootdemo.controllers;
import kz.bitlab.springboot.springbootdemo.entities.ApplicationRequest;
import kz.bitlab.springboot.springbootdemo.entities.Courses;
import kz.bitlab.springboot.springbootdemo.entities.Operators;
import kz.bitlab.springboot.springbootdemo.repositories.ApplicationRequestRepository;
import kz.bitlab.springboot.springbootdemo.repositories.CoursesRepository;
import kz.bitlab.springboot.springbootdemo.repositories.OperatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/app")
public class HomeController {

    @Autowired
    private ApplicationRequestRepository repository;

    @Autowired
    private CoursesRepository repository2;

    @Autowired
    private OperatorsRepository repository3;

    @GetMapping
    public String indexPage(Model model){
        List<ApplicationRequest> app=repository.findAll();
        List<Courses> courses=repository2.findAll();

        model.addAttribute("list",app);
        model.addAttribute("courses",courses);
        return "index";
    }
    @GetMapping(value = "/form")
    public String form(Model model){
        List<Courses> courses=repository2.findAll();
        model.addAttribute("courses",courses);
        return "addRequest";
    }

    @PostMapping(value = "/addRequest")
    public String addRequest(@RequestParam String fullName,@RequestParam Long course,@RequestParam String comment,@RequestParam String phone){
        ApplicationRequest app=new ApplicationRequest();
        app.setUserName(fullName);
        app.setCommentary(comment);
        app.setPhone(phone);
        app.setHandled(false);

        Courses selectedCourse = repository2.findById(course).orElse(null);
        app.setCourse(selectedCourse);
        repository.save(app);
        return "redirect:/app";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        ApplicationRequest app = repository.findById(id).orElse(null);
        model.addAttribute("li", app);

        List<Operators> oper=repository3.findAll();
        model.addAttribute("oper",oper);

        List<Courses> courses = repository2.findAll();
        model.addAttribute("courses", courses);

        return "details";
    }

    @PostMapping("/edit/{id}")
    public String editRequest(@PathVariable Long id, @RequestParam String fullName, @RequestParam Long course, @RequestParam String phone, @RequestParam String comment) {
        ApplicationRequest app = repository.findById(id).orElse(null);
        if (app != null) {
            app.setUserName(fullName);
            app.setCommentary(comment);
            app.setPhone(phone);
            Courses selectedCourse = repository2.findById(course).orElse(null);
            app.setCourse(selectedCourse);

            repository.save(app);
        }
        return "redirect:/app";
    }

    @PostMapping(value = "/process/{id}")
    public String processRequest(@PathVariable Long id,@RequestParam(name="operId") List<Long> ops){
        ApplicationRequest app=repository.findById(id).orElse(null);
        if (app!=null && ops!=null){
            List<Operators> oper=repository3.findAllById(ops);
            app.setOperators(oper);
            app.setHandled(true);
            repository.save(app);
            return "redirect:/app";

        }
        return "redirect:/";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteRequest(@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/";

    }

    @GetMapping("/processed")
    public String processed(Model model){
        List<ApplicationRequest> apps=repository.findAll();
        model.addAttribute("list",apps);
        return "processed";

    }

    @GetMapping("/unprocessed")
    public String unProcessed(Model model){
        List<ApplicationRequest> apps=repository.findAll();
        model.addAttribute("list",apps);
        return "unprocessed";

    }

    @PostMapping("/removeOper/{appId}/{opId}")
    public String removeOper(@PathVariable Long appId,@PathVariable Long opId){
        ApplicationRequest app=repository.findById(appId).orElse(null);
        Operators oper=repository3.findById(opId).orElse(null);
        if(app!=null && oper!=null){
            app.getOperators().remove(oper);
            repository.save(app);

        }
        return "redirect:/app/details/"+appId;
    }



}
