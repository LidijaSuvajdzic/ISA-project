package com.example.demo.service.email;

import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    private Environment env;

    public EmailService(JavaMailSender javaMailSender, Environment env){
        this.javaMailSender = javaMailSender;
        this.env = env;
    }

    @Async
    public void sendEmailForUserAuthentication(Client user) throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("spring.mail.username");
        mail.setSubject("Confirm your account");
        mail.setText("To confirm your account, please click here :" + "http://localhost:8081/api/auth/confirm-account?email=" + user.getEmail());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void sendEmailForReservation(User user) throws MailException, Exception {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("spring.mail.username");
        mail.setSubject("Reservation completed");
        mail.setText("You're reservation was successful!");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    public void sendConfirmEmail(String emailAdress) throws MailException, InterruptedException {
        System.out.println(emailAdress);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(emailAdress);
        mail.setFrom("spring.mail.username");
        mail.setSubject("Confirm your account");
        mail.setText("Your account confirmed!");
        javaMailSender.send(mail);
        System.out.println("Email poslat!");
    }

    public void sendEmailForReservationAction(String email) {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("spring.mail.username");
        mail.setSubject("Reservation completed");
        mail.setText("You're reservation was successful!");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    public void sendEmailForCreatedAction(String email, String entityName) {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("spring.mail.username");
        mail.setSubject("New action");
        mail.setText("New action for " + entityName + " created! Hurry up and use this special offer!" );
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }
}
