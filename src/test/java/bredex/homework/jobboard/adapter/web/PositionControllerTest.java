package bredex.homework.jobboard.adapter.web;

import bredex.homework.jobboard.application.dtos.PositionDTO;
import bredex.homework.jobboard.helper.UrlTest;
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

        validPositionDTO = new PositionDTO(null, "Java", "London");
        validAPIKey = "12345678-aaaa-bbbb-1234-000000000001";
        invalidAPIKey = "12345678-aaaa-bbbb-1234-000000000000";

    }


    @Test
    void requestStatusShouldBeOKWithCorrectRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("API_key", validAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
    void correctRequestShouldReturnWithAnURL() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("API_key", validAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(isValidURL(mvcResult.getResponse().getContentAsString()));

    }

    @Test
    void errorMsgShouldBeThrownWhenPositionNameIsTooLong() throws Exception {

        PositionDTO invalidPosition = new PositionDTO(null, "PositionNamePositionNamePositionNamePositionNamePositionName", "Location");

        mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("API_key", validAPIKey)
                        .content(asJsonString(invalidPosition))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("API_key", validAPIKey)
                        .content(asJsonString(invalidPosition))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("The position name can't be longer than 50 characters!"));

    }

    @Test
    void errorMsgShouldBeThrownWhenLocationNameIsTooLong() throws Exception {

        PositionDTO invalidPosition = new PositionDTO(null, "Position name", "LocationLocationLocationLocationLocationLocationLocation");

        mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("API_key", validAPIKey)
                        .content(asJsonString(invalidPosition))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("API_key", validAPIKey)
                        .content(asJsonString(invalidPosition))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("The location name can't be longer than 50 characters!"));

    }

    @Test
    void errorMsgShouldBeThrownWhenAPIKeyIsInvalid() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("API_key", invalidAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/position")
                        .header("API_key", invalidAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Invalid API key!"));

    }

    @Test
    void errorMsgShouldBeThrownWhenAPIKeyIsInvalidAtSearch() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/position/search")
                        .header("API_key", invalidAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/position/search")
                        .header("API_key", invalidAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Invalid API key!"));

    }

    @Test
    void getURLsWhenCorrectSearchRequestIsSent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/position/search")
                        .header("API_key", validAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/position/search")
                        .header("API_key", validAPIKey)
                        .content(asJsonString(validPositionDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        UrlTest urlTest = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), UrlTest.class);

        assertEquals(2, urlTest.getListOfURLs().size());

        for (int i = 0; i < urlTest.getListOfURLs().size(); i++) {
            assertTrue(isValidURL(urlTest.getListOfURLs().get(i)));
        }
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

}