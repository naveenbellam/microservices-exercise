package cart_service.cart_service.Controller;

import cart_service.cart_service.Entity.CartEntity;
import cart_service.cart_service.Entity.CartItemEntity;
import cart_service.cart_service.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

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
    @PutMapping("/{id}")
    public CartEntity updateCart(@PathVariable Integer id, @RequestBody CartEntity cartEntity) {
        return cartService.updateCart(id, cartEntity);
    }

    @GetMapping("/items/{id}")
    public CartItemEntity getCartItemById(@PathVariable Integer id) {
        return cartService.getCartItemById(id);
    }
    @PutMapping("/items/{id}")
    public CartItemEntity updateCartItem(@PathVariable Integer id,
                                         @RequestBody CartItemEntity cartItemEntity) {
        return cartService.updateCartItem(id, cartItemEntity);
    }

    @GetMapping("/paged")
    public Page<CartEntity> getCartsPaged(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam String sortBy) {
        return cartService.getCartsPaged(page, size, sortBy);
    }
    @GetMapping("/items/product-ids")
    public List<String> getAllProductIds() {
        return cartService.getAllProductIds();
    }
    @GetMapping("/items/filter")
    public List<CartItemEntity> getItemsWithQuantityGreaterThan(@RequestParam int quantity) {
        return cartService.getItemsWithQuantityGreaterThan(quantity);
    }

    @GetMapping("/validate")
    public String validateProductAndStock(@RequestParam String productId,
                                          @RequestParam int quantity) {
        return cartService.validateProductAndStock(productId, quantity);
    }
}