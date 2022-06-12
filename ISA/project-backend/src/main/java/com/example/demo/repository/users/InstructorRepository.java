package com.example.demo.repository.users;

import com.example.demo.model.users.Client;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.Instructor;
import com.example.demo.service.users.InstructorService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Instructor findByEmail(String email);
}
