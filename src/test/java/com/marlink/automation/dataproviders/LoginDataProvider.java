package com.marlink.automation.dataproviders;

import com.marlink.automation.utils.CSVReaderUtil;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class LoginDataProvider {
    public Object[][] getLoginData(String id) {
        List<String[]> data = CSVReaderUtil.readCSV("testdata/data.csv");
        Object[][] result = new Object[data.size() - 1][4];

        for (int i = 1; i < data.size(); i++) {
            if (!data.get(i)[0].trim().equals(id)) continue;
            result[i - 1][0] = data.get(i)[0];
            result[i - 1][1] = data.get(i)[1];
            result[i - 1][2] = data.get(i)[2];
            result[i - 1][3] = data.get(i)[3];
        }
        return result;
    }

    @DataProvider(name = "loginData_01")
    public Object[][] loginData_01() {
        return getLoginData("Login_01");
    }

    @DataProvider(name = "loginData_02")
    public Object[][] loginData_02() {
        return getLoginData("Login_02");
    }




}
