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
@Table(name = "genre")
@ToString
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 30)
    private String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    @ToString.Exclude
    Set<Book> books;

}
