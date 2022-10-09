package xyz.nowinski.ecg.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FunctionHeartbeatComponentTest {

    @Test
    void render() {
        FunctionHeartbeatComponent instance = new FunctionHeartbeatComponent(0, 1, x->x);
        double freq=10.0;
        double[] buffer = new double[21];

        instance.render(freq, "nothing", -.5, buffer);
        IntStream.range(0, 5).forEach(i->assertEquals(0.0, buffer[i]));
        IntStream.range(5, 16).forEach(i->assertEquals(0.1*(i-5), buffer[i], 0.001));
        IntStream.range(17, 21).forEach(i->assertEquals(0.0, buffer[i]));

        Arrays.fill(buffer, 0);
        instance.render(freq, "nothing", .5, buffer);
        IntStream.range(0,5).forEach(i->assertEquals(0.1*(i+5), buffer[i], 0.001));
        IntStream.range(6, 21).forEach(i->assertEquals(0.0, buffer[i]));

    }
}