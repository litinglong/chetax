package com.silva.chetax.spring.life.test.demo.event;

import org.springframework.context.ApplicationEvent;

import lombok.Data;

@Data
public class DemoEvent extends ApplicationEvent {
    private Long id;
    private String message;

    public DemoEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }
}
