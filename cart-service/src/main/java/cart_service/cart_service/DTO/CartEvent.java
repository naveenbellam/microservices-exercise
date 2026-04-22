package cart_service.cart_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEvent {
    private Integer cartId;
    private String productId;
    private Integer quantity;
}
