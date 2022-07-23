package com.marsh.zutils.entity;

import java.time.LocalDateTime;


public class DateIntervalTrendParam extends DateIntervalParam{

    private String duration;

    public DateIntervalTrendParam() {

    }

    public DateIntervalTrendParam(String duration) {
        this.duration = duration;
    }

    public DateIntervalTrendParam(LocalDateTime startTime, LocalDateTime endTime, String duration) {
        super(startTime, endTime);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
