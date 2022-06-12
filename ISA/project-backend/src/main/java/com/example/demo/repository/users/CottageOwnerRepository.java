package com.example.demo.repository.users;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public  interface CottageOwnerRepository extends JpaRepository<CottageOwner, Integer> {


    CottageOwner findByEmail(String email);
    @Query("select co from Cottage co left join fetch co.cottageOwner where co.id = ?1")
    Cottage fetchCottageOwnerByCottage(int id);

}
