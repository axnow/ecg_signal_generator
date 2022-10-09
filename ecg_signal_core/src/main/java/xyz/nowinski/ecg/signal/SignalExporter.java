package xyz.nowinski.ecg.signal;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class SignalExporter {
    public void exportSignalPackage(SignalPackage p, SignalDescription description, File f, boolean withHeader, boolean withTimeColum, char separator) throws IOException {
        log.info("Exeporting signal package to file {}", f);
        try (Writer w = new FileWriter(f, StandardCharsets.UTF_8)) {
            exportSignalPackage(p, description, w, withHeader, withTimeColum, separator);
        }
    }

    public void exportSignalPackage(SignalPackage p, SignalDescription description, Writer w, boolean withHeader, boolean withTimeColum, char separator) throws IOException {
        log.info("Exporting signal package, {} channels, {} frames, header: {}, time column:{}",
                p.frames().length, p.frames()[0].length, withHeader, withTimeColum);
        ICSVWriter writer = new CSVWriterBuilder(w).withSeparator(separator).build();
        if (withHeader) {
            List<String> header = new ArrayList<>();
            if (withTimeColum) {
                header.add("time");
            }
            header.addAll(Arrays.asList(description.getChannelNames()));
            writer.writeNext(header.toArray(new String[0]));
        }
        for (int i = 0; i < p.frames()[0].length; i++) {
            List<String> row = new ArrayList<>();
            if (withTimeColum) {
                row.add("" + description.getSamplingFrequency().frameToSec(p.startFrame() + i));

            }
            for (int j = 0; j < description.getChannelCount(); j++) {
                row.add("" + p.frames()[j][i]);
            }
            writer.writeNext(row.toArray(new String[0]));
        }
        writer.close();
        log.debug("Export completed.");
    }
}
