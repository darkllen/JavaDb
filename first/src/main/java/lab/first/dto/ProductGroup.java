package lab.first.dto;

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
@Table(name = "product_group")
@ToString
public class ProductGroup {
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

    @OneToMany(mappedBy = "productGroup", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @ToString.Exclude private Set<Product> products;
}
