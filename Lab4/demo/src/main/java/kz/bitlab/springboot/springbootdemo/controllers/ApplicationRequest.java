package kz.bitlab.springboot.springbootdemo.controllers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {
    Long id;
    String userName;
    String courseName;
    String commentary;
    String phone;
    boolean handled;
}
