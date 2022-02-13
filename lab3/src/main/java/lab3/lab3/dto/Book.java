package lab3.lab3.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
@ToString

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    private String description;

    @NotNull
    @Min(1)
    private int price;

    @NotNull
    @Min(1)
    @Column(name = "palitur_type")
    private int paliturType;

    @NotNull
    @Min(1)
    @Column(name = "pages_num")
    private int pagesNum;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="publisher_id", nullable=false)
    private Publisher publisher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_to_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    Set<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    Set<Author> authors;
}