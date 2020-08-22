package repositories;

import domain.Category;
import domain.Song;
import domain.Musician;
import domain.Album;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SongRepository implements Repository<Song> {
    private final HashMap<Integer, Song> songRepository = new HashMap<>();
    private static Integer ID = 0;

    public HashMap<Integer, Song> getAll() {
        return songRepository;
    }

    public Song getByID(Integer id) throws Exception {
        if (songRepository.containsKey(id)) {
            return songRepository.get(id);
        }
        throw new Exception("Song not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("title") && args.containsKey("musician") && args.containsKey("album") && args.containsKey("categories")) {
            Song song = new Song((String) args.get("title"),
                    (Musician) args.get("musician"),
                    (Album) args.get("album"),
                    (HashSet<Category>) args.get("categories"));
            ID++;
            songRepository.put(ID, song);
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        if (songRepository.containsKey(id)) {
            Song song = songRepository.get(id);
            if (args.containsKey("title")
                    && args.containsKey("musician")
                    && args.containsKey("album")
                    && args.containsKey("categories")) {
                song.setTitle((String) args.get("title"));
                song.setMusician((Musician) args.get("musician"));
                song.setAlbum((Album) args.get("album"));
                song.setCategories((HashSet<Category>) args.get("categories"));
                return true;
            }
        }
        return false;
    }

    public boolean delete(Integer id) {
        if (songRepository.containsKey(id)) {
            songRepository.remove(id);
            return true;
        }
        return false;
    }

}
