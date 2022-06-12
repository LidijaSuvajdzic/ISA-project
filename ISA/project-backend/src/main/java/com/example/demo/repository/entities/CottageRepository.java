package com.example.demo.repository.entities;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CottageRepository extends JpaRepository<Cottage, Integer> {

    Cottage findById(int id);
    void deleteById(int id);
    void delete(Cottage c);
    Cottage save(Cottage cottage);

    @Query("select co from Cottage co left join fetch  co.cottageOwner c where c.id=?1")
    List<Cottage> findAllCottagesForOwner(int id);

    @Query("select b from Cottage b left join fetch b.additionalServices a where b.id = ?1")
    Cottage getCottageWithServices(int id);

    @Query("select c from CottageOwner c left join fetch c.cottageList l where l.id = ?1")
    CottageOwner findCottageOwner(int id);

}
