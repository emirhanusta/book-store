package bookstore.bookstore.repository;

import bookstore.bookstore.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface UserRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}