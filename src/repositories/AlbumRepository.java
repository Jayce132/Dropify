package repositories;

import domain.Album;
import domain.Musician;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumRepository implements Repository<Album> {
    private final List<Album> albumRepository = new ArrayList<>();
    private static Integer ID = 0;

    public List<Album> getAll() {
        return albumRepository;
    }

    public Album getByID(Integer id) throws Exception {
        for (Album album : albumRepository) {
            if (album.get_id().equals(id)) {
                return album;
            }
        }
        throw new Exception("Album not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("name") && args.containsKey("yearOfRelease")) {
            Album album = new Album((String) args.get("name"), (Integer) args.get("yearOfRelease"));
            ID++;
            album.set_id(ID);
            albumRepository.add(album);
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        for (Album album : albumRepository) {
            if (album.get_id().equals(id)) {
                if (args.containsKey("name") && args.containsKey("yearOfRelease")) {
                    album.setName((String) args.get("name"));
                    album.setYearOfRelease((Integer) args.get("yearOfRelease"));
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(Integer id) {
        for (Album album : albumRepository) {
            if (album.get_id().equals(id)) {
                albumRepository.remove(album);
                return true;
            }
        }
        return false;
    }
}
//if (albumRepository.containsKey(id)) {
//        Album album = albumRepository.get(id);
//        if (args.containsKey("name") && args.containsKey("yearOfRelease")) {
//        album.setName((String)args.get("name"));
//        album.setYearOfRelease((Integer)args.get("yearOfRelease"));
//        return true;
//        }
//        }
//        return false;
//if (albumRepository.containsKey(id)) {
//        albumRepository.remove(id);
//        return true;
//        }
//        return false;