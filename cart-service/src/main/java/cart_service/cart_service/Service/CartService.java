package cart_service.cart_service.Service;

import cart_service.cart_service.Entity.CartEntity;
import cart_service.cart_service.Entity.CartItemEntity;
import cart_service.cart_service.Repository.CartItemRepository;
import cart_service.cart_service.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartEntity> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<CartEntity> getCartById(Integer id) {
        return cartRepository.findById(id);
    }

    public CartEntity createCart(CartEntity cartEntity) {
        return cartRepository.save(cartEntity);
    }

    public void deleteCart(Integer id) {
        cartRepository.deleteById(id);
    }

    public List<CartItemEntity> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItemEntity createCartItem(CartItemEntity cartItemEntity) {
        return cartItemRepository.save(cartItemEntity);
    }

    public void deleteCartItem(Integer id) {
        cartItemRepository.deleteById(id);
    }
}