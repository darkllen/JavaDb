package lab.first.repos;

import lab.first.dto.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGroupRepo extends JpaRepository<ProductGroup, Integer> {
}
