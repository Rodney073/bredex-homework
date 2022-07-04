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

    /**
     * Searching for positions in db
     * Transforming DTO to domain object
     */
    public List<PositionDTO> searchPositions(PositionDTO positionDTO) {
        return positionRepository.searchPositions(positionMapper.transform(positionDTO))
                .stream()
                .map(position -> positionMapper.transform(position))
                .collect(Collectors.toList());
    }

    /**
     * Finding position by ID
     * Transforming returning domain object to DTO
     */
    public PositionDTO getPositionById(Long id) {
        return positionMapper.transform(positionRepository.getPosition(id));
    }

    /**
     * Generating URL DTO-s out of the found positions by: searchPositions()
     */
    public URLDTO getPositionURLList(PositionDTO positionDTO) {

        URLDTO urldto = new URLDTO(new ArrayList<>());

        // Searching for positions by name and location
        List<PositionDTO> positionList = searchPositions(positionDTO);

        //Building URL out of position data
        for (PositionDTO dto : positionList) {
            urldto.addURLToList(getActualBaseURL() + "/position/" + dto.getPositionId());
        }
        return urldto;
    }

    /**
     * Get currently used base URL
     */
    public String getActualBaseURL() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }


}
