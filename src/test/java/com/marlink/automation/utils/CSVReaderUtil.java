package com.marlink.automation.utils;

import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CSVReaderUtil {

    public static List<String[]> readCSV(String fileName) {
        try {
            InputStream is = CSVReaderUtil.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);
            if (is == null) {
                throw new RuntimeException("File not found: " + fileName);
            }
            CSVReader reader = new CSVReader(new InputStreamReader(is));
            return reader.readAll();
        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV", e);
        }
    }
}