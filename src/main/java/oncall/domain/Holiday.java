package oncall.domain;

public class Holiday {
    private final int month;
    private final int day;

    public Holiday(int month, int day) {
        validate(month, day);
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    private void validate(int month, int day) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
    }
}
