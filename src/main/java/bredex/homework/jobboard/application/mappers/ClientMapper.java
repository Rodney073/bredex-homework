package bredex.homework.jobboard.application.mappers;

import bredex.homework.jobboard.application.dtos.ClientDTO;
import bredex.homework.jobboard.domain.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDTO transform(Client client) {
        return new ClientDTO(
                client.getClientKey(),
                client.getClientId(),
                client.getClientName(),
                client.getEmail()
        );
    }

    public Client transform(ClientDTO clientDTO) {

        Client client = new Client();

        client.setClientKey(((clientDTO.getClientKey() == null) ? null : clientDTO.getClientKey()));
        client.setClientId(
                ((clientDTO.getGetClientId() == null) ? null : clientDTO.getGetClientId()));
        // Set client name if valid.
        client.validateClientName(clientDTO.getClientName())
                .setClientName(clientDTO.getClientName());
        // Set client email if valid
        client.validateEmail(clientDTO.getEmail())
                .setEmail(client.getEmail());

        return client;
    }
}
