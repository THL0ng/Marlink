package com.marlink.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class BasicAuthHelper {
    private static final Logger log = LogManager.getLogger(BasicAuthHelper.class);

    /**
     * Lấy chuỗi credentials định dạng user:password từ file CSV
     */
    public static String getBasicAuthCredential(String id) {
        log.info(">>> STEP: Getting Basic Auth credentials for ID: [{}]", id);

        List<String[]> config = CSVReaderHelper.readCSV("testdata/data.csv");

        for (int i = 1; i < config.size(); i++) {
            String[] row = config.get(i);
            if (row.length < 3) continue;

            if (row[0].trim().equals(id)) {
                String user = row[1].trim();
                String pass = row[2].trim();
                log.info("Found credentials for User: [{}]", user);
                return user + ":" + pass;
            }
        }

        log.error("FAILED: Basic Auth ID [{}] not found in CSV!", id);
        return null;
    }
}