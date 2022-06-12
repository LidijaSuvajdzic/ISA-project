package com.example.demo.repository.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User save(UserRequest userRequest);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    //@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    User save(User user);

    User findByEmail(String email);
    User findById(int id);
    void deleteById(int id);
}
