package bredex.homework.jobboard.adapter.web;

import bredex.homework.jobboard.application.dtos.ClientDTO;
import bredex.homework.jobboard.application.services.RegisterClientService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ClientController {

    @Autowired
    private RegisterClientService registerClientService;

    @PostMapping("client")
    ResponseEntity<String> registerClient(@RequestBody ClientDTO clientDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");

        val clientID = registerClientService.registerClient(clientDTO);

        return new ResponseEntity<>(
            clientID, headers, HttpStatus.OK);
    }
}
