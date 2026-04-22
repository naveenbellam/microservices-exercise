package cart_service.cart_service.Service;

import cart_service.cart_service.Entity.CartEntity;
import cart_service.cart_service.Entity.CartItemEntity;
import cart_service.cart_service.Repository.CartItemRepository;
import cart_service.cart_service.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.stream.Collectors;
import cart_service.cart_service.DTO.ProductDto;
import org.springframework.web.reactive.function.client.WebClient;
import cart_service.cart_service.DTO.CartEvent;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private WebClient webClient;

    @Autowired
    private KafkaProducerService kafkaProducerService;

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
        ProductDto product = getProductFromProductService(cartItemEntity.getProductId());

        if (product == null) {
            throw new RuntimeException("Product not found in product-service");
        }

        if (product.getStock() == null || product.getStock() < cartItemEntity.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        CartItemEntity savedItem = cartItemRepository.save(cartItemEntity);

        CartEvent event = new CartEvent(
                savedItem.getCartId(),
                savedItem.getProductId(),
                savedItem.getQuantity()
        );

        kafkaProducerService.sendCartEvent(event);

        return savedItem;
    }

    public void deleteCartItem(Integer id) {
        cartItemRepository.deleteById(id);
    }
    public CartEntity updateCart(Integer id, CartEntity cartEntity) {
        CartEntity existing = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        existing.setUserId(cartEntity.getUserId());

        return cartRepository.save(existing);
    }

    public CartItemEntity getCartItemById(Integer id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
    }

    public CartItemEntity updateCartItem(Integer id, CartItemEntity cartItemEntity) {
        CartItemEntity existing = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        existing.setCartId(cartItemEntity.getCartId());
        existing.setProductId(cartItemEntity.getProductId());
        existing.setQuantity(cartItemEntity.getQuantity());

        return cartItemRepository.save(existing);
    }
    public Page<CartEntity> getCartsPaged(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return cartRepository.findAll(pageable);
    }
    public List<String> getAllProductIds() {
        return cartItemRepository.findAll()
                .stream()
                .map(CartItemEntity::getProductId)
                .collect(Collectors.toList());
    }
    public List<CartItemEntity> getItemsWithQuantityGreaterThan(int quantity) {
        return cartItemRepository.findItemsWithQuantityGreaterThan(quantity);
    }

    public ProductDto getProductFromProductService(String productId) {
        return webClient.get()
                .uri("/{id}", productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }
    public String validateProductAndStock(String productId, int quantity) {
        ProductDto product = getProductFromProductService(productId);

        if (product == null) {
            throw new RuntimeException("Product not found in product-service");
        }

        if (product.getStock() == null || product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        return "Product exists and stock is sufficient";
    }
}