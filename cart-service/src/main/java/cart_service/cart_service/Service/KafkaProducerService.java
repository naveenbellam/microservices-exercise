package cart_service.cart_service.Service;

import cart_service.cart_service.DTO.CartEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private static final String TOPIC_NAME = "cart-topic";

    @Autowired
    private KafkaTemplate<String, CartEvent> kafkaTemplate;

    public void sendCartEvent(CartEvent event) {
        logger.info("Sending Kafka event: {}", event);
        kafkaTemplate.send(TOPIC_NAME, event);
        logger.info("Kafka event sent to topic: {}", TOPIC_NAME);
    }
}