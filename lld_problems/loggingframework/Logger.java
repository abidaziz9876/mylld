package lld_problems.loggingframework;

import java.util.concurrent.ConcurrentHashMap;

public class Logger {
    private static Logger instance;

    private LogLevel minLogLevel;
    private LogAppender appender;
    private LogHandler chain;

    private Logger() {
        this.minLogLevel = LogLevel.DEBUG;
        this.appender = new ConsoleAppender();
        setupChain();
    }

    private void setupChain() {
        LogHandler debugHandler = new DebugLogHandler(appender);
        LogHandler infoHandler = new InfoLogHandler(appender);
        LogHandler errorHandler = new ErrorLogHandler(appender);

        debugHandler.setNext(infoHandler);
        infoHandler.setNext(errorHandler);

        this.chain = debugHandler;
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void setMinLogLevel(LogLevel level) {
        this.minLogLevel = level;
    }

    public void setLogAppender(LogAppender newAppender) {
        this.appender = newAppender;
        setupChain();
    }

    public void log(LogLevel level, String message) {
        if (level.getLevel() >= minLogLevel.getLevel()) {
            chain.handle(new LogMessage(level, message));
        }
    }
}