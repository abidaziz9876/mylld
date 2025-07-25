package lld_problems.loggingframework;

/*
1-The logging framework should support different log levels, such as DEBUG, INFO, WARNING, ERROR, and FATAL.
2-It should allow logging messages with a timestamp, log level, and message content.
3-The framework should support multiple output destinations, such as console, file, and database.
4-It should provide a configuration mechanism to set the log level and output destination.
5-The logging framework should be thread-safe to handle concurrent logging from multiple threads.
6-It should be extensible to accommodate new log levels and output destinations in the future.
*/

public class LoggingFrameworkDemo {
    private static LogHandler getChainOfLoggers(LogAppender appender) {
        LogHandler errorLogger = new ErrorLogger(LogHandler.ERROR, appender);
        LogHandler debugLogger = new DebugLogger(LogHandler.DEBUG, appender);
        LogHandler infoLogger = new InfoLogger(LogHandler.INFO, appender);
        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);
        return infoLogger;
    }
    public static void main() {
        

        // Logging with default configuration
        LogAppender consoleAppender = new ConsoleAppender();
        LogAppender fileAppender = new FileAppender("logs.txt");
        // Create the chain of loggers with the console appender
        LogHandler loggerChain = getChainOfLoggers(consoleAppender);
        System.out.println("Logging INFO level message:");
        loggerChain.logMessage(LogHandler.INFO, "This is an information.");
        System.out.println("nLogging DEBUG level message:");
        loggerChain.logMessage(LogHandler.DEBUG, "This is a debug level information.");
        System.out.println("nLogging ERROR level message:");
        loggerChain.logMessage(LogHandler.ERROR, "This is an error information.");

        // Demonstrate the singleton Logger usage as an alternative
        System.out.println("nUsing Singleton Logger:");
        Logger logger = Logger.getInstance(LogLevel.INFO, consoleAppender);
        logger.setConfig(new LoggerConfig(LogLevel.INFO, fileAppender));
        logger.error("Using singleton Logger - Error message");
    }
}