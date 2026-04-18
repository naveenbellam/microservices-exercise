package product_service.product_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import product_service.product_service.Entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query(value = "SELECT * FROM product_entity WHERE price > :price", nativeQuery = true)
    List<ProductEntity> findExpensiveProducts(@Param("price") Double price);
}