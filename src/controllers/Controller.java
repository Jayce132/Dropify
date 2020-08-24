package controllers;

import domain.Album;
import domain.Category;
import domain.Musician;
import domain.Song;
import repositories.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private static SongRepository songRepository;
    private static MusicianRepository musicianRepository;
    private static AlbumRepository albumRepository;
    private static CategoryRepository categoryRepository;

    private static Controller controller = new Controller();

    private Controller() {
        songRepository = new SongRepository();
        musicianRepository = new MusicianRepository();
        albumRepository = new AlbumRepository();
        categoryRepository = new CategoryRepository();
    }

    public static Controller getInstance() {
        return controller;
    }

    public List<Object> getAll(String repository) throws Exception {
        switch (repository) {
            case "Song":
                return songRepository.getAll()
                        .stream()
                        .map(object -> (Song) object)
                        .collect(Collectors.toList());
            case "Musician":
                return musicianRepository.getAll()
                        .stream()
                        .map(object -> (Musician) object)
                        .collect(Collectors.toList());
            case "Album":
                return albumRepository.getAll()
                        .stream()
                        .map(object -> (Album) object)
                        .collect(Collectors.toList());
            case "Category":
                return categoryRepository.getAll()
                        .stream()
                        .map(object -> (Category) object)
                        .collect(Collectors.toList());
            default:
                throw new Exception("No repository to return");
        }
    }

    public Object getById(String repository, Integer ID) throws Exception {
        switch (repository) {
            case "Song":
                return songRepository.getByID(ID);
            case "Musician":
                return musicianRepository.getByID(ID);
            case "Album":
                return albumRepository.getByID(ID);
            case "Category":
                return categoryRepository.getByID(ID);
            default:
                throw new Exception("Nothing found by ID");
        }
    }

    public void add(String repository, Map<String, Object> args) {
        switch (repository) {
            case "Song":
                songRepository.add(args);
                break;
            case "Musician":
                musicianRepository.add(args);
                break;
            case "Album":
                albumRepository.add(args);
                break;
            case "Category":
                categoryRepository.add(args);
                break;
            default:
                break;
        }
    }

    public boolean modify(String repository, Integer ID, Map<String, Object> args) {
        switch (repository) {
            case "Song":
                return songRepository.modify(ID, args);
            case "Musician":
                return musicianRepository.modify(ID, args);
            case "Album":
                return albumRepository.modify(ID, args);
            case "Category":
                return categoryRepository.modify(ID, args);
            default:
                return false;
        }
    }

    public boolean delete(String repository, Integer ID) {
        switch (repository) {
            case "Song":
                return songRepository.delete(ID);
            case "Musician":
                return musicianRepository.delete(ID);
            case "Album":
                return albumRepository.delete(ID);
            default:
                return false;
        }
    }

    public List<Song> searchBySongName(String title) {
        List<Song> songs = songRepository.getAll()
                .stream()
                .filter(x -> x.getTitle()
                        .equals(title))
                .collect(Collectors.toList());
        return songs;
    }

    public List<Musician> searchByMusicianName(String name) {
        List<Musician> musicians = musicianRepository.getAll()
                .stream()
                .filter(x -> x.getName()
                        .equals(name))
                .collect(Collectors.toList());
        return musicians;
    }

    public List<Album> searchByAlbumName(String name) {
        List<Album> albums = albumRepository.getAll()
                .stream()
                .filter(x -> x.getName()
                        .equals(name))
                .collect(Collectors.toList());
        return albums;
    }

    public List<Category> searchByCategoryName(String name) {
        List<Category> categories = categoryRepository.getAll()
                .stream()
                .filter(x -> x.getName()
                        .equals(name))
                .collect(Collectors.toList());
        return categories;
    }
}