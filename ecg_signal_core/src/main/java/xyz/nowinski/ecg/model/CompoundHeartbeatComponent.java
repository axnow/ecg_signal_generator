package xyz.nowinski.ecg.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CompoundHeartbeatComponent extends HeartbeatComponent {
    private final List<HeartbeatComponent> components;
    @Getter
    private final double start;
    @Getter
    private final double duration;

    public CompoundHeartbeatComponent(List<HeartbeatComponent> components) {
        this.components = new ArrayList<>(components);
        start = components.stream().mapToDouble(HeartbeatComponent::getStart).min().orElseThrow();
        duration = components.stream().mapToDouble(HeartbeatComponent::getEnd).max().orElseThrow() - start;
    }


    @Override
    public void render(double samplingFrequency, String channelName, double bufferStartTime, double[] buffer) {
        components.forEach(c->c.render(samplingFrequency, channelName, bufferStartTime, buffer));
    }
}
