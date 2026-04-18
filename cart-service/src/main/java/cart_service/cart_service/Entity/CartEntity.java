package cart_service.cart_service.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
}