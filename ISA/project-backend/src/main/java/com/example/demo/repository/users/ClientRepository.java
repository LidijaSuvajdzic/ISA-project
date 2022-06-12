package com.example.demo.repository.users;

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
public interface ClientRepository extends JpaRepository<Client,Integer> {


    Client findByEmail(String email);

    Client findById(int id);
    Client save(Client client);

    @Query("select e from EntityClass e left join fetch e.subscribedClients c where c.id = ?1")
    List<EntityClass> findSubscribedEnities(int idClient);

    @Query("select c from Client c left join fetch c.subscribedEntities e where e.id=?1")
    List<Client> findClientWithSubscribedEntities(int id);

//    @Query("select c from Client c left join fetch Reservations r where r.client.id = c.id and  r.id=?1")
//    Client findClientFromReservation( int id_reservation);

}
