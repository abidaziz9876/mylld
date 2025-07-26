package lld_problems.loggingframework;

public class DebugLogHandler extends LogHandler {
    public DebugLogHandler(LogAppender appender) {
        super(LogLevel.DEBUG, appender);
    }
}
