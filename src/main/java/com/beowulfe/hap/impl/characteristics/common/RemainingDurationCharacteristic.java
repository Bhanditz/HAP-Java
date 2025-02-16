package com.beowulfe.hap.impl.characteristics.common;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.beowulfe.hap.HomekitCharacteristicChangeCallback;
import com.beowulfe.hap.characteristics.EventableCharacteristic;
import com.beowulfe.hap.characteristics.IntegerCharacteristic;

public class RemainingDurationCharacteristic extends IntegerCharacteristic implements EventableCharacteristic {

    private final Supplier<CompletableFuture<Integer>> getter;
    private final Consumer<HomekitCharacteristicChangeCallback> subscriber;
    private final Runnable unsubscriber;

    public RemainingDurationCharacteristic(Supplier<CompletableFuture<Integer>> getter,
            Consumer<HomekitCharacteristicChangeCallback> subscriber, Runnable unsubscriber) {
        super("000000D4-0000-1000-8000-0026BB765291", false, true, "Remaining Duration", 0, 3600, "seconds");
        this.getter = getter;
        this.subscriber = subscriber;
        this.unsubscriber = unsubscriber;
    }

    @Override
    protected CompletableFuture<Integer> getValue() {
        return getter.get();
    }

    @Override
    protected void setValue(Integer value) throws Exception {
        // Read Only
    }

    @Override
    public void subscribe(HomekitCharacteristicChangeCallback callback) {
        subscriber.accept(callback);
    }

    @Override
    public void unsubscribe() {
        unsubscriber.run();
    }
}
