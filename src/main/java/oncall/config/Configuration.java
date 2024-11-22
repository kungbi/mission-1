package oncall.config;

public enum Configuration {
    HOLIDAYS_FILE_NAME("holidays.md");

    private final Object value;

    Configuration(Object value) {
        this.value = value;
    }

    public int getIntValue() {
        return (int) value;
    }

    public String getStringValue() {
        return (String) value;
    }
}
