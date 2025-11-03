package kz.bitlab.springboot.springbootdemo.service;

import kz.bitlab.springboot.springbootdemo.entities.ApplicationRequest;
import kz.bitlab.springboot.springbootdemo.entities.Courses;
import kz.bitlab.springboot.springbootdemo.entities.Operators;
import kz.bitlab.springboot.springbootdemo.repositories.AppRepository;
import kz.bitlab.springboot.springbootdemo.repositories.CoursesRepository;
import kz.bitlab.springboot.springbootdemo.repositories.OperatorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AppService {
    private AppRepository appRepository;

    public List<ApplicationRequest> getList(){
        List<ApplicationRequest> app=appRepository.findAll();
        return app;
    }

    public ApplicationRequest addApp(ApplicationRequest app){
        ApplicationRequest createdApp=appRepository.save(app);
        return createdApp;
    }

    public ApplicationRequest getApp(Long id){
        ApplicationRequest app=appRepository.findById(id).orElse(null);
        return app;
    }

    public ApplicationRequest updateApp(Long id,ApplicationRequest app){
        ApplicationRequest checkApp=getApp(id);

        if (Objects.isNull(checkApp)){
            return null;
        }
        ApplicationRequest updatedApp=appRepository.save(app);
        return updatedApp;
    }

    public boolean deleteApp(Long id){
        ApplicationRequest app=getApp(id);
        if(Objects.isNull(app)){
            return false;
        }
        appRepository.deleteById(id);
        return true;
    }

    public ApplicationRequest addOperator(Long id, Operators operator){
        ApplicationRequest app=getApp(id);
        if (Objects.isNull(app)){
            return null;
        }
        List<Operators> oper=app.getOperators();
        if(!oper.contains(operator)){
            oper.add(operator);
        }
        app.setOperators(oper);
        appRepository.save(app);
        return app;
    }


}
