package xyz.nowinski.ecg.signal;

import lombok.Value;
import xyz.nowinski.ecg.model.SamplingFrequency;

@Value
public class SignalDescription {
    String name;
    double frequency;
    String[] channelNames;

    SamplingFrequency samplingFrequency;

    public SignalDescription(String name, double frequency, String[] channelNames) {
        this.name = name;
        this.frequency = frequency;
        this.channelNames = channelNames;
        this.samplingFrequency = new SamplingFrequency(frequency);
    }

    public int getChannelCount() {
        return channelNames.length;
    }
}
