package xyz.nowinski.ecg.signal;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import xyz.nowinski.ecg.signal.sample.SineSignalSource;

import java.time.Duration;
import java.util.function.Consumer;

@Slf4j
public class SingalTestApp {

    public static void main(String[] args) {
        log.debug("Starting app");
        SineSignalSource source = new SineSignalSource(2, 10, 100);
        Flux.from(source.generateSignal())
                .delayElements(Duration.ofSeconds(1))
//                .log()
                .subscribe(p -> log.info("Got package for frame {}.", p.getStartFrame()));
//                .subscribe(p -> log.info("Got package for frame {}.", p.getStartFrame()),
//                        e->log.warn("Got an error: {}", e),
//                        ()->log.info("Stream completed"),
//                        s->s.request(10));

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
