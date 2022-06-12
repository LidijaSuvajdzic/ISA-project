package com.example.demo.model.users;

import javax.persistence.*;

@Entity
public class DeleteUserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)

    private int id;

    @Column(name="explanation")
    private String explanation;

    @Column(name="rejected")
    private Boolean rejected;
    @Column(name="accepted")
    private Boolean accepted;


    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    public DeleteUserRequest() {}

    public DeleteUserRequest(String explanation, User user) {
        this.explanation = explanation;
        this.user = user;
    }

    public DeleteUserRequest(String explanation, int id) {
        this.explanation = explanation;
        this.id=id;
        //this.user = user;
        //this.reviewedRequest = reviewedRequest;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Boolean getRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }
    public String getExplanation() {
        return explanation;
    }
    public Boolean getAccepted() {
        return accepted;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
