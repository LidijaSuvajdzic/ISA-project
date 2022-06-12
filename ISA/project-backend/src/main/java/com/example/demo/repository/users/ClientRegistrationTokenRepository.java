package com.example.demo.repository.users;

import com.example.demo.model.users.ClientRegistrationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRegistrationTokenRepository extends JpaRepository<ClientRegistrationToken, Integer> {
}
