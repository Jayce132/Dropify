package repositories;

import domain.Album;

import java.util.HashMap;
import java.util.Map;

public class AlbumRepository implements Repository<Album> {
    private final HashMap<Integer, Album> albumRepository = new HashMap<>();
    private static Integer ID = 0;

    public Map<Integer, Album> getAll() {
        return albumRepository;
    }

    public Album getByID(Integer id) throws Exception {
        if (albumRepository.containsKey(id)) {
            return albumRepository.get(id);
        }
        throw new Exception("Album not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("name") && args.containsKey("yearOfRelease")) {
            Album album = new Album((String)args.get("name"), (Integer)args.get("yearOfRelease"));
            ID++;
            albumRepository.put(ID, album);
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        if (albumRepository.containsKey(id)) {
            Album album = albumRepository.get(id);
            if (args.containsKey("name") && args.containsKey("yearOfRelease")) {
                album.setName((String)args.get("name"));
                album.setYearOfRelease((Integer)args.get("yearOfRelease"));
                return true;
            }
        }
        return false;
    }

    public boolean delete(Integer id) {
        if (albumRepository.containsKey(id)) {
            albumRepository.remove(id);
            return true;
        }
        return false;
    }
}
