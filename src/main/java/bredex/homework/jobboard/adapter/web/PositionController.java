package bredex.homework.jobboard.adapter.web;

import bredex.homework.jobboard.application.dtos.ClientDTO;
import bredex.homework.jobboard.application.dtos.PositionDTO;
import bredex.homework.jobboard.application.services.CreatePositionService;
import bredex.homework.jobboard.application.services.GetPositionService;
import bredex.homework.jobboard.application.services.RegisterClientService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @PostMapping("position")
    ResponseEntity<String> createPosition(@RequestBody PositionDTO positionDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "foo");

        val url = createPositionService.createPosition(positionDTO);

        return new ResponseEntity<>(
                url, headers, HttpStatus.OK);
    }

    @GetMapping("position/search")
    ResponseEntity<List<PositionDTO>> searchPositions(@RequestBody PositionDTO positionDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "foo");

        return new ResponseEntity<>(
                getPositionService.searchPositions(positionDTO), headers, HttpStatus.OK);
    }

    @GetMapping("position/{id}")
    ResponseEntity<Object> getPositionById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "foo");

        return new ResponseEntity<>(
                getPositionService.getPositionById(id), headers, HttpStatus.OK);
    }
}
