package bredex.homework.jobboard.application.dtos;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

    @Parameter(hidden = true)
    private final String clientKey;
    @Parameter(hidden = true)
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
