package oncall.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import oncall.domain.Person;

public class PersonRepository implements Repository<Person> {
    private final List<Person> persons = new ArrayList<>();

    @Override
    public void add(Person data) {
        persons.add(data);
    }

    @Override
    public void remove(Person data) {

    }

    @Override
    public int getSize() {
        return persons.size();
    }

    @Override
    public Optional<Person> findByName(String name) {
        return persons.stream().filter(person -> person.getName().equals(name)).findFirst();
    }

    @Override
    public List<Person> findAll() {
        return List.copyOf(persons);
    }

    @Override
    public void update(String name, Person newData) {

    }

    @Override
    public boolean exists(String name) {
        Optional<Person> person = findByName(name);
        if (person.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public List<Person> findByCondition(Predicate<Person> condition) {
        return List.of();
    }

    @Override
    public void clear() {

    }

    @Override
    public List<Person> findPage(int pageNumber, int pageSize) {
        return List.of();
    }
}
