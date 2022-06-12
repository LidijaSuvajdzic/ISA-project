package com.example.demo.service.users;

import com.example.demo.model.users.DeleteUserRequest;
import com.example.demo.repository.users.DeleteUserRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteUserRequestService {

    private DeleteUserRequestRepository deleteUserRequestRepository;

    public DeleteUserRequestService(DeleteUserRequestRepository deleteUserRequestRepository){
        this.deleteUserRequestRepository = deleteUserRequestRepository;
    }

    public DeleteUserRequest saveRequest(DeleteUserRequest request){
        request.setRejected(null);
        request.setAccepted(null);
        return this.deleteUserRequestRepository.save(request);
    }
    public DeleteUserRequest acceptRequest(DeleteUserRequest request){
        request.setAccepted(true);
        request.setRejected(false);
        return this.deleteUserRequestRepository.save(request);
    }
    public DeleteUserRequest rejectRequest(DeleteUserRequest request){
        request.setRejected(true);
        request.setAccepted(false);
        return this.deleteUserRequestRepository.save(request);
    }
    public DeleteUserRequest findById(int id) {
        return this.deleteUserRequestRepository.findById(id);
    }
    public List<DeleteUserRequest> findAll() {
        return deleteUserRequestRepository.findAll();
    }

    public void deleteRequest(DeleteUserRequest du) {
        this.deleteUserRequestRepository.delete(du);
    }
}
