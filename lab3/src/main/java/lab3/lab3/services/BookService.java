package lab3.lab3.services;

import lab3.lab3.dto.*;
import lab3.lab3.repos.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private List<Book> getBooksByFiledName(String field, String name){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select distinct b FROM Book b JOIN b."+field+" a WHERE a.name=:name");
        query.setParameter("name", name);
        List<Book> res = query.getResultList();
        entityManager.close();
        return res;
    }

    private List<Book> getBooksBySomeFieldNames(String field, List<String> names){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select distinct b FROM Book b JOIN b."+field+" a WHERE a.name IN :names");
        query.setParameter("names", names);
        List<Book> res = query.getResultList();
        entityManager.close();
        return res;
    }

    private List<Book> getBooksInFieldRange(String field, int lower, int upper){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select b FROM Book b WHERE b."+field+" >= :lower and b."+field+" <= :upper");
        query.setParameter("lower", lower);
        query.setParameter("upper", upper);
        List<Book> res = query.getResultList();
        entityManager.close();
        return res;
    }

    private List<Book> getBooksFieldEqual(String field, int val){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select b FROM Book b WHERE b."+field+" = :val");
        query.setParameter("val", val);
        List<Book> res = query.getResultList();
        entityManager.close();
        return res;
    }


    public List<Book> getBooksByAuthorName(String name){
        return getBooksByFiledName("authors", name);
    }
    public List<Book> getBooksByPublisherName(String name){
        return getBooksByFiledName("publisher", name);
    }
    public List<Book> getBooksByGenreName(String name){
        return getBooksByFiledName("genres", name);
    }


    public List<Book> getBooksBySomeAuthors(List<String> names){
        return getBooksBySomeFieldNames("authors", names);
    }
    public List<Book> getBooksBySomeGenres(List<String> names){
        return getBooksBySomeFieldNames("genres", names);
    }

    public List<Book> getBooksInPriceRange(int lower, int upper){
        return getBooksInFieldRange("price", lower, upper);
    }
    public List<Book> getBooksInPagesRange(int lower, int upper){
        return getBooksInFieldRange("pagesNum", lower, upper);
    }

    public List<Book> getBooksPriceEqual(int price){
        return getBooksFieldEqual("price", price);
    }
    public List<Book> getBooksPagesEqual(int pagesNum){
        return getBooksFieldEqual("pagesNum", pagesNum);
    }

    public List<Book> getBooksAllAuthors(List<String> authorNames){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select b FROM Book b " +
                "WHERE NOT EXISTS " +
                "(SELECT author " +
                "FROM Author author " +
                "WHERE author.name IN :names AND author.name NOT IN " +
                "(SELECT a.name FROM b.authors a))");
        query.setParameter("names", authorNames);
        List<Book> res = query.getResultList();
        entityManager.close();
        return res;
    }
    public List<Book> getBooksAllGenres(List<String> genreNames){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select b FROM Book b " +
                "WHERE NOT EXISTS " +
                "(SELECT genre " +
                "FROM Genre genre " +
                "WHERE genre.name IN :names AND genre.name NOT IN " +
                "(SELECT a.name FROM b.genres a))");
        query.setParameter("names", genreNames);
        List<Book> res = query.getResultList();
        entityManager.close();
        return res;
    }

    public List<Book> searchTextEverywhere(String text){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = query.from(Book.class);

        Join<Book, Author> authors = book.join(Book_.authors);
        Join<Book, Genre> genres = book.join(Book_.genres);
        Join<Book, Publisher> publishers = book.join(Book_.publisher);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(book.get("name"), text));
        predicates.add(criteriaBuilder.equal(book.get("description"), text));
        predicates.add(criteriaBuilder.equal(publishers.get("name"), text));
        predicates.add(criteriaBuilder.equal(authors.get("name"), text));
        predicates.add(criteriaBuilder.equal(genres.get("name"), text));

        query.select(book).distinct(true).where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        List<Book> res = entityManager.createQuery(query).getResultList();
        entityManager.close();
        return res;
    }

    public List<Book> filter(Specification<Book> specification) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = query.from(Book.class);

        query.select(book);
        if (specification != null)
            query.distinct(true).where(specification.toPredicate(book, query, criteriaBuilder));
        List<Book> res = entityManager.createQuery(query).getResultList();
        entityManager.close();
        return res;
    }

}
