package com.eric6166.notification.kafka;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ConditionalOnProperty(prefix = "spring.kafka", name = "enabled")
public class KafkaConsumerProperties {

    @Value("${spring.kafka.consumers.internal-consumer.group-id}")
    String internalGroupId;

    @Value("${spring.kafka.consumers.notification-consumer.group-id}")
    String notificationGroupId;


}
