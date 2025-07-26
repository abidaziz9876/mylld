package lld_problems.loggingframework;

public enum LogLevel {
    DEBUG(1), INFO(2), ERROR(3);

    private int level;

    LogLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

