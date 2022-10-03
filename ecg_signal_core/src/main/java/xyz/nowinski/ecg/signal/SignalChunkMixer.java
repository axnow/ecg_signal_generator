package xyz.nowinski.ecg.signal;

import static java.util.Objects.nonNull;

public class SignalChunkMixer {

    SignalPackage buffer;

    public SignalPackage addChunk(SignalPackage chunk) {
        SignalPackage res = null;
        if(nonNull(buffer)) {
            //send front of the package

        }
        //prepare new buffer
        //add chunk content

        return null;
    }

    public SignalPackage flush() {
        return null;
    }
}
