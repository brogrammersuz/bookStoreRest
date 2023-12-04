package uz.brogrammers.bookStoreRest.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brogrammers.bookStoreRest.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
