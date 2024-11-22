package oncall.enums;

public enum NumberOfDaysPerMonth {
    January(1, 31),
    February(2, 28),
    March(3, 31),
    April(4, 30),
    May(5, 31),
    June(6, 30),
    July(7, 31),
    August(8, 31),
    September(9, 30),
    October(10, 31),
    November(11, 30),
    December(12, 31);

    private final int month;
    private final int numberOfDays;

    NumberOfDaysPerMonth(int month, int numberOfDays) {
        this.month = month;
        this.numberOfDays = numberOfDays;
    }

    public static NumberOfDaysPerMonth findNumberOfDaysPerMonth(int month) {
        for (NumberOfDaysPerMonth numberOfDaysPerMonth : NumberOfDaysPerMonth.values()) {
            if (numberOfDaysPerMonth.getMonth() == month) {
                return numberOfDaysPerMonth;
            }
        }
        throw new IllegalArgumentException("Invalid month: " + month);
    }

    public int getMonth() {
        return month;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }
}
