package bredex.homework.jobboard.adapter.web;

import bredex.homework.jobboard.application.dtos.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ClientDTO correctClientData;

    @BeforeEach
    void init() {
        correctClientData = new ClientDTO(null, null, "Myname", "mail@example.com");
    }

    @Test
    void requestStatusShouldBeOK() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/client")
                        .content(asJsonString(correctClientData))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void UUIDAPIKeyShouldBeReturned() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/client")
                        .content(asJsonString(correctClientData))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();


        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertTrue(actualResponseBody.matches("[a-f0-9]{8}(?:-[a-f0-9]{4}){4}[a-f0-9]{8}"));
        System.out.println(actualResponseBody);

    }

    @Test
    void ErrorMessageRespondShouldBeReturnedIfClientNameLongerThan100Chars() throws Exception {

        ClientDTO clientDTO = new ClientDTO(null, null, "MynameMynameMynameMynameMynameMynameMynameMynameMynamMynameMynameMynameMynameMynameMynameMynameMynameMynam", "mail@example.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/client")
                        .content(asJsonString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/client")
                        .content(asJsonString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("The name can't be longer than 100 characters!"));

    }
    @Test
    void ErrorMessageRespondShouldBeReturnedIfClientNameIsEmpty() throws Exception {

        ClientDTO clientDTO = new ClientDTO(null, null, "", "mail@example.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/client")
                        .content(asJsonString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/client")
                        .content(asJsonString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("The name can not be empty!"));

    }

    @Test
    void ErrorMessageRespondShouldBeReturnedIfEmailFormatInvalid() throws Exception {

        ClientDTO clientDTO = new ClientDTO(null, null, "MyName", "myEmailAdd");
        mockMvc.perform(MockMvcRequestBuilders.post("/client")
                        .content(asJsonString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/client")
                        .content(asJsonString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Invalid email address format!"));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}