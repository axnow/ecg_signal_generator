package xyz.nowinski.ecg.signal;

import org.reactivestreams.Publisher;

public interface SignalSource {
    SignalDescription getSignalDescription();
    Publisher<SignalPackage> generateSignal();
}
