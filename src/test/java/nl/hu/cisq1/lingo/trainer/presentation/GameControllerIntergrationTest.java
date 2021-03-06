package nl.hu.cisq1.lingo.trainer.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
class GameControllerIntergrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String  tempId;

    @BeforeEach
    void before() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/game")).andReturn();
        String json = result.getResponse().getContentAsString();
        Game game = objectMapper.readValue(json, Game.class);
        this.tempId = game.getId().toString();
    }

    @Test
    @DisplayName("When game is started a game is started and gives an id")
    void startGame() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/game/");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString());
    }

    @Test
    @DisplayName("Can Request game")
    void getGame() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/game/" + this.tempId);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString());
    }

    @Test
    @DisplayName("A round can be started")
    void startRound() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/game/" + this.tempId + "/play");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("STARTED"));
    }

    @Test
    @DisplayName("Guesses can be made")
    void guess() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/game/" + this.tempId + "/play");
        mockMvc.perform(request)
                .andExpect(status().isOk());
        RequestBuilder request2 = MockMvcRequestBuilders.patch("/game/" + this.tempId + "/guess?guess=hallo");
        mockMvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("INPROGRESS"));
    }
}