package oncall.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import oncall.domain.Holiday;

/**
 * 휴일을 저장하는 DB 입니다.
 * 해당 repository 를 사용하여 휴일을 추가하거나 탐색할 수 있습니다.
 */
public class HolidayRepository implements Repository<Holiday> {
    private final List<Holiday> holidays = new ArrayList<>();

    public Optional<Holiday> findByDate(int month, int day) {
        for (Holiday holiday : holidays) {
            if (holiday.getMonth() == month && holiday.getDay() == day) {
                return Optional.of(holiday);
            }
        }
        return Optional.empty();
    }

    @Override
    public void add(Holiday data) {
        holidays.add(data);
    }

    @Override
    public void remove(Holiday data) {

    }

    @Override
    public int getSize() {
        return holidays.size();
    }

    @Override
    public Optional<Holiday> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Holiday> findAll() {
        return List.of();
    }

    @Override
    public void update(String name, Holiday newData) {

    }

    @Override
    public boolean exists(String name) {
        return false;
    }

    @Override
    public List<Holiday> findByCondition(Predicate<Holiday> condition) {
        return List.of();
    }

    @Override
    public void clear() {

    }

    @Override
    public List<Holiday> findPage(int pageNumber, int pageSize) {
        return List.of();
    }
}
