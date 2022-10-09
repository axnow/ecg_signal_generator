package xyz.nowinski.ecg.model;

import lombok.Data;
import lombok.Value;

@Value
public class Heartbeat {
    double timestamp;
    HeartbeatType type;
    double rr;
}
