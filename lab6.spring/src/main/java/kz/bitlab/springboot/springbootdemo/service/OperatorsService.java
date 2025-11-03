package kz.bitlab.springboot.springbootdemo.service;


import kz.bitlab.springboot.springbootdemo.entities.Courses;
import kz.bitlab.springboot.springbootdemo.entities.Operators;
import kz.bitlab.springboot.springbootdemo.repositories.OperatorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class OperatorsService {

    private OperatorsRepository operatorsRepository;

    public List<Operators> getOperators(){
        List<Operators> operators=operatorsRepository.findAll();
        return operators;
    }

    public Operators getOperator(Long id){
        Operators oper=operatorsRepository.findById(id).orElse(null);
        return oper;
    }
    public Operators addOperator(Operators operator){
        Operators createdOper=operatorsRepository.save(operator);
        return createdOper;
    }
}
