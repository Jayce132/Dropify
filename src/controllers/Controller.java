package controllers;

import domain.Album;
import domain.Category;
import domain.Musician;
import domain.Song;
import repositories.*;
import services.AlbumService;
import services.CategoryService;
import services.MusicianService;
import services.SongService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Controller {
    private static SongService songService;
    private static MusicianService musicianService;
    private static AlbumService albumService;
    private static CategoryService categoryService;

    private static Controller controller = new Controller();

    private Controller() {
        songService = new SongService();
        musicianService = new MusicianService();
        albumService = new AlbumService();
        categoryService = new CategoryService();
    }

    public static Controller getInstance() {
        return controller;
    }

    public List<Object> getAll(String repository) throws Exception {
        switch (repository) {
            case "Song":
                return songService.getAll()
                        .stream()
                        .map(object -> (Song) object)
                        .collect(Collectors.toList());
            case "Musician":
                return musicianService.getAll()
                        .stream()
                        .map(object -> (Musician) object)
                        .collect(Collectors.toList());
            case "Album":
                return albumService.getAll()
                        .stream()
                        .map(object -> (Album) object)
                        .collect(Collectors.toList());
            case "Category":
                return categoryService.getAll()
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
                return songService.getByID(ID);
            case "Musician":
                return musicianService.getByID(ID);
            case "Album":
                return albumService.getByID(ID);
            case "Category":
                return categoryService.getByID(ID);
            default:
                throw new Exception("Nothing found by ID");
        }
    }

    public void add(String repository, Map<String, Object> args) {
        switch (repository) {
            case "Song":
                songService.add(args);
                break;
            case "Musician":
                musicianService.add(args);
                break;
            case "Album":
                albumService.add(args);
                break;
            case "Category":
                categoryService.add(args);
                break;
            default:
                break;
        }
    }

    public boolean modify(String repository, Integer ID, Map<String, Object> args) {
        switch (repository) {
            case "Song":
                return songService.modify(ID, args);
            case "Musician":
                return musicianService.modify(ID, args);
            case "Album":
                return albumService.modify(ID, args);
            case "Category":
                return categoryService.modify(ID, args);
            default:
                return false;
        }
    }

    public boolean delete(String repository, Integer ID) {
        switch (repository) {
            case "Song":
                return songService.delete(ID);
            case "Musician":
                return musicianService.delete(ID);
            case "Album":
                return albumService.delete(ID);
            case "Category":
                return categoryService.delete(ID);
            default:
                return false;
        }
    }

    public List<Song> searchBySongName(String title) {
        List<Song> songs = songService.getAll()
                .stream()
                .filter(x -> x.getTitle()
                        .equals(title))
                .collect(Collectors.toList());
        return songs;
    }

    public List<Musician> searchByMusicianName(String name) {
        List<Musician> musicians = musicianService.getAll()
                .stream()
                .filter(x -> x.getName()
                        .equals(name))
                .collect(Collectors.toList());
        return musicians;
    }

    public List<Album> searchByAlbumName(String name) {
        List<Album> albums = albumService.getAll()
                .stream()
                .filter(x -> x.getName()
                        .equals(name))
                .collect(Collectors.toList());
        return albums;
    }

    public List<Category> searchByCategoryName(String name) {
        List<Category> categories = categoryService.getAll()
                .stream()
                .filter(x -> x.getName()
                        .equals(name))
                .collect(Collectors.toList());
        return categories;
    }

    public Song searchBySongID(Integer ID) throws Exception {
        Optional<Song> song = songService.getAll()
                .stream()
                .filter(x -> x.get_id()
                        .equals(ID))
                .findFirst();
        if (song.isPresent()) { //means is not null
            return song.get(); //.get unwraps Optional
        } else {
            throw new Exception("Song not found by ID");
        }
    }

    public Musician searchByMusicianID(Integer ID) throws Exception {
        Optional<Musician> musician = musicianService.getAll()
                .stream()
                .filter(x -> x.get_id()
                        .equals(ID))
                .findFirst();
        if (musician.isPresent()) {
            return musician.get();
        } else {
            throw new Exception("Musician not found by ID");
        }
    }

    public Album searchByAlbumID(Integer ID) throws Exception {
        Optional<Album> album = albumService.getAll()
                .stream()
                .filter(x -> x.get_id()
                        .equals(ID))
                .findFirst();
        if (album.isPresent()) {
            return album.get();
        } else {
            throw new Exception("Album not found by ID");
        }
    }

    public Category searchByCategoryID(Integer ID) throws Exception {
        Optional<Category> category = categoryService.getAll()
                .stream()
                .filter(x -> x.get_id()
                        .equals(ID))
                .findFirst();
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new Exception("Category not found by ID");
        }
    }
}