package product_service.product_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product_service.product_service.Entity.ProductEntity;

@Repository

public interface ProductRepository extends JpaRepository<ProductEntity,String> {
}
