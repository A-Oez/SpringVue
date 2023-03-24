package dev.example.fsjava.logger;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);


    public static void logger(LogInbound inbound, String message){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);

        LogModel model = new LogModel(formattedDate,
                                      message,
                                      inbound);

        logger.info(new Gson().toJson(model));
    }

    public static LogInbound createInbound(String body ,HttpServletRequest request){
        LogInbound inbound = new LogInbound(request.getMethod(),
                                            body,
                                            request.getQueryString(),
                                            request.getRequestURI());
        return inbound;
    }
}
