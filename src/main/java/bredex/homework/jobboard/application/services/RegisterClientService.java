package bredex.homework.jobboard.application.services;

import bredex.homework.jobboard.application.dtos.ClientDTO;
import bredex.homework.jobboard.application.mappers.ClientMapper;
import bredex.homework.jobboard.domain.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public String registerClient(ClientDTO clientDTO) {
        return clientRepository.registerClient(clientMapper.transform(clientDTO));
    }
}
