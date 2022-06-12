package com.example.demo.repository.business;

import com.example.demo.model.business.Percent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PercentRepository extends JpaRepository<Percent, Integer> {
    Percent findById(int id);
    Percent findByValue(double value);
}
