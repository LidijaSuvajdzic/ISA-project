package com.example.demo.repository.entities;

import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalServicesRepository  extends JpaRepository<AdditionalService, Integer> {
   AdditionalService findById(int id);

  // List<AdditionalService> saveAll(List<AdditionalService> services); //nisam sigurna radi li

   @Query("select ac from AdditionalService ac join fetch ac.entity e  where e.id=?1")
   List<AdditionalService> findAdditionalServicesForCottage(int id);

   @Query("select ac from AdditionalService ac join fetch ac.entity e  where e.name=?1")
   List<AdditionalService> findAdditionalServicesForAdventure(String name);

   AdditionalService findByName(String name);
}
