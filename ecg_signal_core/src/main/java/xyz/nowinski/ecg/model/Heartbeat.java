package xyz.nowinski.ecg.model;

import lombok.Data;

@Data
public class Heartbeat {
    HeartbeatType type;
    long rTimestamp;

}
