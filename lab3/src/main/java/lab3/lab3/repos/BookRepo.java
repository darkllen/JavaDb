package lab3.lab3.repos;

import lab3.lab3.dto.Author;
import lab3.lab3.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepo  extends JpaRepository<Book, Integer> {
}
