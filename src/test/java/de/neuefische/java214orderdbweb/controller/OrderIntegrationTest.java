package de.neuefische.java214orderdbweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.java214orderdbweb.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void getOrdersWithEmptyList() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/order"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                        """));
    }

    @Test
    @DirtiesContext
    void getOrdersWithOneElement() throws Exception {
        //GIVEN
        String content = mockMvc.perform(MockMvcRequestBuilders.post("/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                []
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Order order = objectMapper.readValue(content, Order.class);

        //WHEN
        String expected = """
                        [{"id":"<ID>","productList":[]}]
                        """.replace("<ID>", order.getId());

        mockMvc.perform(MockMvcRequestBuilders.get("/order"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }
}
