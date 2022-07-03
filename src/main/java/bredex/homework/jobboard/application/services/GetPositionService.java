package bredex.homework.jobboard.application.services;

import bredex.homework.jobboard.application.dtos.PositionDTO;
import bredex.homework.jobboard.application.dtos.URLDTO;
import bredex.homework.jobboard.application.mappers.PositionMapper;
import bredex.homework.jobboard.domain.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
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

    public URLDTO getPositionURLList(PositionDTO positionDTO) {

        URLDTO urldto = new URLDTO(new ArrayList<>());
        List<PositionDTO> positionList = searchPositions(positionDTO);

        for (PositionDTO dto : positionList) {
            urldto.addURLToList(getPositionURL() + "/position/" + dto.getPositionId());
        }

        return urldto;
    }

    public String getPositionURL() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }


}
