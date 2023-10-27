package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class Rq {
    private String cmd;
    private String action;
    private String queryString;
    private List<String> paramNames;
    private List<String> paramValues;

    Rq(String cmd) {
        paramNames = new ArrayList<>();
        paramValues = new ArrayList<>();

        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();
        queryString = cmdBits[1].trim();

        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            paramNames.add(paramName);
            paramValues.add(paramValue);
        }
    }

    public String getAction() {
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        int index = paramNames.indexOf(paramName);

        if (index == -1) return defaultValue;

        String paramValue = paramValues.get(index);

        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}