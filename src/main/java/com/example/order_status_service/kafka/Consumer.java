package com.example.order_status_service.kafka;
import com.example.order_status_service.etntity.OrderEvent;
import com.example.order_status_service.etntity.OrderStatusEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
@Slf4j
public class Consumer {
    private static final String ORDER_STATUS_TOPIC = "order-status-topic";

    @Autowired
    private KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;

    @KafkaListener(topics = "order-topic", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<String, OrderEvent> record) {
        log.info("Received order event: {}", record.value());
        OrderStatusEvent orderStatusEvent = new OrderStatusEvent("CREATED", Instant.now());
        kafkaTemplate.send(ORDER_STATUS_TOPIC, orderStatusEvent);
    }
}
