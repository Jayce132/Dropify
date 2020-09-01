package services;

import domain.Musician;
import repositories.MusicianRepository;

import java.util.List;
import java.util.Map;

public class MusicianService {
    public static MusicianRepository musicianRepository = new MusicianRepository();

    public List<Musician> getAll() {
        return musicianRepository.getAll();
    }

    public Musician getByID(Integer id) throws Exception {
        return musicianRepository.getByID(id);
    }

    public void add(Map<String, Object> args) {
            musicianRepository.add(args);
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        return musicianRepository.modify(id, args);
    }

    public boolean delete(Integer id) {
        return musicianRepository.delete(id);
    }
}
