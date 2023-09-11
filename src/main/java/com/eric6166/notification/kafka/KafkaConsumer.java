package com.eric6166.notification.kafka;

import com.eric6166.framework.kafka.AppEvent;
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

    @KafkaListener(topics = "notificationTopic",
            groupId = "${spring.kafka.consumer.group-id:notificationId}",
            containerFactory = "kafkaListenerContainerFactory",
            concurrency = "${spring.kafka.consumer.properties.concurrency}"
    )
    public void handleNotification(AppEvent event) {
        OrderPlacedEvent placedEvent = modelMapper.map(event.getPayload(), OrderPlacedEvent.class);
        log.info("Received notification, order number: {}", placedEvent.getOrderNumber());
    }

//    @KafkaListener(topics = "internalTopic",
//            groupId = "notificationId",
//            containerFactory = "kafkaListenerContainerFactory",
//            concurrency = "${spring.kafka.consumer.properties.concurrency:1}"
//    )
//    public void handleInternalConsumer(AppEvent event) {
//        OrderPlacedEvent placedEvent = modelMapper.map(event.getPayload(), OrderPlacedEvent.class);
//        log.info("Handle from internal topic, order number: {}", placedEvent.getOrderNumber());
//    }

}
