package lld_problems.loggingframework;

class DebugLogger extends LogHandler {
    public DebugLogger(int level, LogAppender appender) {
        super(level, appender);
    }
     
    @Override
    protected void write(String message) {
        System.out.println("DEBUG: " + message);
    }
}
