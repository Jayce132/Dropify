package services;

import domain.Song;
import repositories.SongRepository;

import java.util.List;
import java.util.Map;

public class SongService {
    public static SongRepository songRepository = new SongRepository();

    public List<Song> getAll() {
        return songRepository.getAll();
    }

    public Song getByID(Integer id) throws Exception {
        return songRepository.getByID(id);
    }

    public void add(Map<String, Object> args) {
        songRepository.add(args);
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        return songRepository.modify(id, args);
    }

    public boolean delete(Integer id) {
        return songRepository.delete(id);
    }
}
