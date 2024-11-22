package oncall.enums;

public enum DayOfWeek {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private final String korean;

    DayOfWeek(String korean) {
        this.korean = korean;
    }

    public static DayOfWeek findByKorean(String korean) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getKorean().equals(korean)) {
                return day;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getKorean() {
        return korean;
    }
}
