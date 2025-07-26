package lld_problems.loggingframework;

public class InfoLogHandler extends LogHandler {
  public InfoLogHandler(LogAppender appender) {
      super(LogLevel.INFO, appender);
  }
}