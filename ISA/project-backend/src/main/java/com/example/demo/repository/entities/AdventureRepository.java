package com.example.demo.repository.entities;

import com.example.demo.model.entities.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureRepository extends JpaRepository<Adventure,Integer> {

    @Query("select a from Adventure a left join fetch a.instructor where a.id = ?1")
    Adventure fetchInstructor(int id);

    Adventure findByNameOfAdventure(String namee);


    Adventure findById(int id);
}
