package cart_service.cart_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import cart_service.cart_service.Entity.CartEntity;
import cart_service.cart_service.Entity.CartItemEntity;
import cart_service.cart_service.Repository.CartItemRepository;
import cart_service.cart_service.Repository.CartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // CART

    public CartEntity addCart(CartEntity cart) {
        return cartRepository.save(cart);
    }

    public List<CartEntity> getAllCarts() {
        return cartRepository.findAll();
    }

    public CartEntity updateCart(String id, CartEntity cart) {
        CartEntity existing = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        existing.setUserId(cart.getUserId());

        return cartRepository.save(existing);
    }

    public void deleteCart(String id) {
        cartRepository.deleteById(id);
    }

    // CART ITEM

    public CartItemEntity addCartItem(CartItemEntity item) {
        return cartItemRepository.save(item);
    }

    public List<CartItemEntity> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItemEntity updateCartItem(String id, CartItemEntity item) {
        CartItemEntity existing = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        existing.setCartId(item.getCartId());
        existing.setProductId(item.getProductId());
        existing.setQuantity(item.getQuantity());

        return cartItemRepository.save(existing);
    }

    public void deleteCartItem(String id) {
        cartItemRepository.deleteById(id);
    }

    public Page<CartEntity> getCarts(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return cartRepository.findAll(pageable);
    }

    public Page<CartItemEntity> getCartItems(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return cartItemRepository.findAll(pageable);
    }

    public List<CartItemEntity> getItemsByMinQuantity(int qty) {

        return cartItemRepository.findAll()
                .stream()
                .filter(item -> item.getQuantity() > qty)
                .collect(Collectors.toList());
    }

    public List<String> getAllProductIdsInCart() {

        return cartItemRepository.findAll()
                .stream()
                .map(CartItemEntity::getProductId)
                .collect(Collectors.toList());
    }
}