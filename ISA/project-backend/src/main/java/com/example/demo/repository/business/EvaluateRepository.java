package com.example.demo.repository.business;

import com.example.demo.model.business.Complaint;
import com.example.demo.model.business.Evaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluateRepository extends JpaRepository<Evaluate, Integer> {
    Evaluate findById(int id);

    Evaluate save(Evaluate evaluate);

    @Query("select e from Evaluate e join fetch e.entity join fetch e.user where e.id = ?1")
    Evaluate findEvaluateALl(int id);
}
