package com.l7bug.factorykit;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Factory Kit 模式单元测试
 */
class FactoryKitTest {

    @Test
    void createRegisteredMessageTypes() {
        MessageFactory factory = MessageFactory.factory(builder -> {
            builder.add("EMAIL", EmailMessage::new);
            builder.add("SMS", SmsMessage::new);
            builder.add("PUSH", PushMessage::new);
        });

        Message email = factory.create("EMAIL");
        Message sms = factory.create("SMS");
        Message push = factory.create("PUSH");

        assertNotNull(email);
        assertNotNull(sms);
        assertNotNull(push);

        assertInstanceOf(EmailMessage.class, email);
        assertInstanceOf(SmsMessage.class, sms);
        assertInstanceOf(PushMessage.class, push);

        assertEquals("EMAIL", email.getType());
        assertEquals("SMS", sms.getType());
        assertEquals("PUSH", push.getType());
    }

    @Test
    void createCustomMessageType() {
        MessageFactory factory = MessageFactory.factory(builder -> {
            builder.add("WEBHOOK", WebhookMessage::new);
        });

        Message webhook = factory.create("WEBHOOK");

        assertNotNull(webhook);
        assertInstanceOf(WebhookMessage.class, webhook);
        assertEquals("WEBHOOK", webhook.getType());
    }

    @Test
    void createMultipleInstances() {
        MessageFactory factory = MessageFactory.factory(builder -> {
            builder.add("EMAIL", EmailMessage::new);
        });

        // 每次创建都是新实例
        Message msg1 = factory.create("EMAIL");
        Message msg2 = factory.create("EMAIL");

        assertNotSame(msg1, msg2);
    }

    @Test
    void unregisteredTypeThrowsException() {
        MessageFactory factory = MessageFactory.factory(builder -> {
            builder.add("EMAIL", EmailMessage::new);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            factory.create("UNKNOWN");
        });
    }

    @Test
    void factoryWithMultipleRegistrations() {
        MessageFactory factory = MessageFactory.factory(builder -> {
            builder.add("EMAIL", EmailMessage::new);
            builder.add("SMS", SmsMessage::new);
        });

        List<String> types = List.of("EMAIL", "SMS");
        for (String type : types) {
            Message message = factory.create(type);
            assertNotNull(message);
            assertEquals(type, message.getType());
        }
    }
}
