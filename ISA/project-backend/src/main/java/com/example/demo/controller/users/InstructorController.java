package com.example.demo.controller.users;

import com.example.demo.dto.users.ClientDTO;
import com.example.demo.dto.users.InstructorDTO;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.Instructor;
import com.example.demo.model.users.User;
import com.example.demo.service.users.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/instructor")
public class InstructorController
{
    InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/profileInstructor")
    public ResponseEntity<InstructorDTO> getProfileInfo()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user.getRole().getName().equals("ROLE_INSTRUCTOR")){
            Instructor instructor = instructorService.findByEmail(user.getEmail());
            return new ResponseEntity<>(new InstructorDTO(instructor), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
