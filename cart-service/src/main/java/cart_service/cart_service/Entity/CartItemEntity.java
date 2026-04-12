package cart_service.cart_service.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemEntity {


    private  String id;
    @Id
    private String cartId;
    private String productId;
    private int quantity;
}
