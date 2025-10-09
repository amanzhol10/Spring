package kz.bitlab.springboot.springbootdemo.controllers;
import kz.bitlab.springboot.springbootdemo.services.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    private final RequestService service;

    public HomeController(RequestService service){
        this.service=service;
    }

    @GetMapping(value = "/")
    public String indexPage(Model model){
        model.addAttribute("list",service.getAll());
        return "index";
    }
    @GetMapping(value = "/form")
    public String form(){
        return "addRequest";
    }

    @PostMapping(value = "/addRequest")
    public String addRequest(@RequestParam String fullName,@RequestParam String course,@RequestParam String number,@RequestParam String comment){
        service.addRequest(fullName,course,number,comment);
        return "redirect:/";
    }

    @GetMapping(value="/details/{id}")
    public String edit(@PathVariable Long id,Model model){
        model.addAttribute("li",service.getByid(id));
        return "details";
    }

    @PostMapping(value = "/edit/{id}")
    public String editRequest(@PathVariable Long id,@RequestParam String fullName,@RequestParam String course,@RequestParam String number,@RequestParam String comment){
        service.updRequest(id,fullName,course,number,comment);
        return "redirect:/";
    }

    @PostMapping(value = "/process/{id}")
    public String processRequest(@PathVariable Long id){
        service.process(id);
        return "redirect:/details/"+id;
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteRequest(@PathVariable Long id){
        service.delete(id);
        return "redirect:/";

    }

    @GetMapping("/processed")
    public String processed(Model model){
        model.addAttribute("list",service.getAll());
        return "processed";

    }

    @GetMapping("/unprocessed")
    public String unProcessed(Model model){
        model.addAttribute("list",service.getAll());
        return "unprocessed";

    }




}
