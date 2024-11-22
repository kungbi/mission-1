package oncall.dto;


public record WorkDto(int month, int day, boolean holiday, String dayOfWeek, String workerName) {

}
