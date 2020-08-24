package repositories;

import domain.Model;

import java.util.List;
import java.util.Map;
public interface Repository<SongMusicianAlbumOrCategory extends Model> {
    public List<SongMusicianAlbumOrCategory> getAll();

    public SongMusicianAlbumOrCategory getByID(Integer id) throws Exception;

    public void add(Map<String, Object> args);

    public boolean modify(Integer id, Map<String, Object> args);

    public boolean delete(Integer id);
}
