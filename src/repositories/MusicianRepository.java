package repositories;

import domain.Musician;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicianRepository implements Repository<Musician> {
    private final List<Musician> musicianRepository = new ArrayList<>();
    private static Integer ID = 0;

    public List<Musician> getAll() {
        return musicianRepository;
    }

    public Musician getByID(Integer id) throws Exception {
        for (Musician musician : musicianRepository) {
            if (musician.get_id().equals(id)) {
                return musician;
            }
        }
        throw new Exception("Musician not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("name")) {
            Musician musician = new Musician((String) args.get("name"));
            ID++;
            musician.set_id(ID);
            musicianRepository.add(musician);
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        for (Musician musician : musicianRepository) {
            if (musician.get_id().equals(id)) {
                if (args.containsKey("name")) {
                    musician.setName((String) args.get("name"));
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(Integer id) {
        for (Musician musician : musicianRepository) {
            if (musician.get_id().equals(id)) {
                musicianRepository.remove(musician);
                return true;
            }
        }
        return false;
    }
}
//        List<Musician> filtered = musicianRepository.stream().filter(x -> x.get_id().equals(id)).collect(Collectors.toList());
//        if (filtered.size() > 0) {
//            return filtered.get(0);
//        } else {
//            throw new Exception("Musician not found by ID");
//        }