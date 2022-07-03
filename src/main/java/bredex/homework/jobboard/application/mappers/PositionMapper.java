package bredex.homework.jobboard.application.mappers;

import bredex.homework.jobboard.application.dtos.PositionDTO;
import bredex.homework.jobboard.domain.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {

    public PositionDTO transform(Position position) {
        return new PositionDTO(
                position.getName(),
                position.getLocation()
        );
    }

    public Position transform(PositionDTO positionDTO) {

        Position position = new Position();

        position.setName(positionDTO.getName());
        position.setLocation(positionDTO.getLocation());

        return position;
    }
}
