package com.example.demo.repository.business;

import com.example.demo.model.business.ReservedTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedTermRepository extends JpaRepository<ReservedTerm, Integer> {
    @Query("select t from ReservedTerm t join fetch t.entity e where e.id = ?1")
    ReservedTerm findTermWithEntity(int id);

    ReservedTerm findById(int id);
}
