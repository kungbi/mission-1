package oncall.enums;

import oncall.exception.GlobalErrorMessage;

public enum DayOfWeek {
    MONDAY(0, "월"),
    TUESDAY(1, "화"),
    WEDNESDAY(2, "수"),
    THURSDAY(3, "목"),
    FRIDAY(4, "금"),
    SATURDAY(5, "토"),
    SUNDAY(6, "일");

    public static final int NUMBER_OF_DAYS_IN_WEEK = 7;
    private final int order;
    private final String korean;

    DayOfWeek(int order, String korean) {
        this.order = order;
        this.korean = korean;
    }

    public static DayOfWeek findByKorean(String korean) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getKorean().equals(korean)) {
                return day;
            }
        }
        throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
    }

    public static DayOfWeek findByOrder(int order) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getOrder() == order) {
                return day;
            }
        }
        throw new IllegalArgumentException(GlobalErrorMessage.INVALID_INPUT.getMessage());
    }

    public static DayOfWeek nextDayOfWeek(DayOfWeek dayOfWeek) {
        return findByOrder((dayOfWeek.getOrder() + 1) % NUMBER_OF_DAYS_IN_WEEK);
    }

    public static boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == SATURDAY || dayOfWeek == SUNDAY;
    }

    public int getOrder() {
        return order;
    }

    public String getKorean() {
        return korean;
    }
}
