package repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class MusicianRepositoryTest {
    MusicianRepository musicianRepository = new MusicianRepository();

    @BeforeEach
    void addMusicians() {
        Map<String, Object> args = new HashMap<>();
        args.put("name", "Musician");
        musicianRepository.add(args);
        args.put("name", "Second Musician");
        musicianRepository.add(args);
        args.put("name", "Third Musician");
        musicianRepository.add(args);
    }

    @Test
    void getByID() {
        try {
            assert musicianRepository.getByID(1).getName().equals("Musician");
            assert musicianRepository.getByID(2).getName().equals("Second Musician");
            assert musicianRepository.getByID(3).getName().equals("Third Musician");
        } catch (Exception e) {
            System.out.println("Not found by ID");
        }
    }

    @Test
    void modify() {
        Map<String, Object> args = new HashMap<>();
        args.put("name", "Modified Musician");
        assert musicianRepository.modify(1, args);
    }

    @Test
    void delete() {
        assert musicianRepository.delete(1);
        try {
            musicianRepository.getByID(1);
            assert false;
        } catch (Exception e) {
            assert true;
        }
    }
}
