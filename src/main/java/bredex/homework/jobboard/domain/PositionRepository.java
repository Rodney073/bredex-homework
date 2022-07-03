package bredex.homework.jobboard.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository {

    String createPosition(Position position);

    List<Position> searchPositions(Position position);

    Position getPosition(Long id);
}
