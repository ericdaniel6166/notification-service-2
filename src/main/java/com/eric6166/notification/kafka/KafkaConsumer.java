package com.eric6166.notification.kafka;

import com.eric6166.framework.kafka.Event;
import com.eric6166.notification.event.OrderPlacedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class KafkaConsumer {

    final ModelMapper modelMapper;

    @KafkaListener(topics = "${spring.kafka.consumers.notification-consumer.topic-name}",
            groupId = "${spring.kafka.consumers.notification-consumer.group-id}",
            containerFactory = "notificationKafkaListenerContainerFactory",
            concurrency = "${spring.kafka.consumers.notification-consumer.properties.concurrency}"
    )
    public void handleNotification(Event event) {
        OrderPlacedEvent placedEvent = modelMapper.map(event.getPayload(), OrderPlacedEvent.class);
        log.info("Received notification, order number: {}", placedEvent.getOrderNumber());
    }

    @KafkaListener(topics = "${spring.kafka.consumers.internal-consumer.topic-name}",
            groupId = "${spring.kafka.consumers.internal-consumer.group-id}",
            containerFactory = "internalKafkaListenerContainerFactory",
            concurrency = "${spring.kafka.consumers.internal-consumer.properties.concurrency}"
    )
    public void handleInternalConsumer(Event event) {
        OrderPlacedEvent placedEvent = modelMapper.map(event.getPayload(), OrderPlacedEvent.class);
        log.info("Handle from internal topic, order number: {}", placedEvent.getOrderNumber());
    }

}
