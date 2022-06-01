package xyz.nowinski.ecg.signal;

import lombok.extern.slf4j.Slf4j;
import xyz.nowinski.ecg.signal.sample.SineSignalSource;

import java.util.function.Consumer;

@Slf4j
public class SingalTestApp {

    public static void main(String[] args) {
        log.debug("Starting app");
        SineSignalSource source = new SineSignalSource(2, 10, 100);
        source.generateSignal().subscribe(p-> {
            log.info("Got package...");
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
