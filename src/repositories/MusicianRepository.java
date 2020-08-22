package repositories;

import domain.Musician;

import java.util.HashMap;
import java.util.Map;

public class MusicianRepository implements Repository<Musician> {
    private final HashMap<Integer, Musician> musicianRepository = new HashMap<>();
    private static Integer ID = 0;

    public Map<Integer, Musician> getAll() {
        return musicianRepository;
    }

    public Musician getByID(Integer id) throws Exception {
        if (musicianRepository.containsKey(id)) {
            return musicianRepository.get(id);
        }
        throw new Exception("Musician not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("name")) {
            Musician musician = new Musician((String) args.get("name"));
            ID++;
            musicianRepository.put(ID, musician);
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        if (musicianRepository.containsKey(id)) {
            Musician musician = musicianRepository.get(id);
            if (args.containsKey("name")) {
                musician.setName((String) args.get("name"));
                return true;
            }
        }
        return false;
    }

    public boolean delete(Integer id) {
        if (musicianRepository.containsKey(id)) {
            musicianRepository.remove(id);
            return true;
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