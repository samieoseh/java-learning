package com.samuel.progresqldatabase.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samuel.progresqldatabase.TestDataUtil;
import com.samuel.progresqldatabase.domain.dto.AuthorDto;
import com.samuel.progresqldatabase.domain.entities.AuthorEntity;
import com.samuel.progresqldatabase.domain.entities.BookEntity;
import com.samuel.progresqldatabase.services.AuthorService;
import com.samuel.progresqldatabase.services.BookService;
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

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    private MockMvc mockMvc;
    private  AuthorService authorService;
    private BookService bookService;
    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.bookService = bookService;
        this.authorService = authorService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public  void testThatCreateBookReturnsHttpStatus201Created() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor(2L);
        authorEntity.setId(null);

        BookEntity bookEntity = TestDataUtil.createTestBook(authorEntity);

        String bookJson = objectMapper.writeValueAsString(bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.put(
                        "/books/123-456-789"
                ).contentType(MediaType.APPLICATION_JSON).content(
                        bookJson
                )
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public  void testThatCreateBookSuccessfullyReturnSavedBook() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor(2L);
        authorEntity.setId(null);

        BookEntity bookEntity = TestDataUtil.createTestBook(authorEntity);

        String bookJson = objectMapper.writeValueAsString(bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.put(
                        "/books/123-456-789"
                ).contentType(MediaType.APPLICATION_JSON).content(
                        bookJson
                )
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("The Shadow in the Attic")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("123-456-789")
        );
    }

    @Test
    public  void testThatGetBooksReturnsHttpStatus200Created() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/books"
                )
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public  void testThatGetBooksSuccessfullyReturnAllBooks() throws Exception {
        int numOfBooks = 10;
        AuthorEntity author = TestDataUtil.createTestAuthor(1L);
        authorService.createAuthor(author);
        BookEntity bookEntity = TestDataUtil.createTestBook(author);

        for (int num = 1; num < numOfBooks; num++) {
            bookService.createBook("123-456-789-" + num, bookEntity);
        }

        mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/books"
                )
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("123-456-789-1")
        );
    }
}
