package oncall.dto;

import oncall.enums.DayOfWeek;

public record MakeScheduleInputDto(int month, DayOfWeek dayOfWeek, int numberOfDaysPerMonth) {
}
