package domain;

import java.util.Objects;

public class Musician extends Entity {
    private String name;

    public Musician(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Musician musician = (Musician) o;
        return Objects.equals(name, musician.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
