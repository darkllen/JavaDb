package lab3.lab3.repos;

import lab3.lab3.dto.Author;
import lab3.lab3.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo  extends JpaRepository<Author, Integer> {

}

