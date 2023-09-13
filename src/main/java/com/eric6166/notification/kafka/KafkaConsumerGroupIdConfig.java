package com.eric6166.notification.kafka;

import com.eric6166.framework.kafka.KafkaConsumerConfig;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaConsumerGroupIdConfig {

    final KafkaConsumerConfig kafkaConsumerConfig;

    final KafkaConsumerProperties kafkaConsumerProperties;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> notificationKafkaListenerContainerFactory() {
        return kafkaConsumerConfig.kafkaListenerContainerFactory(kafkaConsumerProperties.getNotificationGroupId());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> internalKafkaListenerContainerFactory() {
        return kafkaConsumerConfig.kafkaListenerContainerFactory(kafkaConsumerProperties.getInternalGroupId());
    }


}
