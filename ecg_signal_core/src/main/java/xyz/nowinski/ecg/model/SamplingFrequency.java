package xyz.nowinski.ecg.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SamplingFrequency {
    @Getter
    private final double samplingHz;

    public double secToFramesExact(double time) {
        return time*samplingHz;
    }

    public double frameToSec(long frame) {
        return frame/samplingHz;
    }

}
