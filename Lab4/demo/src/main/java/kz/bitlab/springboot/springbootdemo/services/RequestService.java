package kz.bitlab.springboot.springbootdemo.services;

import kz.bitlab.springboot.springbootdemo.controllers.ApplicationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class RequestService {
    private final List<ApplicationRequest> list = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public void addRequest(String fullName, String course, String number, String comment) {
        Long id = counter.incrementAndGet();
        list.add(new ApplicationRequest(id, fullName, course, comment, number, false));
    }

    public List<ApplicationRequest> getAll() {
        return list;
    }

    public ApplicationRequest getByid(Long id) {
        return list.stream()
                .filter(li -> li.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updRequest(Long id, String fullName, String course, String number, String comment) {
        ApplicationRequest app = getByid(id);
        if (app != null) {
            app.setUserName(fullName);
            app.setCourseName(course);
            app.setPhone(number);
            app.setCommentary(comment);
        }
    }

    public void process(Long id){
        ApplicationRequest app=getByid(id);
        if(app!=null){
            app.setHandled(true);
        }
    }

    public void delete(Long id){
        list.removeIf(li->li.getId().equals(id));
    }
}

