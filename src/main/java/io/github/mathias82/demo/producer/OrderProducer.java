package io.github.mathias82.demo.producer;

import io.github.mathias82.demo.model.OrderEvent;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    private static final String TOPIC = "order-events";

    private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, SpecificRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OrderEvent request) {

        io.github.mathias82.avro.OrderEvent avro =
                io.github.mathias82.avro.OrderEvent.newBuilder()
                        .setOrderId(request.orderId())
                        .setAmount(request.amount())
                        .setCreatedAt(
                                request.createdAt() != null
                                        ? request.createdAt()
                                        : ""
                        )
                        .build();

        kafkaTemplate.send(TOPIC, avro);
    }
}