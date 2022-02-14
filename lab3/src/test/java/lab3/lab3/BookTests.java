package lab3.lab3;

import lab3.lab3.dto.*;
import lab3.lab3.repos.BookRepo;
import lab3.lab3.services.BookService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTests  {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepo bookRepo;

    @Test
    @Transactional
    public void testGetBooksByAuthorName(){
        List<Book> books = bookService.getBooksByAuthorName("author1");
        List<Book> answer = bookRepo.findAll().stream().
                filter(x ->
                        x.getAuthors().stream().map(Author::getName).collect(Collectors.toList()).contains("author1")).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testGetBooksByGenreName(){
        List<Book> books = bookService.getBooksByGenreName("genre1");
        List<Book> answer = bookRepo.findAll().stream().
                filter(x ->
                        x.getGenres().stream().map(Genre::getName).collect(Collectors.toList()).contains("genre1")).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testGetBooksByPublisherName(){
        List<Book> books = bookService.getBooksByPublisherName("publisher1");
        List<Book> answer = bookRepo.findAll().stream().
                filter(x ->
                        x.getPublisher().getName().equals("publisher1")).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testGetBooksBySomeAuthors(){
        List<String> names = List.of("author1", "author2");
        List<Book> books = bookService.getBooksBySomeAuthors(names);
        List<Book> answer = bookRepo.findAll().stream().
                filter(x -> !Collections.disjoint(x.getAuthors().stream().map(Author::getName).collect(Collectors.toList()),names)).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testGetBooksAllAuthors(){
        List<String> names = List.of("author1", "author2");
        List<Book> books = bookService.getBooksAllAuthors(names);
        List<Book> answer = bookRepo.findAll().stream().
                filter(x ->
                        x.getAuthors().stream().map(Author::getName).collect(Collectors.toList()).containsAll(names)).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testGetBooksAllGenres(){
        List<String> names = List.of("genre1", "genre2");
        List<Book> books = bookService.getBooksAllGenres(names);
        List<Book> answer = bookRepo.findAll().stream().
                filter(x ->
                        x.getGenres().stream().map(Genre::getName).collect(Collectors.toList()).containsAll(names)).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testGetBooksBySomeGenres(){
        List<String> names = List.of("genre1", "genre2");
        List<Book> books = bookService.getBooksBySomeGenres(names);
        List<Book> answer = bookRepo.findAll().stream().
                filter(x -> !Collections.disjoint(x.getGenres().stream().map(Genre::getName).collect(Collectors.toList()),names)).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testGetBooksInPagesRange(){
        int lower = 10;
        int upper = 20;
        List<Book> books = bookService.getBooksInPagesRange(lower, upper);
        List<Book> answer = bookRepo.findAll().stream().
                filter(x -> upper >= x.getPagesNum() && x.getPagesNum() >= lower).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testGetBooksInPriceRange(){
        int lower = 100;
        int upper = 200;
        List<Book> books = bookService.getBooksInPriceRange(lower, upper);
        List<Book> answer = bookRepo.findAll().stream().
                filter(x -> upper >= x.getPrice() && x.getPrice() >= lower).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testGetBooksInPriceEqual(){
        int eq = 100;
        List<Book> books = bookService.getBooksPriceEqual(eq);
        List<Book> answer = bookRepo.findAll().stream().
                filter(x -> x.getPrice() == eq).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testGetBooksInPagesEqual(){
        int eq = 10;
        List<Book> books = bookService.getBooksPagesEqual(eq);
        List<Book> answer = bookRepo.findAll().stream().
                filter(x -> x.getPagesNum() == eq).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testSearchTextDescription(){
        String text = "descr1";
        List<Book> books = bookService.searchTextEverywhere(text);
        List<Book> answer = bookRepo.findAll().stream().
                filter(x -> x.getDescription().equals(text)).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testSearchTextGenre(){
        String text = "genre1";
        List<Book> books = bookService.searchTextEverywhere(text);
        List<Book> answer = bookService.getBooksByGenreName(text);
        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testSearchTextPublisher(){
        String text = "publisher1";
        List<Book> books = bookService.searchTextEverywhere(text);
        List<Book> answer = bookService.getBooksByPublisherName(text);
        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testFindByNull(){
        List<Book> books = bookService.filter(null);
        List<Book> answer = bookRepo.findAll();
        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testFindAnd(){
        int price = 100;
        String descr = "descr1";
        BookSpecification bookSpecificationA = new BookSpecification(new BookCriteria("description", "=", descr));
        BookSpecification bookSpecificationB = new BookSpecification(new BookCriteria("price", "=", price));

        List<Book> books = bookService.filter(bookSpecificationA.and(bookSpecificationB));
        List<Book> answer = bookRepo.findAll().stream().
                filter(x -> x.getPrice() == price && x.getDescription().equals(descr)).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }

    @Test
    @Transactional
    public void testFindOr(){
        String publisher = "publisher1";
        String author = "author1";
        BookSpecification bookSpecificationA = new BookSpecification(new BookCriteria("publisher", "=", publisher));
        BookSpecification bookSpecificationB = new BookSpecification(new BookCriteria("author", "=", author));

        List<Book> books = bookService.filter(bookSpecificationA.or(bookSpecificationB));
        List<Book> answer = bookRepo.findAll().stream().
                filter(x -> x.getAuthors().stream().map(Author::getName).collect(Collectors.toList()).contains(author) ||
                        x.getPublisher().getName().equals(publisher)).
                collect(Collectors.toList());

        assert answer.containsAll(books) && books.containsAll(answer);
    }
}
