package services;

import domain.Album;
import repositories.AlbumRepository;

import java.util.List;
import java.util.Map;

public class AlbumService {
    public static AlbumRepository albumRepository = new AlbumRepository();

    public List<Album> getAll() {
        return albumRepository.getAll();
    }

    public Album getByID(Integer id) throws Exception {
        return albumRepository.getByID(id);
    }

    public void add(Map<String, Object> args) {
        albumRepository.add(args);
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        return albumRepository.modify(id, args);
    }

    public boolean delete(Integer id) {
        return albumRepository.delete(id);
    }
}
