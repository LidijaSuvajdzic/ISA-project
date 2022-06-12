package com.example.demo.repository.business;

import com.example.demo.model.business.FreeTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeTermsRepository extends JpaRepository<FreeTerms, Integer> {
    FreeTerms findById(int id);


}
