public enum Note {
    FIVE_HUNDRED(500), TWO_HUNDRED(200), ONE_HUNDRED(100);

    private final int value;

    Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
