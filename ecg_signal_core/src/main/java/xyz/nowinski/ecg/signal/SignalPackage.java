package xyz.nowinski.ecg.signal;

import lombok.Value;

@Value
public class SignalPackage {
    long startFrame;
    double[][] frames;


}
