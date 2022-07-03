package bredex.homework.jobboard.application.services;

import bredex.homework.jobboard.application.dtos.PositionDTO;
import bredex.homework.jobboard.application.mappers.PositionMapper;
import bredex.homework.jobboard.domain.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetPositionService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    PositionMapper positionMapper;

    public List<PositionDTO> searchPositions(PositionDTO positionDTO) {
        return positionRepository.searchPositions(positionMapper.transform(positionDTO))
                .stream()
                .map(position -> positionMapper.transform(position))
                .collect(Collectors.toList());
    }

    public PositionDTO getPositionById(Long id) {
        return positionMapper.transform(positionRepository.getPosition(id));
    }

}
