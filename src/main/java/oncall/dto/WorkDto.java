package oncall.dto;


import oncall.enums.DayOfWeek;

public record WorkDto(int month, int day, boolean holiday, DayOfWeek dayOfWeek, String workerName) {

}
