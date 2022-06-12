package com.example.demo.service.business;

import com.example.demo.model.business.FreeTerms;
import com.example.demo.repository.business.FreeTermsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreeTermsService {
    private FreeTermsRepository freeTermsRepository;

    public FreeTermsService(FreeTermsRepository freeTermsRepository) {
        this.freeTermsRepository = freeTermsRepository;
    }

    public void save(FreeTerms ft) {
        System.out.print("STIGLA"+ft.getFreeFrom().toString());
        this.freeTermsRepository.save(ft);
    }

    public List<FreeTerms> findAll() {
        return this.freeTermsRepository.findAll();
    }
}
