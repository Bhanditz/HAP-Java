package com.beowulfe.hap.accessories;

import com.beowulfe.hap.HomekitAccessory;
import com.beowulfe.hap.HomekitCharacteristicChangeCallback;
import com.beowulfe.hap.Service;
import com.beowulfe.hap.impl.services.MotionSensorService;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

/**
 * <p>A motion sensor that reports whether motion has been detected.</p>
 *
 * <p>Motion sensors that run on batteries will need to implement this interface
 * and also implement {@link BatteryStatusAccessory}.</p>
 *
 * @author Gaston Dombiak
 */
public interface MotionSensor extends HomekitAccessory {

    /**
     * Retrieves the state of the motion sensor. If true then motion has been detected.
     *
     * @return a future that will contain the motion sensor's state
     */
    CompletableFuture<Boolean> getMotionDetected();

    @Override
    default Collection<Service> getServices() {
        return Collections.singleton(new MotionSensorService(this));
    }

    /**
     * Subscribes to changes in the motion sensor.
     *
     * @param callback the function to call when the state changes.
     */
    void subscribeMotionDetected(HomekitCharacteristicChangeCallback callback);

    /**
     * Unsubscribes from changes in the motion sensor.
     */
    void unsubscribeMotionDetected();
}
