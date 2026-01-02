package io.github.mathias82.demo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class OrderConsumer {

    private final List<io.github.mathias82.demo.model.OrderEvent> receivedEvents = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "order-events", groupId = "demo-consumer")
    public void onMessage(io.github.mathias82.avro.OrderEvent avro) {

        // mapping Avro → DTO
        io.github.mathias82.demo.model.OrderEvent dto =
                new io.github.mathias82.demo.model.OrderEvent(
                        avro.getOrderId().toString(),
                        avro.getAmount(),
                        avro.getCreatedAt().toString()
                );

        receivedEvents.add(dto);
    }

    public List<io.github.mathias82.demo.model.OrderEvent> getEvents() {
        return receivedEvents;
    }

    public void clear() {
        receivedEvents.clear();
    }
}
