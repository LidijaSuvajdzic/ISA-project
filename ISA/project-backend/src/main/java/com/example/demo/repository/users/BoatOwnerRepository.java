package com.example.demo.repository.users;

import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatOwnerRepository extends JpaRepository<BoatOwner, Integer> {

    BoatOwner findByEmail(String email);

    BoatOwner findById(int id);

    @Query("select bo from Boat bo left join fetch bo.boatOwner where bo.id = ?1")
    Boat fetchBoatOwnerByBoat(int idBoat);

    @Query("select b from BoatOwner b left join fetch b.boatList a where b.id = ?1")
    BoatOwner findOwnerWithBoats(int id_owner);



}
