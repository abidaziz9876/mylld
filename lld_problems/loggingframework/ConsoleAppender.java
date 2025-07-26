package lld_problems.loggingframework;

public class ConsoleAppender implements LogAppender {
    @Override
    public void append(LogMessage message) {
        System.out.println("[" + message.getTimestamp() + "] [" + message.getLevel() + "] " + message.getMessage());
    }
}