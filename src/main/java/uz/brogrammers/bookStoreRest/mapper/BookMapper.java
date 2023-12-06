package uz.brogrammers.bookStoreRest.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uz.brogrammers.bookStoreRest.dto.BookResponse;
import uz.brogrammers.bookStoreRest.dto.CreateBookRequest;
import uz.brogrammers.bookStoreRest.entity.Book;
import uz.brogrammers.bookStoreRest.model.BookModel;
import uz.brogrammers.bookStoreRest.respository.AuthorRepository;

import java.util.HashSet;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookMapper {

    private final AuthorRepository authorRepository;

    public BookModel mapFromEntityToModel(Book book) {

        log.info(book.toString());

        var authorsIds = book.getAuthors().stream()
                .map(a -> a.getId())
                .collect(Collectors.toSet());

        return BookModel.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publisherId(book.getPublisherId())
                .authorsIds(authorsIds)
                .build();
    }

    public BookResponse mapFromModelToDto(BookModel book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publisherId(book.getPublisherId())
                .authorsIds(book.getAuthorsIds())
                .build();
    }

    public BookModel mapFromRequestToModel(CreateBookRequest request) {
        return BookModel.builder()
                .title(request.getTitle())
                .isbn(request.getIsbn())
                .publisherId(request.getPublisherId())
                .authorsIds(request.getAuthorsIds())
                .build();
    }


    public Book mapFromModelToEntity(BookModel book) {

        var authors = new HashSet<>(authorRepository.findAllById(book.getAuthorsIds()));

        return Book.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publisherId(book.getPublisherId())
                .authors(authors)
                .build();
    }

}
