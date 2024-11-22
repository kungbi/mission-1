package oncall.dto;

import oncall.enums.DayOfWeek;

public record MakeScheduleInputDto(int month, DayOfWeek dayOfWeek, int numberOfDaysPerMonth) {

    public static class Builder {
        private int month;
        private DayOfWeek dayOfWeek;
        private int numberOfDaysPerMonth;

        public Builder month(int month) {
            this.month = month;
            return this;
        }

        public Builder dayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public Builder numberOfDaysPerMonth(int numberOfDaysPerMonth) {
            this.numberOfDaysPerMonth = numberOfDaysPerMonth;
            return this;
        }

        public MakeScheduleInputDto build() {
            return new MakeScheduleInputDto(month, dayOfWeek, numberOfDaysPerMonth);
        }
    }
}
