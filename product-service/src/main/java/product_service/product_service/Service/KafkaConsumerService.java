package product_service.product_service.Service;

import product_service.product_service.DTO.CartEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "cart-topic", groupId = "product-service-group")
    public void consumeCartEvent(CartEvent event) {
        logger.info("Received Kafka event in product-service: {}", event);
    }
}