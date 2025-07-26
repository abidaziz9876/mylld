package lld_problems.loggingframework;

public class ErrorLogHandler extends LogHandler {
    public ErrorLogHandler(LogAppender appender) {
        super(LogLevel.ERROR, appender);
    }
}
