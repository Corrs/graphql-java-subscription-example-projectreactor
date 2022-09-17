package com.graphql.example.http.data;


import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestPublisher {
    private final Flux<Integer> publisher;

    public TestPublisher() {
        publisher = Flux.create(emitter -> {

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(newStockTick(emitter), 0, 2, TimeUnit.SECONDS);

        });
    }

    private Runnable newStockTick(FluxSink<Integer> emitter) {
        return () -> {
            emitter.next(1);
        };
    }

    public Flux<Integer> getPublisher() {
        return publisher;
    }
}
