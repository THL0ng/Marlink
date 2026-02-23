package com.marlink.automation.dataproviders;

import com.marlink.automation.utils.CSVReaderUtil;
import org.testng.annotations.DataProvider;

import java.util.List;

public class LoginDataProvider {
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {

        // đọc csv từ resources/testdata/data.csv
        List<String[]> data = CSVReaderUtil.readCSV("testdata/data.csv");

        // bỏ header => size - 1
        Object[][] result = new Object[data.size() - 1][4];

        for (int i = 1; i < data.size(); i++) {
            result[i - 1][0] = data.get(i)[0]; // id
            result[i - 1][1] = data.get(i)[1]; // email
            result[i - 1][2] = data.get(i)[2]; // password
            result[i - 1][3] = data.get(i)[3]; // expectedResult
        }

        return result;
    }

}
