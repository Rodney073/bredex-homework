package bredex.homework.jobboard.application.services;

import bredex.homework.jobboard.domain.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateClientService {

    @Autowired
    ClientRepository clientRepository;

    public boolean isKeyValid(String key) {
        return clientRepository.isKeyValid(key);
    }

}

