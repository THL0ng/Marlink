package com.marlink.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class BasicAuthHelper {
    private static final Logger log = LogManager.getLogger(BasicAuthHelper.class);

    public static void handleBasicAuth(String id) throws Exception {
        log.info(">>> STEP: Handling Basic Auth for ID: [{}]", id);

        // 1. Đọc dữ liệu từ CSV
        List<String[]> config = CSVReaderHelper.readCSV("testdata/data.csv");
        String username = "";
        String password = "";
        boolean isFound = false;

        // 2. Tìm kiếm credentials dựa trên ID
        for (int i = 1; i < config.size(); i++) {
            String[] row = config.get(i);
            if (row.length < 3) continue;

            if (row[0].trim().equals(id)) {
                username = row[1].trim();
                password = row[2].trim();
                isFound = true;
                break;
            }
        }

        // 3. Kiểm tra nếu không tìm thấy ID thì dừng test luôn cho đỡ tốn thời gian
        if (!isFound) {
            log.error("FAILED: Basic Auth ID [{}] not found in CSV file!", id);
            throw new RuntimeException("Data for Basic Auth ID " + id + " is missing.");
        }

        // 4. Log thông tin (Che mật khẩu để đảm bảo bảo mật)
        log.info("Auth info retrieved - User: [{}], Password: [********]", username);

        // 5. Sử dụng Robot để tương tác với Popup hệ thống (Basic Auth)
        Thread.sleep(3000); // Chờ popup ổn định
        Robot robot = new Robot();
        robot.setAutoDelay(100);

        // Nhập Username
        pasteTextWithRobot(robot, username);

        // Nhấn TAB để chuyển sang ô Password
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(500);

        // Nhập Password
        pasteTextWithRobot(robot, password);
        Thread.sleep(500);

        // Nhấn ENTER để hoàn tất
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        log.info("Basic Auth popup handled successfully.");
    }

    /**
     * Hàm phụ dùng để Copy text vào Clipboard và Paste bằng Robot (Ctrl + V)
     * Giúp code gọn hơn và tránh lặp lại logic gõ phím.
     */
    private static void pasteTextWithRobot(Robot robot, String text) {
        if (text == null || text.isEmpty()) {
            log.warn("Attempting to paste empty text with Robot!");
            return;
        }
        StringSelection selection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }
}