package bredex.homework.jobboard.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionDTO {

    private Long positionId;
    private String name;
    private String location;

    @Builder

    public PositionDTO(Long positionId, String name, String location) {
        this.positionId = positionId;
        this.name = name;
        this.location = location;
    }
}
