package com.marlink.automation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonHelper {
    // Đường dẫn trỏ đúng vào folder testdata anh đã có trong resources
    private static final String FILE_PATH = "src/test/resources/testdata/messages.json";
    private static JsonNode data;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readTree(new File(FILE_PATH));
        } catch (IOException e) {
            System.err.println("Lỗi: Không tìm thấy file JSON tại " + FILE_PATH);
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        if (data != null && data.has(key)) {
            return data.get(key).asText();
        }
        return "MISSING_KEY: " + key;
    }
}
