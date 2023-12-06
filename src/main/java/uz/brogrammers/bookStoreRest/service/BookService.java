package uz.brogrammers.bookStoreRest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.brogrammers.bookStoreRest.entity.Book;
import uz.brogrammers.bookStoreRest.mapper.BookMapper;
import uz.brogrammers.bookStoreRest.model.BookModel;
import uz.brogrammers.bookStoreRest.respository.AuthorRepository;
import uz.brogrammers.bookStoreRest.respository.BookRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    public List<BookModel> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapFromEntityToModel)
                .toList();
    }

    public void create(BookModel request) {

        var entity = bookMapper.mapFromModelToEntity(request);

        bookRepository.save(entity);
    }

    public Optional<BookModel> findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapFromEntityToModel);
    }

    public BookModel update(BookModel model) {
        var saved = bookRepository.save(bookMapper.mapFromModelToEntity(model));

        return bookMapper.mapFromEntityToModel(saved);
    }

    public void deleteById(Long id) {

        bookRepository.deleteById(id);
    }

}
