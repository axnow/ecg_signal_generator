package xyz.nowinski.ecg.signal;


import xyz.nowinski.ecg.model.HeartRhythm;
import xyz.nowinski.ecg.model.Heartbeat;
import xyz.nowinski.ecg.model.HeartbeatType;

//rhythm->beat
public class SimpleHeartbeatGenerator {
    HeartRhythm rhythm;

    private double lastHeartbeatR = 0; //in sec


    public Heartbeat nextHeartbeat() {
        double rr = rhythm.getPulse().toRRsec();
        lastHeartbeatR += rr;
        return new Heartbeat(lastHeartbeatR, HeartbeatType.N, rr);
    }
}
