package xyz.nowinski.ecg.model;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

import static java.util.Collections.emptyMap;

public class FunctionHeartbeatComponent extends HeartbeatComponent {

    private double start;
    private double duration;
    private final Map<String, DoubleUnaryOperator> functions;
    private final DoubleUnaryOperator defaultFunction;


    public FunctionHeartbeatComponent(double start, double duration, DoubleUnaryOperator defaultFunction) {
        this(start, duration, emptyMap(), defaultFunction);
    }

    public FunctionHeartbeatComponent(double start, double duration, Map<String, DoubleUnaryOperator> functions, DoubleUnaryOperator defaultFunction) {
        this.start = start;
        this.duration = duration;
        this.functions = functions;
        this.defaultFunction = defaultFunction;
    }

    @Override
    public double getStart() {
        return start;
    }

    @Override
    public double getDuration() {
        return duration;
    }

    @Override
    public void render(double samplingFrequency, String channelName, double bufferStartTime, double[] buffer) {
        DoubleUnaryOperator function = functions.getOrDefault(channelName, defaultFunction);
        //find the start:
        SamplingFrequency fq = new SamplingFrequency(samplingFrequency);

        double startTime = bufferStartTime;
        int startBufferFrame = 0;

        if (start > bufferStartTime) {
            startTime = fq.frameToSec((long) Math.ceil(fq.secToFramesExact(start))); //first frame > start
            startBufferFrame = (int) Math.round(fq.secToFramesExact(startTime - bufferStartTime));
        }

        int bufferFrame = startBufferFrame;
        double time = startTime;
        while ((time - start) <= duration && bufferFrame < buffer.length) {
            buffer[bufferFrame] += function.applyAsDouble(time - start);
            bufferFrame++;
            time = bufferStartTime + fq.frameToSec(bufferFrame);
        }
    }
}
