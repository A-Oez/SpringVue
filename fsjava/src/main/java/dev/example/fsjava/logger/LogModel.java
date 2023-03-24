package dev.example.fsjava.logger;

import java.util.Date;

public class LogModel {
    private String datetime;
    private String message;
    private LogInbound inbound;

    public LogModel(String datetime, String message, LogInbound inbound) {
        this.datetime = datetime;
        this.message = message;
        this.inbound = inbound;
    }
}
