package kz.bitlab.springboot.springbootdemo.service;

import kz.bitlab.springboot.springbootdemo.controllers.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Task> getAll() {
        return tasks;
    }

    public void addTask(String name, String deadlineDate, boolean isCompleted) {
        Long id = counter.incrementAndGet();
        tasks.add(new Task(id, name, deadlineDate, isCompleted));
    }

    public Task getById(Long id){
        return tasks.stream()
                .filter(t->t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public void updateTask(Long id,String name,String deadlineDate,boolean isCompleted){
        Task task=getById(id);
        if(task!=null){
            task.setName(name);
            task.setDeadlineDate(deadlineDate);
            task.setCompleted(isCompleted);
        }
    }




    public void deleteTask(Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
    }
}


