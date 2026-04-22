package cart_service.cart_service.Service;

import cart_service.cart_service.DTO.CartEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC_NAME = "cart-topic";

    @Autowired
    private KafkaTemplate<String, CartEvent> kafkaTemplate;


    public void sendCartEvent(CartEvent event) {
        System.out.println("About to send event: " + event);
        kafkaTemplate.send(TOPIC_NAME, event);
        System.out.println("Send method called");
    }
}
