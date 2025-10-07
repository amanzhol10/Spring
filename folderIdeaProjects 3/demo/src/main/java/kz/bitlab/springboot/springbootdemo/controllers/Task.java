package kz.bitlab.springboot.springbootdemo.controllers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private String name;
    private String deadlineDate;
    private boolean isCompleted;

    public Task(String name,String deadlineDate,boolean isCompleted){
        this.name=name;
        this.deadlineDate=deadlineDate;
        this.isCompleted=isCompleted;
    }


}
