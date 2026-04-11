package cart_service.cart_service.Repository;

import cart_service.cart_service.Entity.CartitemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartitemRepository extends JpaRepository<CartitemEntity,String> {
}
