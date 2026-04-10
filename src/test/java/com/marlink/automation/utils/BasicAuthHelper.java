package com.marlink.automation.utils;

import java.util.List;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class BasicAuthHelper {
    public static void handleBasicAuth(String id) throws Exception {
        System.out.println("ID truyền vào: [" + id + "]"); // thêm dòng này

        List<String[]> config = CSVReaderHelper.readCSV("testdata/data.csv");

        String username = "";
        String password = "";

        for (int i = 1; i < config.size(); i++) {
            if (config.get(i).length < 3) continue; // bỏ qua dòng trống hoặc thiếu cột
            if (config.get(i)[0].trim().equals(id)) {
                username = config.get(i)[1].trim();
                password = config.get(i)[2].trim();
                break;
            }
        }

        System.out.println("username: [" + username + "]"); // thêm dòng này
        System.out.println("password: [" + password + "]"); // thêm dòng này



        for (int i = 1; i < config.size(); i++) {
            if (config.get(i).length < 3) continue;
            System.out.println("row " + i + ": [" + config.get(i)[0] + "] [" + config.get(i)[1] + "] [" + config.get(i)[2] + "]");
        }

        Thread.sleep(3000);
        Robot robot = new Robot();
        robot.setAutoDelay(100);

        StringSelection usernameSelection = new StringSelection(username);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(usernameSelection, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(300);

        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(300);

        StringSelection passwordSelection = new StringSelection(password);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(passwordSelection, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(300);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

}
