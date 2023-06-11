package com.threedots.bookstore.APITests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.threedots.bookstore.model.Book;
import com.threedots.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void helloTest() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getBooksTest() throws Exception {

        // mockMvc perform getrequest to /books
        // expect status 200
        mockMvc.perform(get("/api/books/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

    @Test
    public void postBooksTest() throws Exception {
        Book b = new Book();
        b.setTitle("1984");
        b.setAuthor("George Orwell");
        b.setGenre("Dystopian");
        b.setPublisher("Penguin");
        b.setPrice(10);
        b.setYearofPublication(1949);

        List<Book> bookList = new ArrayList<Book>();
        bookList.add(b);

        String requestBody = objectMapper.writeValueAsString(bookList);
        System.out.println(requestBody);

        MvcResult result = mockMvc.perform(post("/api/books/").contentType("application/json").content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        System.out.println(response);
        List<Book> returnedBooks = objectMapper.readValue(response, new TypeReference<List<Book>>(){});
        assertEquals(returnedBooks.size(), 1);

        Book rb = returnedBooks.get(0);
        assertEquals(rb.getTitle(), b.getTitle());

        Optional<Book> saved = bookRepository.findById(returnedBooks.get(0).getId());
        assertTrue(saved.isPresent());

        bookRepository.delete(saved.get());
    }


}
