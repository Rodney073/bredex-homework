package bredex.homework.jobboard.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionDTO {

    private String name;
    private String location;

    @Builder
    public PositionDTO(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
