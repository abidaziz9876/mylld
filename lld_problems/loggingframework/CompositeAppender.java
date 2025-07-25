package lld_problems.loggingframework;
import java.util.List;


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

