package com.marlink.automation.dataproviders;

import com.marlink.automation.utils.CSVReaderHelper;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class LoginDataProvider {
    public Object[][] getLoginData(String id) {
        List<String[]> data = CSVReaderHelper.readCSV("testdata/data.csv");
        List<Object[]> matchedRows = new ArrayList<>();

        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);

            if (row == null || row.length < 4) {
                continue;
            }

            String rowId = row[0] != null ? row[0].trim() : "";
            if (!rowId.equals(id)) {
                continue;
            }

            matchedRows.add(new Object[]{
                    rowId,
                    row[1] != null ? row[1].trim() : "",
                    row[2] != null ? row[2].trim() : "",
                    row[3] != null ? row[3].trim() : ""
            });
        }

        if (matchedRows.isEmpty()) {
            throw new IllegalArgumentException("No test data found for id: " + id);
        }

        return matchedRows.toArray(new Object[0][]);
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
