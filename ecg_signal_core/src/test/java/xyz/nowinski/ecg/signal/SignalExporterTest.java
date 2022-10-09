package xyz.nowinski.ecg.signal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SignalExporterTest {
    @Test
    void testWriterExport() throws IOException {
        StringWriter w = new StringWriter();
        SignalPackage p = new SignalPackage(10, new double[][]{{0,0,0,0},{1,2,3,4}});
        SignalDescription d = new SignalDescription("test", 10, new String[]{"c1", "c2"});
        SignalExporter exporter = new SignalExporter();
        exporter.exportSignalPackage(p, d, w, true, true, ',');
        String res = w.toString();
        log.debug("Export result:\n{}", res);
        String[] rows = res.split("\n");
        assertEquals(rows.length, p.getFrameCount());
    }
}