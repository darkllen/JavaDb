package lab3.lab3;

import lab3.lab3.dto.Author;
import lab3.lab3.dto.Book;
import lab3.lab3.repos.BookRepo;
import lab3.lab3.services.BookService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTests  {

    @Autowired
    private BookService bookService;

    @Test
    public void findByAuthor(){
        List<Book> books = bookService.getBooksAllAuthors(List.of("author1", "author2"));
        books.forEach(System.out::println);
    }
}
