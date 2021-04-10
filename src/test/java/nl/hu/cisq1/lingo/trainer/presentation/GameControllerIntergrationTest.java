package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.CiTestConfiguration;
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

    private UUID tempId;

    @BeforeEach
    void before() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/game/");
        MvcResult result = mockMvc.perform(request).andReturn();
        // TODO
    }

    @Test
    @DisplayName("WHen game is started a game is started and gives an id")
    void startGame() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/game/");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString());
    }

    @Test
    void getGame() {

    }

    @Test
    void startRound() {
    }

    @Test
    void guess() {
    }
}