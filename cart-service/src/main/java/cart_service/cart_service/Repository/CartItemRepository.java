package cart_service.cart_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cart_service.cart_service.Entity.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, String> {
}