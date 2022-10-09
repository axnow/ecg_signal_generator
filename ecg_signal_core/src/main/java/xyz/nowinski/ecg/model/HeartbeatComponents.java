package xyz.nowinski.ecg.model;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.util.List;

public class HeartbeatComponents {
    public static final double P_WIDTH = 0.11;
    public static final double P_HEIGHT = 0.25;

    public static final double PR_INTERVAL = 0.16; //begin of P - begin of Q
    public static final double Q_WIDTH = 0.03;
    public static final double Q_HEIGHT = -0.25;
    public static final double R_WIDTH = 0.035;
    public static final double R_HEIGHT = 2.1;
    public static final double S_WIDTH = 0.02;
    public static final double S_HEIGHT = -0.4;
    public static final double QT_DISTANCE_BASE = 0.0;//qrs start-t end
    public static final double T_WIDTH = 0.0;
    public static final double T_HEIGHT = 0.085;


    private static final double P_OFFSET = -R_WIDTH / 2.0 - PR_INTERVAL - Q_WIDTH;
    private static final double Q_OFFSET = -R_WIDTH / 2.0 - Q_WIDTH;
    private static final double R_OFFSET = -R_WIDTH / 2.0;
    private static final double S_OFFSET = R_WIDTH / 2.0;
    private static final double T_OFFSET = Q_OFFSET + QT_DISTANCE_BASE - T_WIDTH;

    private static SplineInterpolator interpolator = new SplineInterpolator();

    private static FunctionHeartbeatComponent buildBump(double width, double height, double offset) {
        PolynomialSplineFunction function = interpolator.interpolate(new double[]{0, width / 2, width},
                new double[]{0.0, height, 0.0});
        return new FunctionHeartbeatComponent(offset, width, function::value);
    }

    public static HeartbeatComponent getNBeatPWave(Heartbeat beat) {
        return buildBump(P_WIDTH, P_HEIGHT, P_OFFSET);
    }

    public static HeartbeatComponent getNBeatQRSComplex(Heartbeat beat) {
        return new CompoundHeartbeatComponent(List.of(getNBeatQWave(beat), getNBeatRWave(beat), getNBeatSWave(beat)));
    }
    
    public static HeartbeatComponent getNBeatQWave(Heartbeat beat) {
        return buildBump(Q_WIDTH, Q_HEIGHT, Q_OFFSET);
    }
    public static HeartbeatComponent getNBeatRWave(Heartbeat beat) {
        return buildBump(R_WIDTH, R_HEIGHT, R_OFFSET);
    }
    public static HeartbeatComponent getNBeatSWave(Heartbeat beat) {
        return buildBump(S_WIDTH, S_HEIGHT, S_OFFSET);
    }
    public static HeartbeatComponent getNBeatTWave(Heartbeat beat) {
        return buildBump(T_WIDTH, T_HEIGHT, T_OFFSET);
    }
    public static HeartbeatComponent getNBeat(Heartbeat beat) {
        return new CompoundHeartbeatComponent(List.of(getNBeatTWave(beat), getNBeatQRSComplex(beat),
                getNBeatTWave(beat)));
    }

    public static HeartbeatComponent getNBeatAt(Heartbeat beat, double rTime) {
        return new OffsetHeartbeatComponent(getNBeat(beat), rTime);
    }
    
    

}
