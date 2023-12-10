package uz.brogrammers.bookStoreRest.rest;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import uz.brogrammers.bookStoreRest.mapper.BookMapper;
import uz.brogrammers.bookStoreRest.service.BookService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWebMvc
@AutoConfigureWebMvc
class BookControllerTest {

    @MockBean
    private BookService bookService;
    @MockBean
    private BookMapper bookMapper;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void testGetAll() {

        mockMvc.perform(get("/book/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("{[]}"));

    }


}