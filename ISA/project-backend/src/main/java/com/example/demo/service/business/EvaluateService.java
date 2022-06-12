package com.example.demo.service.business;

import com.example.demo.model.business.Evaluate;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ComplaintRepository;
import com.example.demo.repository.business.EvaluateRepository;
import com.example.demo.repository.users.UserRepository;
import com.example.demo.service.entities.EntityService;
import com.example.demo.service.users.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateService {

    EvaluateRepository evaluateRepository;
    private EntityService entityService;
    private UserRepository userService;

    public EvaluateService(EvaluateRepository evaluateRepository, EntityService entityService, UserRepository userService){
        this.evaluateRepository = evaluateRepository;
        this.entityService = entityService;
        this.userService = userService;
    }

    public List<Evaluate> findAll() {
        return evaluateRepository.findAll();
    }

    public Evaluate findById(int id) {
        return evaluateRepository.findById(id);
    }

    public void acceptEvaluate(Evaluate e) {
        Evaluate evaluate = this.evaluateRepository.findEvaluateALl(e.getId());
        evaluate.setAccepted(true);

        this.evaluateRepository.save(evaluate);

        //racunanje prosecne ocene
        this.calculateGrade(e);
    }

    private void calculateGrade(Evaluate evaluate) {
        Evaluate evaluateWithEntity = this.evaluateRepository.findEvaluateALl(evaluate.getId());
        EntityClass entity = evaluateWithEntity.getEntity();
        User user = evaluateWithEntity.getUser();

        int sumEntity = 0;
        int brEntity = 0;
        int sumUser = 0;
        int brUser = 0;

        for (Evaluate e : this.findAll()){
            Evaluate e2 = this.evaluateRepository.findEvaluateALl(e.getId());
            if (e2.getEntity().getId() == entity.getId() && e2.getAccepted() == true){
                sumEntity = sumEntity + e2.getGradeForEntity();
                brEntity = brEntity + 1;
            }
            if (e2.getUser().getId() == user.getId()  && e2.getAccepted() == true){
                sumUser = sumUser + e2.getGradeForUser();
                brUser = brUser + 1;
            }
        }

        double grade = (double) sumEntity / (double) brEntity;
        entity.setGrade(grade);
        this.entityService.save(entity);

        double gradeUser = (double) sumUser / (double) brUser;
        user.setGrade(gradeUser);
        this.userService.save(user);
    }

    public void rejectEvaluate(Evaluate e) {
        e.setAccepted(false);
        this.evaluateRepository.save(e);
    }

    public void deleteById(int id) {
        Evaluate e = this.evaluateRepository.findById(id);
        this.evaluateRepository.delete(e);
    }

    public void save(Evaluate evaluate, User user){
        evaluate.setUserWhoSendsComplaint(user);
        this.evaluateRepository.save(evaluate);
    }
}
