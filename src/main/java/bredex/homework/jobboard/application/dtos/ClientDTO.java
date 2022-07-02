package bredex.homework.jobboard.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

    private final String clientKey;
    private final Long getClientId;
    private final String clientName;
    private final String email;

    @Builder
    public ClientDTO(String clientKey, Long getClientId, String clientName, String email) {
        this.clientKey = clientKey;
        this.getClientId = getClientId;
        this.clientName = clientName;
        this.email = email;
    }
}
