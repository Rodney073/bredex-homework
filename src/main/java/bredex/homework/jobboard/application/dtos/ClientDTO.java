package bredex.homework.jobboard.application.dtos;

import lombok.Builder;

public class ClientDTO {

    private String id;
    private String clientName;
    private String email;

    @Builder
    public ClientDTO(String id, String clientName, String email) {
        this.id = id;
        this.clientName = clientName;
        this.email = email;
    }
}
