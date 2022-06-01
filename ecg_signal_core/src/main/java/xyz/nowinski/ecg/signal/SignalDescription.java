package xyz.nowinski.ecg.signal;

import lombok.Value;

@Value
public class SignalDescription {
    String name;
    double frequency;
    String[] channelNames;

    public int getChannelCount() {
        return channelNames.length;
    }
}
