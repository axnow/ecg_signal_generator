package xyz.nowinski.ecg.model;


import xyz.nowinski.ecg.signal.SignalDescription;
import xyz.nowinski.ecg.signal.SignalPackage;

public abstract class HeartbeatComponent {
    public abstract double getStart();

    public abstract double getDuration();

    public double getEnd() {
        return getStart()+getDuration();
    }


    public abstract void render(double samplingFrequency, String channelName, double bufferStartTime, double[] buffer);

    public void render(SignalDescription signalDescription, SignalPackage signalPackage) {
        SamplingFrequency sampling = new SamplingFrequency(signalDescription.getFrequency());
        double startFrameSec = sampling.frameToSec(signalPackage.startFrame());
        for (int i = 0; i < signalDescription.getChannelCount(); i++) {
            render(signalDescription.getFrequency(), signalDescription.getChannelNames()[i], startFrameSec, signalPackage.frames()[i]);
        }
    }

    public SignalPackage render(SignalDescription signalDescription) {
        SamplingFrequency frequency = signalDescription.getSamplingFrequency();
        SignalPackage res = new SignalPackage(getStartFrame(frequency), new double[signalDescription.getChannelCount()][
                getEndFrame(frequency) -
                        getStartFrame(frequency)]);
        render(signalDescription, res);
        return res;
    }

    public double[] render(double samplingFrequency, String channelName) {
        SamplingFrequency frequency = new SamplingFrequency(samplingFrequency);
        int startFrame = getStartFrame(frequency);
        int endFrame = getEndFrame(frequency);
        double[] buffer = new double[endFrame - startFrame + 1];
        render(samplingFrequency, channelName, frequency.frameToSec(startFrame), buffer);
        return buffer;
    }

    private int getEndFrame(SamplingFrequency frequency) {
        return (int) Math.ceil(frequency.secToFramesExact(getStart() + getDuration()));
    }

    private int getStartFrame(SamplingFrequency frequency) {
        return (int) Math.floor(frequency.secToFramesExact(getStart()));
    }


}
