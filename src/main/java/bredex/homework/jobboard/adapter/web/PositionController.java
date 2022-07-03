package bredex.homework.jobboard.adapter.web;

import bredex.homework.jobboard.application.dtos.PositionDTO;
import bredex.homework.jobboard.application.services.AuthenticateClientService;
import bredex.homework.jobboard.application.services.CreatePositionService;
import bredex.homework.jobboard.application.services.GetPositionService;
import bredex.homework.jobboard.exceptions.InvalidAPIKeyException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PositionController {

    @Autowired
    private CreatePositionService createPositionService;

    @Autowired
    private GetPositionService getPositionService;

    @Autowired
    private AuthenticateClientService authenticateClientService;

    @PostMapping("position")
    ResponseEntity<String> createPosition(@RequestHeader("API_key") String apiKey, @RequestBody PositionDTO positionDTO) {

        if (!authenticateClientService.isKeyValid(apiKey)) {
            throw new InvalidAPIKeyException("Invalid API key!");
        } else {
            val url = createPositionService.createPosition(positionDTO);
            return new ResponseEntity<>(
                    url, HttpStatus.OK);
        }
    }

    @GetMapping("position/search")
    ResponseEntity<List<PositionDTO>> searchPositions(@RequestHeader("API_key") String apiKey, @RequestBody PositionDTO positionDTO) {

        if (!authenticateClientService.isKeyValid(apiKey)) {
            throw new InvalidAPIKeyException("Invalid API key!");
        } else {
            return new ResponseEntity<>(
                    getPositionService.searchPositions(positionDTO), HttpStatus.OK);
        }

    }

    @GetMapping("position/{id}")
    ResponseEntity<Object> getPositionById(@RequestHeader("API_key") String apiKey, @PathVariable Long id) {

        if (!authenticateClientService.isKeyValid(apiKey)) {
            throw new InvalidAPIKeyException("Invalid API key!");
        } else {
            return new ResponseEntity<>(
                    getPositionService.getPositionById(id), HttpStatus.OK);
        }
    }

}
