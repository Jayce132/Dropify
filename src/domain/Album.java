package domain;

import java.util.Objects;

public class Album extends Entity {
    private String name;
    private Integer yearOfRelease;

    public Album(String name, Integer yearOfRelease) {
        this.name = name;
        this.yearOfRelease = yearOfRelease;
    }

    public String getName() {
        return name;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setName(String name) {
        if (this.name == null) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        if (this.yearOfRelease == null) {
            this.yearOfRelease = 0;
        } else {
            this.yearOfRelease = yearOfRelease;
        }
    }

    @Override
    public String toString() {
        return name + " was released in " + yearOfRelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Album album = (Album) o;
        return Objects.equals(name, album.name) &&
                Objects.equals(yearOfRelease, album.yearOfRelease);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearOfRelease);
    }
}
