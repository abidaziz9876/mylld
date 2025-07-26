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
    
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.log(LogLevel.DEBUG, "Debug log - console");
        logger.log(LogLevel.ERROR, "Error log - console");

        logger.setMinLogLevel(LogLevel.INFO);
        logger.setLogAppender(new FileAppender("logs.txt"));

        logger.log(LogLevel.DEBUG, "This will be ignored");
        logger.log(LogLevel.INFO, "This will go to file");
        logger.log(LogLevel.ERROR, "This too will go to file");
    }
}