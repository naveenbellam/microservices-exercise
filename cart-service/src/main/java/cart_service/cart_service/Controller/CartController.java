package cart_service.cart_service.Controller;

import cart_service.cart_service.Entity.CartEntity;
import cart_service.cart_service.Entity.CartItemEntity;
import cart_service.cart_service.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<CartEntity> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public CartEntity getCartById(@PathVariable Integer id) {
        return cartService.getCartById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @PostMapping
    public CartEntity createCart(@RequestBody CartEntity cartEntity) {
        return cartService.createCart(cartEntity);
    }

    @DeleteMapping("/{id}")
    public String deleteCart(@PathVariable Integer id) {
        cartService.deleteCart(id);
        return "Cart deleted successfully";
    }

    @GetMapping("/items")
    public List<CartItemEntity> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    @PostMapping("/items")
    public CartItemEntity createCartItem(@RequestBody CartItemEntity cartItemEntity) {
        return cartService.createCartItem(cartItemEntity);
    }

    @DeleteMapping("/items/{id}")
    public String deleteCartItem(@PathVariable Integer id) {
        cartService.deleteCartItem(id);
        return "Cart item deleted successfully";
    }
}