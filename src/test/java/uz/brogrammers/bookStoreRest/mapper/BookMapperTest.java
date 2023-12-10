package uz.brogrammers.bookStoreRest.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uz.brogrammers.bookStoreRest.entity.Author;
import uz.brogrammers.bookStoreRest.entity.Book;
import uz.brogrammers.bookStoreRest.model.BookModel;
import uz.brogrammers.bookStoreRest.respository.AuthorRepository;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class BookMapperTest {

    @MockBean
    private AuthorRepository authorRepository;

    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        bookMapper = new BookMapper(authorRepository);
    }

    @Test
    void mapFromEntityToModel() {

        //Given
        var entity = Book.builder()
                .id(1l)
                .title("Something")
                .isbn("kjdskjdsf")
                .publisherId(2l)
                .authors(new HashSet<>())
                .build();

        //When


        //Then

        var result = bookMapper.mapFromEntityToModel(entity);

        assertThat(result.getId()).isEqualTo(entity.getId());
        assertThat(result.getTitle()).isEqualTo(entity.getTitle());
        assertThat(result).isNotNull();

    }


    @Test
    void mapFromModelToEntity() {

        //Given
        var model = BookModel.builder()
                .id(1l)
                .title("dfgdfg")
                .isbn("asdfdf")
                .authorsIds(new HashSet<>(List.of(1l, 2l, 3l, 4l)))
                .build();

        //when
        when(authorRepository.findAllById(any(HashSet.class)))
                .thenReturn(List.of(
                        Author.builder().id(1l).build(),
                        Author.builder().id(2l).build(),
                        Author.builder().id(3l).build(),
                        Author.builder().id(4l).build()
                ));

        //then
        var result = bookMapper.mapFromModelToEntity(model);

        assertThat(result.getId()).isEqualTo(model.getId());
        assertThat(result.getAuthors()).isNotNull();
        assertThat(result.getAuthors()).isNotEmpty();
        assertThat(result.getAuthors().size()).isEqualTo(4);

    }
}