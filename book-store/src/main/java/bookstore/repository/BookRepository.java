package bookstore.repository;

import bookstore.model.Book;
import bookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByCategory(Category name);

    List<Book> findByTitle(String title);

}
