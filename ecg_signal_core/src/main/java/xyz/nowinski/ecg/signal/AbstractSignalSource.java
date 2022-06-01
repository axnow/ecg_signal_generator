package xyz.nowinski.ecg.signal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractSignalSource implements SignalSource{
    @Getter
    final SignalDescription signalDescription;

}
