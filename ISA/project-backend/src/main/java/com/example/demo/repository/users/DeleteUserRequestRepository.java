package com.example.demo.repository.users;

import com.example.demo.model.users.DeleteUserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteUserRequestRepository extends JpaRepository<DeleteUserRequest, Integer> {
    DeleteUserRequest findById(int id);
}
