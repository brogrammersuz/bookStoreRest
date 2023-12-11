package uz.brogrammers.bookStoreRest.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import uz.brogrammers.bookStoreRest.dto.BookResponse;
import uz.brogrammers.bookStoreRest.mapper.BookMapper;
import uz.brogrammers.bookStoreRest.model.BookModel;
import uz.brogrammers.bookStoreRest.service.BookService;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = BookController.class)
public class BookControllerWebLayerTest {

    @MockBean
    BookService bookService;
    @MockBean
    BookMapper bookMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetAll() throws Exception {


        var springResponse =  Files.readString(Path.of("src/test/resources/data/response.json"), StandardCharsets.UTF_8);

        var list = List.of(BookModel.builder()
                .id(1l)
                .title("Something")
                .isbn("sdfsd")
                .publisherId(1l)
                .authorsIds(Set.of(1l, 2l))
                .build());
        var dto = BookResponse.builder()
                .id(1l)
                .title("Something")
                .isbn("dsgfgdf")
                .publisherId(1L)
                .authorsIds(new HashSet<>(List.of(1l)))
                .build();



        when(bookService.getAll()).thenReturn(list);
        when(bookMapper.mapFromModelToDto(any(BookModel.class))).thenReturn(dto);

        mockMvc.perform(get("/book/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(springResponse));

        verify(bookService).getAll();
        verify(bookMapper).mapFromModelToDto(any());


    }

}
