package com.marlink.automation.utils;

import java.util.Random;

public class RandomData {

    public static int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(99999);
    }


    public static String email = "Long" + getRandomNumber() + "@gmail.com";
    public static String lastName = "Test_Marlink" + getRandomNumber();
    public static String firstName = "Long" + getRandomNumber();
    public static String password = "Quenpassroi123";
    public static String phoneNumber = "123456";
    public static String streetAddress = "HCM 16A";
    public static String city = "SaiGon";
    public static String zipCode = "700000";







}
