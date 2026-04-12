package cart_service.cart_service.Controller;

import cart_service.cart_service.Entity.CartEntity;
import cart_service.cart_service.Entity.CartItemEntity;
import cart_service.cart_service.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/carts")
    List<CartEntity> getAllCarts(){
        return cartService.getAllCarts();
    }

    @GetMapping("/cartItems")
    List<CartItemEntity> getAllCartItems(){
        return cartService.getAllCartsItems();
    }

    //
    @PostMapping("/addCarts")
    void addCart(@RequestBody CartEntity cart )
    {
        cartService.addCart(cart);
    }

    @PostMapping("/addCartItems")
    void addCartItem(@RequestBody  CartItemEntity cartItem){
        cartService.addCartItems(cartItem);
    }

    //put
    @PutMapping("/updateCarts")
    void updateCart(@RequestBody CartEntity cart){
        cartService.updateCart(cart);

    }
    //put
    @PutMapping("/updateCartItems")
    void updateCartItems(@RequestBody CartItemEntity cartItemEntity){
        cartService.updateCartItems(cartItemEntity);
    }

    //delete
    @DeleteMapping("/deleteCart")
    void deleteCart(@RequestBody CartEntity cart){
        cartService.deleteCart(cart);
    }
    //delete
    @DeleteMapping("/deleteCartItems")
    void deleteCartItem(@RequestBody CartItemEntity cartItem){
        cartService.deleteCartItem(cartItem);
    }
}
