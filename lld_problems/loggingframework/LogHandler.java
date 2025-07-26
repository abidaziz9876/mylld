package lld_problems.loggingframework;

public abstract class LogHandler {
    protected LogHandler next;
    protected LogLevel level;
    protected LogAppender appender;

    public LogHandler(LogLevel level, LogAppender appender) {
        this.level = level;
        this.appender = appender;
    }

    public void setNext(LogHandler next) {
        this.next = next;
    }

    public void handle(LogMessage message) {
        if (message.getLevel().getLevel() >= level.getLevel()) {
            appender.append(message);
            //we can write return; here to stop repeating msgs like slf4j logger stop passing to next
            // return;
        }
        if (next != null) {
            next.handle(message);
        }
    }
}