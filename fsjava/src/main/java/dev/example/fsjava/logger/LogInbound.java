package dev.example.fsjava.logger;

public class LogInbound {
    private String httpMethod;
    private String httpBody;
    private String httpQueries;
    private String httpURI;


    public LogInbound(String httpMethod, String httpBody, String httpQueries, String httpURI) {
        this.httpMethod = httpMethod;
        this.httpBody = httpBody;
        this.httpQueries = httpQueries;
        this.httpURI = httpURI;
    }
}
