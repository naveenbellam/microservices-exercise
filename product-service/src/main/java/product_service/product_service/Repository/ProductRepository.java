package product_service.product_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import product_service.product_service.Entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}