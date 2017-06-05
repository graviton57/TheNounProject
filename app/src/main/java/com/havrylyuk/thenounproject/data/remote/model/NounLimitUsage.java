package com.havrylyuk.thenounproject.data.remote.model;

/**
 * Object representing limit usage information returned
 * by the NounProject API.
 * Created by Igor Havrylyuk on 19.05.2017.
 */

public class NounLimitUsage {

    private int daily;
    private int hourly;
    private int monthly;

    public NounLimitUsage() {
    }

    public int getDaily() {
        return daily;
    }

    public int getHourly() {
        return hourly;
    }

    public int getMonthly() {
        return monthly;
    }

    public void setDaily(int daily) {
        this.daily = daily;
    }

    public void setHourly(int hourly) {
        this.hourly = hourly;
    }

    public void setMonthly(int monthly) {
        this.monthly = monthly;
    }
}
