package cart_service.cart_service.Service;


import cart_service.cart_service.Entity.CartEntity;
import cart_service.cart_service.Entity.CartItemEntity;
import cart_service.cart_service.Repository.CartItemRepository;
import cart_service.cart_service.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartItemRepository cartItems;
    @Autowired
    CartRepository carts;

    // get carts
    List<CartEntity> getAllCarts(){
        return carts.findAll();
    }
    //get cart items
    List<CartItemEntity> getAllCartsItems(){
        return cartItems.findAll();
    }
    // post new cart
    void addCart(CartEntity cart){
        carts.save(cart);
    }
    //post new cart items
    void addCartItems(CartItemEntity cartItem){
        cartItems.save(cartItem);
    }
    // put cart
    void updateCart(CartEntity cart){
        List<CartEntity> carts1 = carts.findAll();
        for (CartEntity c : carts1){
            if(c.getId()==cart.getId())
            {
                c.setUserId(cart.getUserId());
            }
        }
    }
    //put cart items
    void updateCartItems(CartItemEntity cartItem){
        List<CartItemEntity> cartItems1 =cartItems.findAll();
        for(CartItemEntity it : cartItems1){
            if (it.getCartId()==cartItem.getCartId())
            {
                it.setId(cartItem.getId());
                it.setQuantity(cartItem.getQuantity());
                it.setProductId(cartItem.getProductId());
            }
        }
    }
    //delete cart
    void deleteCart(CartEntity cart) {
        carts.delete(cart);
    }
    // delete items
    void deleteCartItem(CartItemEntity cartItem){
        cartItems.delete(cartItem);
    }


}