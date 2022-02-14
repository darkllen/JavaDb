package lab3.lab3.dto;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BookSpecification implements Specification<Book> {

    private final BookCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        switch (criteria.getOperation()){
            case ">=":
                return formGteOp(root, builder);
            case "<=":
                return formLteOp(root, builder);
            case ">":
                return formGtOp(root, builder);
            case "<":
                return formLtOp(root, builder);
            case "=":
                return formEqOp(root, builder);
            default:
                return null;
        }
    }

    private Predicate formEqOp(Root<Book> root, CriteriaBuilder builder) {
        switch (criteria.getKey()){
            case "author":
                Join<Book, Author> authors = root.join(Book_.authors);
                return builder.equal(authors.get("name"), criteria.getValue());
            case "genre":
                Join<Book, Genre> genres = root.join(Book_.genres);
                return builder.equal(genres.get("name"), criteria.getValue());
            case "publisher":
                Join<Book, Publisher> publishers = root.join(Book_.publisher);
                return builder.equal(publishers.get("name"), criteria.getValue());
            default:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
    }

    private Predicate formLtOp(Root<Book> root, CriteriaBuilder builder) {
        return builder.lessThan(root.get(criteria.getKey()), (int)criteria.getValue());
    }

    private Predicate formGtOp(Root<Book> root, CriteriaBuilder builder) {
        return builder.greaterThan(root.get(criteria.getKey()), (int)criteria.getValue());
    }

    private Predicate formGteOp(Root<Book> root, CriteriaBuilder builder){
        return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (int)criteria.getValue());
    }

    private Predicate formLteOp(Root<Book> root, CriteriaBuilder builder) {
        return builder.lessThanOrEqualTo(root.get(criteria.getKey()), (int)criteria.getValue());
    }
}

