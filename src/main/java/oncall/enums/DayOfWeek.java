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

    public String getKorean() {
        return korean;
    }
}
