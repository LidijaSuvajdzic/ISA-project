package com.example.demo.repository.entities;

import com.example.demo.model.entities.Boat;
import com.example.demo.model.users.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Integer> {

    @Query(" select b from Boat b left join fetch b.boatOwner bo where bo.id = ?1")
    List<Boat> findBoatsForBoatOwner(int id);

    Boat findById(int id);

    @Query("select b from Boat b left join fetch b.additionalServices a where b.id = ?1")
    Boat findBoatWithServices(Integer id);

    @Query("select b from BoatOwner b left join fetch b.boatList l where l.id = ?1")
    BoatOwner findBoatOwner(int id);


}
