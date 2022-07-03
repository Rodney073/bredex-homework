package bredex.homework.jobboard.application.services;

import bredex.homework.jobboard.application.dtos.PositionDTO;
import bredex.homework.jobboard.application.mappers.PositionMapper;
import bredex.homework.jobboard.domain.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePositionService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    PositionMapper positionMapper;

    public String createPosition(PositionDTO positionDTO) {
        return positionRepository.createPosition(positionMapper.transform(positionDTO));
    }
}
