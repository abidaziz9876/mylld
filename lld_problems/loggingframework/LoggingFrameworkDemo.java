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
    public static void run() {
        Logger logger = Logger.getInstance();

        // Logging with default configuration
        logger.info("This is an information message");
        logger.warning("This is a warning message");
        logger.error("This is an error message");

        // Changing log level and appender
        LoggerConfig config = new LoggerConfig(LogLevel.DEBUG, new FileAppender("app.log"));
        logger.setConfig(config);

        // List<LogAppender> appenders = new ArrayList<>();
        // appenders.add(new ConsoleAppender());
        // appenders.add(new FileAppender("logs.txt"));
        // appenders.add(new DatabaseAppender(...));

        // If you want to change the minimum loglevel then you can just do from below
        // LoggerConfig config = new LoggerConfig(LogLevel.INFO, new CompositeAppender(appenders));
        // Logger.getInstance().setConfig(config);

        logger.debug("This is a debug message");
        logger.info("This is an information message");
    }
}