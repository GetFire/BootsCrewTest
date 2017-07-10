package com.botscrewtest;

import com.botscrewtest.utils.ConnectionUtil;
import com.botscrewtest.utils.Injector;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static BufferedReader getReader() {
        return reader;
    }

    public static void main(String[] args) throws Exception {
        Injector.getConsoleMain().consoleMain();
        reader.close();
        ConnectionUtil.closeConnection();

    }
}
