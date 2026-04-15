package cart_service.cart_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cart_service.cart_service.Entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, String> {
}