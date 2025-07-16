package lld_problems.loggingframework;

public class ConsoleAppender implements LogAppender {
    @Override
    public void append(LogMessage logMessage) {
        System.out.println(logMessage);
    }
}