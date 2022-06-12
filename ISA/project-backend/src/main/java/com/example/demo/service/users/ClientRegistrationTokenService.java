package com.example.demo.service.users;

import com.example.demo.repository.users.ClientRegistrationTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientRegistrationTokenService {

    private ClientRegistrationTokenRepository clientRegistrationTokenRepository;

    public ClientRegistrationTokenService (ClientRegistrationTokenRepository clientRegistrationTokenRepository){
        this.clientRegistrationTokenRepository = clientRegistrationTokenRepository;
    }

}
