package com.patterns.processor;

import com.patterns.model.Message;

public class ProcessorUpperField10 implements Processor {

    @Override
    public Message process(Message message) {
        return message.toBuilder().field5(message.getField10().toUpperCase()).build();
    }
}
