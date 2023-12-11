package uz.brogrammers.bookStoreRest.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uz.brogrammers.bookStoreRest.mapper.BookMapper;
import uz.brogrammers.bookStoreRest.model.BookModel;
import uz.brogrammers.bookStoreRest.service.BookService;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

//@AutoConfigureMockMvc
//@EnableWebMvc
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = BookController.class)
@SpringBootTest
class BookControllerTest {

    @Mock
    private BookService bookService;
    @Mock
    private BookMapper bookMapper;

    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookController = new BookController(bookService, bookMapper);
    }

    @Test
    void testGetAll() throws Exception {

        var list = List.of(BookModel.builder()
                .id(1l)
                .title("Something")
                .isbn("sdfsd")
                .publisherId(1l)
                .authorsIds(Set.of(1l, 2l))
                .build());

        when(bookService.getAll()).thenReturn(list);

        bookController.getAllV2();

        /*
        mockMvc.perform(get("/book/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("{[]}"));

         */
    }


}