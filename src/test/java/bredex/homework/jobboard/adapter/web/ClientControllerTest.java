package bredex.homework.jobboard.adapter.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClientController.class)
class ClientControllerTest {

    private MockMvc mockMvc;


    @BeforeEach
    void init() {

    }

    @Test
    void requestStatusShouldBeOK() {
        /*mockMvc.perform(post("/client")
                        .contentType("application/json"))
                .andExpect(status().isOk());*/
    }

    @Test
    void UUIDAPIKeyShouldBeReturned() {

    }

    @Test
    void ClientNameShouldFailIfLongerThan100Chars() {

    }

    @Test
    void InvalidEmailAddressShouldBeValid() {

    }

}