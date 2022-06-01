package xyz.nowinski.ecg.signal.sample;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import xyz.nowinski.ecg.signal.AbstractSignalSource;
import xyz.nowinski.ecg.signal.SignalDescription;
import xyz.nowinski.ecg.signal.SignalPackage;

@Slf4j
public class SineSignalSource extends AbstractSignalSource {
    private final double amplitude;
    private final double frequency;
    private final double samplingFrequency;

    private final int packageSize = 1000;

    public SineSignalSource(double amplitude, double frequency, double samplingFrequency) {
        super(new SignalDescription(String.format("Sample sine A=%f f=%fHz @%fHz", amplitude, frequency, samplingFrequency), samplingFrequency, new String[]{
                "signal"}));
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.samplingFrequency = samplingFrequency;
    }

    @Override
    public Flux<SignalPackage> generateSignal() {
        return Flux.generate(
                () -> 0,
                (packageNumber, sink) -> {
                    log.info("Generating sine package {}", packageNumber);
                    double[] sample = new double[packageSize];
                    for (int i = 0; i < packageSize; i++) {
                        sample[i] = value(packageNumber * packageSize + i);
                    }
                    sink.next(new SignalPackage(packageSize * packageNumber, new double[][]{sample}));
                    return packageNumber + 1;
                });
    }

    double value(int frame) {
        double t = frame / samplingFrequency;
        double v = Math.sin(t / (2 * Math.PI * frequency));
        return amplitude * v;
    }
}
