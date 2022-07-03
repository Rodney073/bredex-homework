package bredex.homework.jobboard.adapter.web;

import bredex.homework.jobboard.application.dtos.PositionDTO;
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

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PositionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private PositionDTO validPositionDTO;
    private String validAPIKey;
    private String invalidAPIKey;

    @BeforeEach
    void init() {

        validPositionDTO = new PositionDTO(null, "Position name", "MyLocation");
        validAPIKey = getValidKeyFromDatabase();
        invalidAPIKey = "12345678-aaaa-bbbb-1234-000000000000";

    }


    @Test
    void requestStatusShouldBeOKWithCorrectRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("token", validAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
    void correctRequestShouldReturnWithAnURL() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("token", validAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(isValidURL(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void errorMsgShouldBeThrownWhenPositionNameIsTooLong() throws Exception {

        PositionDTO invalidPosition = new PositionDTO(null, "PositionNamePositionNamePositionNamePositionNamePositionName", "Location");

        mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("token", validAPIKey)
                        .content(asJsonString(invalidPosition))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("token", validAPIKey)
                        .content(asJsonString(invalidPosition))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("The position name can't be longer than 50 characters!"));
    }

    @Test
    void errorMsgShouldBeThrownWhenLocationNameIsTooLong() throws Exception {
        PositionDTO invalidPosition = new PositionDTO(null, "Position name", "LocationLocationLocationLocationLocationLocationLocation");

        mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("token", validAPIKey)
                        .content(asJsonString(invalidPosition))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("token", validAPIKey)
                        .content(asJsonString(invalidPosition))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("The location name can't be longer than 50 characters!"));

    }

    @Test
    void errorMsgShouldBeThrownWhenAPIKeyIsInvalid() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("token", invalidAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("token", "12345678-aaaa-bbbb-1234-000000000000")
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Invalid API key!"));


    }


    public static boolean isValidURL(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getValidKeyFromDatabase() {
        return "";
    }

}