
import java.util.List;

import loggingframework.LogMessage;

public class CompositeAppender implements LogAppender {
    private List<LogAppender> appenders;

    public CompositeAppender(List<LogAppender> appenders) {
        this.appenders = appenders;
    }

   
    @Override
    public void append(LogMessage logMessage) {
        for (LogAppender appender : appenders) {
            appender.append(logMessage);
        } 
    }
}

