package product_service.product_service.Service;

import product_service.product_service.DTO.CartEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "cart-topic", groupId = "product-service-group")
    public void consumeCartEvent(CartEvent event) {
        System.out.println("Received Kafka event in product-service: " + event);
    }
}