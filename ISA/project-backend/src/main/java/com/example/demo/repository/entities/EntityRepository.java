package com.example.demo.repository.entities;

import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface EntityRepository extends JpaRepository<EntityClass, Integer> {


   // @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from EntityClass e where e.id = ?1 ")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    EntityClass findByIdTrans(int id);

   // @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from EntityClass e where e.id = ?1 ")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    EntityClass findById(int id);

    void deleteById(int id);

    EntityClass findByName(String name);
    
//    @Query("select e from EntityClass e join fetch e.subscribedClients s join fetch s.subscribedEntities en where en.id = ?1")
//    EntityClass findEntityWithClients(int id);
//
//    @Query("select e from EntityClass e left join fetch e.subscribedClients c where c.id = ?1")
//    List<EntityClass> findSubscribedEnities(int idClient);

    @Query("select e from Client e left join fetch e.subscribedEntities c where c.id = ?1")
    List<Client> findSubscribedClients(int id);

    @Query("select e from EntityClass e left join fetch e.reservedTerms c where c.id = ?1")
    EntityClass findEntityWithReservedTerms(int id);

    @Query("select e from EntityClass e left join fetch e.reservations c where c.id = ?1")
    EntityClass findEntityWithReservations(int id);

}
