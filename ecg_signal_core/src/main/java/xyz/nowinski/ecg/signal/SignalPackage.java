package xyz.nowinski.ecg.signal;

import lombok.Value;

public record SignalPackage ( long startFrame,double[][] frames) {

    public int getFrameCount() {
        if (frames.length > 0) {
            return frames[0].length;
        } else {
            return 0;
        }
    }
}
