package repositories;

import domain.Category;
import domain.Song;
import domain.Musician;
import domain.Album;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class SongRepository implements Repository<Song> {
    private final List<Song> songRepository = new ArrayList<>();
    private static Integer ID = 0;

    public List<Song> getAll() {
        return songRepository;
    }

    public Song getByID(Integer id) throws Exception {
        for (Song song : songRepository) {
            if (song.get_id().equals(id)) {
                return song;
            }
        }
        throw new Exception("Song not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("title")
                && args.containsKey("musician")
                && args.containsKey("album")
                && args.containsKey("categories")) {
            Song song = new Song((String) args.get("title"),
                    (Musician) args.get("musician"),
                    (Album) args.get("album"),
                    (HashSet<Category>) args.get("categories"));
            ID++;
            song.set_id(ID);
            songRepository.add(song);
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        for (Song song : songRepository) {
            if (song.get_id().equals(id)) {
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
        }
        return false;
    }

    public boolean delete(Integer id) {
        for (Song song : songRepository) {
            if (song.get_id().equals(id)) {
                songRepository.remove(song);
                return true;
            }
        }
        return false;
    }

}
