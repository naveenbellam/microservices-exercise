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
    public List<CartEntity> getAllCarts(){
        return carts.findAll();
    }
    //get cart items
    public  List<CartItemEntity> getAllCartsItems(){
        return cartItems.findAll();
    }
    // post new cart
    public void addCart(CartEntity cart){
        carts.save(cart);
    }
    //post new cart items
    public void addCartItems(CartItemEntity cartItem){
        cartItems.save(cartItem);
    }
    // put cart
    public  void updateCart(CartEntity cart){
        CartEntity cart1 = carts.findById(cart.getUserId()).orElse(null);
        if(cart1!=null){
            carts.delete(cart1);
            carts.save(cart);
        }
    }
    //put cart items
    public void updateCartItems(CartItemEntity cartItem){
        CartItemEntity cartItem1=cartItems.findById(cartItem.getProductId()).orElse(null);
        if(cartItem1!=null)
        {
            cartItems.delete(cartItem1);
            cartItems.save(cartItem);
        }
    }
    //delete cart
    public  void deleteCart(CartEntity cart) {
        carts.delete(cart);
    }
    // delete items
    public void deleteCartItem(CartItemEntity cartItem){
        cartItems.delete(cartItem);
    }


}