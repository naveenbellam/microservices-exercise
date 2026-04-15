package cart_service.cart_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import cart_service.cart_service.Entity.CartEntity;
import cart_service.cart_service.Entity.CartItemEntity;
import cart_service.cart_service.Service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // CART

    @PostMapping
    public CartEntity addCart(@RequestBody CartEntity cart) {
        return cartService.addCart(cart);
    }

    @GetMapping
    public List<CartEntity> getAllCarts() {
        return cartService.getAllCarts();
    }

    @PutMapping("/{id}")
    public CartEntity updateCart(@PathVariable String id,
                                 @RequestBody CartEntity cart) {
        return cartService.updateCart(id, cart);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
    }

    // CART ITEM

    @PostMapping("/item")
    public CartItemEntity addCartItem(@RequestBody CartItemEntity item) {
        return cartService.addCartItem(item);
    }

    @GetMapping("/item")
    public List<CartItemEntity> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    @PutMapping("/item/{id}")
    public CartItemEntity updateCartItem(@PathVariable String id,
                                         @RequestBody CartItemEntity item) {
        return cartService.updateCartItem(id, item);
    }

    @DeleteMapping("/item/{id}")
    public void deleteCartItem(@PathVariable String id) {
        cartService.deleteCartItem(id);
    }

    @GetMapping("/paged")
    public Page<CartEntity> getCarts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        return cartService.getCarts(page, size, sortBy);
    }

    @GetMapping("/items/paged")
    public Page<CartItemEntity> getCartItems(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        return cartService.getCartItems(page, size, sortBy);
    }

    @GetMapping("/items/filter")
    public List<CartItemEntity> getItemsByMinQuantity(
            @RequestParam int qty) {

        return cartService.getItemsByMinQuantity(qty);
    }

    @GetMapping("/items/productIds")
    public List<String> getAllProductIds() {
        return cartService.getAllProductIdsInCart();
    }
}