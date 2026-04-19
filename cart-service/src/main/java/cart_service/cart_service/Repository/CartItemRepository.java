package cart_service.cart_service.Repository;

import cart_service.cart_service.Entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {

    @Query(value = "SELECT * FROM cartitem_entity WHERE quantity > :quantity", nativeQuery = true)
    List<CartItemEntity> findItemsWithQuantityGreaterThan(@Param("quantity") int quantity);
}