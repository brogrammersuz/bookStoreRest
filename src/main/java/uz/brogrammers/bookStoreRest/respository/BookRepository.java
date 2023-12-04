package uz.brogrammers.bookStoreRest.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brogrammers.bookStoreRest.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
