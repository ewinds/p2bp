package io.github.ewinds.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannel {
    String CHANNEL = "userChannel";

    @Output
    MessageChannel userChannel();
}
