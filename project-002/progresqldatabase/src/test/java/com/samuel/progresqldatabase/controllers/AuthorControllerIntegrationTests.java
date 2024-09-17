package com.samuel.progresqldatabase.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samuel.progresqldatabase.TestDataUtil;
import com.samuel.progresqldatabase.domain.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorEntity testAuthorA  = TestDataUtil.createTestAuthor(1L);
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnSavedAuthor() throws Exception {
        AuthorEntity testAuthorA  = TestDataUtil.createTestAuthor(1L);
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Samuel Oseh")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(89)
        );
    }

    @Test
    public void testThatGetAuthorsSuccessfullyReturnsHttp201Created() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorsReturnsAllAuthors() throws  Exception {
        List<AuthorEntity> authors = TestDataUtil.createMultipleTestAuthors(10);
        List<AuthorEntity> authorsWithNullIds = authors.stream()
                .peek(author -> {
                    author.setId(null); // Set the ID to null
                    // Return the modified author
                })
                .toList();

        System.out.println(authorsWithNullIds);

        for (AuthorEntity author: authorsWithNullIds) {
            // convert object to json
            String authorJson = objectMapper.writeValueAsString(author);

            mockMvc.perform(
                    MockMvcRequestBuilders.post("/authors")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(authorJson)
            );
        }

        // get all objects

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Author 1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(1)
        );
    }
}
