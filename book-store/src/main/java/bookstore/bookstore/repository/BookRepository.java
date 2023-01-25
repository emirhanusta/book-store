package bookstore.bookstore.repository;

import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByCategory(Category name);

    List<Book> findByTitle(String title);

}
