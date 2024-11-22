package oncall.domain;

public class Person {
    private final String name;

    public Person(String name) {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validate(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }


}
