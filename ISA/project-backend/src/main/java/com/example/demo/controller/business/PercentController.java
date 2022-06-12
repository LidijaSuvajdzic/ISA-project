package com.example.demo.controller.business;

import com.example.demo.model.business.Percent;
import com.example.demo.model.users.DeleteUserRequest;
import com.example.demo.service.business.PercentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "api/percent")
public class PercentController {
    private PercentService percentService;

    public PercentController(PercentService percentService) {
        this.percentService = percentService;
    }

    @PostMapping(value = "/setPercentage/{percent}")
    public ResponseEntity<Void> setPercentage(@PathVariable double percent){
        System.out.print("OVDE SAM 1");
        List<Percent> list = this.percentService.findAll();
        System.out.print("OVDE SAM 2");
        if (list == null) {
            System.out.print("OVDE SAM 3");
            Percent newPercent = new Percent();
            newPercent.setValue(percent);
            this.percentService.save(newPercent);
        }else {
            System.out.print("OVDE SAM 4");
            this.percentService.deleteAll();
            Percent newPercent = new Percent();
            newPercent.setValue(percent);
            this.percentService.save(newPercent);
            }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
