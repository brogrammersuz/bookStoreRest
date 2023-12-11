package uz.brogrammers.bookStoreRest.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.brogrammers.bookStoreRest.dto.BookResponse;
import uz.brogrammers.bookStoreRest.dto.CreateBookRequest;
import uz.brogrammers.bookStoreRest.mapper.BookMapper;
import uz.brogrammers.bookStoreRest.service.BookService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/all")
    public List<BookResponse> getAllV2() {
        var list = bookService.getAll().stream()
                .map(bookMapper::mapFromModelToDto)
                .toList();

        return list;
    }

    @PostMapping("/create")
    public void createBook(@RequestBody CreateBookRequest request) {

        var model = bookMapper.mapFromRequestToModel(request);
        bookService.create(model);
        log.info("New book is created");
    }

    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable(name = "id") Long id) {
        return bookService.findById(id)
                .map(bookMapper::mapFromModelToDto)
                .orElseThrow(() -> new NoSuchElementException("Book with id " + id + " not found"));
    }

    @PutMapping("/update/{id}")
    public BookResponse update(@RequestBody CreateBookRequest request, @PathVariable Long id) {
        var book = bookService.findById(id).orElseThrow();

        var model = bookMapper.mapFromRequestToModel(request);
        model.setId(id);
        var response = bookService.update(model);

        return bookMapper.mapFromModelToDto(response);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
