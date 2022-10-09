package xyz.nowinski.ecg.model;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Simple wrapper over the component, which shifts it in time to appropriate location.
 * Original component is rendered as shifted in time by {@code offset} seconds;
 */
@AllArgsConstructor
@Slf4j
public class OffsetHeartbeatComponent extends HeartbeatComponent {
    private final HeartbeatComponent sourceComponent;
    private final double offset;

    @Override
    public double getStart() {
        return sourceComponent.getStart() + offset;
    }

    @Override
    public double getDuration() {
        return sourceComponent.getDuration();
    }

    @Override
    public void render(double samplingFrequency, String channelName, double bufferStartTime, double[] buffer) {
        sourceComponent.render(samplingFrequency, channelName, bufferStartTime - offset, buffer);
    }
}
