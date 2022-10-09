package xyz.nowinski.ecg.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PulseRate {

    @Getter
    private final double beatsPerMinute;

    public double toRRMsec() {
        return 60. * 1000 / beatsPerMinute;
    }
    public double toRRsec() {
        return 60.  / beatsPerMinute;
    }

    public double toBeatsPerSecond() {
        return beatsPerMinute / 60.;
    }

}
