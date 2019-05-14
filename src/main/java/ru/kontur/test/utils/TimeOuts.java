package ru.kontur.test.utils;

import javax.annotation.Nonnull;

public class TimeOuts {
    public long pageLoadTimeout;
    public long implicitlyWait;
    public long implicitlyWaitMin;
    public long webDriverWait;
    public long sleepInMillis;


    public TimeOuts(@Nonnull PropertiesReader aConfig) {
        pageLoadTimeout = Long.valueOf(aConfig.getProperty("pageLoadTimeout"));
        implicitlyWait = Long.valueOf(aConfig.getProperty("implicitlyWait"));
        implicitlyWaitMin = Long.valueOf(aConfig.getProperty("implicitlyWaitMin"));
        webDriverWait = Long.valueOf(aConfig.getProperty("webDriverWait"));
        sleepInMillis = Long.valueOf(aConfig.getProperty("sleepInMillis"));
    }
}
