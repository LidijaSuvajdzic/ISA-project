package com.example.demo.service.business;

import com.example.demo.model.business.Percent;
import com.example.demo.repository.business.PercentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PercentService {
    private PercentRepository percentRepository;

    public PercentService(PercentRepository percentRepository) {
        this.percentRepository = percentRepository;
    }
    public void save(Percent p) {
        this.percentRepository.save(p);
    }
    public Percent findById(int id) {
        return this.percentRepository.findById(id);
    }
    public Percent findByValue(double value) {
        return this.percentRepository.findByValue(value);
    }
    public List<Percent> findAll() {
        return this.percentRepository.findAll();
    }
    public void deleteAll() {
        this.percentRepository.deleteAll();
    }
    public void change(Percent p) {
        Percent percent = this.percentRepository.findById(1);
        this.percentRepository.deleteById(1);
        Percent newPercent = new Percent();
        newPercent.setValue(p.getValue());
        newPercent.setId(1);
        this.percentRepository.save(newPercent);
    }
}
