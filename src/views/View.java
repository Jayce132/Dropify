package views;

import controllers.Controller;
import domain.Album;
import domain.Category;
import domain.Musician;
import domain.Song;

import java.util.*;
import java.util.stream.Collectors;


public class View {
    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller = Controller.getInstance();

    public void start() {
        while (true) {
            System.out.println("\nEnter a command: ");
            System.out.println("add Something, modify Something, list Something, delete Something, search Something, end program");
            String toSplit = scanner.nextLine();
            String[] split = toSplit.split(" ");
            String command = split[0];
            String subCommand = split[1];
            if ("add".equals(command)) {
                switch (subCommand) {
                    case "Song":
                        System.out.println("Enter song title: ");
                        String title = scanner.nextLine();
                        Map<String, Object> map = new HashMap<>();
                        map.put("title", title);

                        System.out.println("Pick the number of the Musician you want: ");
                        try {
                            List<Object> objectRepo = controller.getAll("Musician");
                            List<Musician> musicianRepo = objectRepo
                                    .stream()
                                    .map(x -> (Musician) x)
                                    .collect(Collectors.toList());
                            for (Musician musician : musicianRepo) {
                                System.out.println(musician.get_id() + ": " + musician);
                            }
                            int musicianIndex = Integer.parseInt(scanner.nextLine());

                            for (Musician musician : musicianRepo) {
                                if (musician.get_id() == musicianIndex) {
                                    map.put("musician", musician);
                                    break;
                                }
                            }
                        } catch (Exception noMusicianRepo) {
                            System.out.println("No musician repository");
                        }

                        System.out.println("Pick the number of the Album you want: ");
                        try {
                            List<Object> objectRepo = controller.getAll("Album");
                            List<Album> albumRepo = objectRepo
                                    .stream()
                                    .map(x -> (Album) x)
                                    .collect(Collectors.toList());
                            for (Album album : albumRepo) {
                                System.out.println(album.get_id() + ": " + album);
                            }

                            int albumIndex = Integer.parseInt(scanner.nextLine());
                            for (Album album : albumRepo) {
                                if (album.get_id() == albumIndex) {
                                    map.put("album", album);
                                    break;
                                }
                            }
                        } catch (Exception noAlbumRepo) {
                            System.out.println("No album repository");
                        }

                        HashSet<Category> categories = new HashSet<>();
                        while (true) {
                            System.out.println("Pick the number of the Category you want: ");
                            System.out.println("You can pick one to three different categories, type 0 when it's enough");
                            try {
                                List<Object> objectRepo = controller.getAll("Category");
                                List<Category> categoryRepo = objectRepo
                                        .stream()
                                        .map(x -> (Category) x)
                                        .collect(Collectors.toList());
                                for (Category category : categoryRepo) {
                                    System.out.println(category.get_id() + ": " + category);
                                }
                                int categoryIndex = Integer.parseInt(scanner.nextLine());
                                if (categoryIndex == 0) {
                                    break;
                                }
                                for (Category category : categoryRepo) {
                                    if (category.get_id() == categoryIndex) {
                                        categories.add(category);
                                        break;
                                    }
                                }
                                if (categories.size() >= 3) {
                                    break;
                                }
                                map.put("categories", categories);
                            } catch (Exception e) {
                                System.out.println("No category repository");
                            }
                        }

                        controller.add("Song", map);
                        break;

                    case "Musician":
                        System.out.println("Enter musician name: ");
                        String name = scanner.nextLine();
                        map = new HashMap<>();
                        map.put("name", name);
                        controller.add("Musician", map);
                        break;

                    case "Album":
                        System.out.println("Enter album name: ");
                        name = scanner.nextLine();
                        System.out.println("Enter year of release: ");
                        Integer yearOfRelease = Integer.parseInt(scanner.nextLine());
                        map = new HashMap<>();
                        map.put("name", name);
                        map.put("yearOfRelease", yearOfRelease);
                        controller.add("Album", map);
                        break;

                    case "Category":
                        System.out.println("Enter category name: ");
                        name = scanner.nextLine();
                        System.out.println("Enter category description: ");
                        String description = scanner.nextLine();
                        map = new HashMap<>();
                        map.put("name", name);
                        map.put("description", description);
                        controller.add("Category", map);
                        break;

                    default:
                        break;
                }
            } else if ("modify".equals(command)) {
                switch (subCommand) {
                    case "Song":
                        System.out.println("Enter the ID of the Song you want to modify: ");
                        int modifyID = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter song title: ");
                        String title = scanner.nextLine();
                        Map<String, Object> map = new HashMap<>();
                        map.put("title", title);

                        System.out.println("Pick the ID of the Musician you want: ");
                        try {
                            List<Object> objectRepo = controller.getAll("Musician");
                            List<Musician> musicianRepo = objectRepo
                                    .stream()
                                    .map(x -> (Musician) x)
                                    .collect(Collectors.toList());
                            for (Musician musician : musicianRepo) {
                                System.out.println(musician.get_id() + ": " + musician);
                            }
                            int musicianIndex = Integer.parseInt(scanner.nextLine());

                            for (Musician musician : musicianRepo) {
                                if (musician.get_id() == musicianIndex) {
                                    map.put("musician", musician);
                                    break;
                                }
                            }
                        } catch (Exception noMusicianRepo) {
                            System.out.println("No musician repository");
                        }

                        System.out.println("Pick the ID of the Album you want: ");
                        try {
                            List<Object> objectRepo = controller.getAll("Album");
                            List<Album> albumRepo = objectRepo
                                    .stream()
                                    .map(x -> (Album) x)
                                    .collect(Collectors.toList());
                            for (Album album : albumRepo) {
                                System.out.println(album.get_id() + ": " + album);
                            }

                            int albumIndex = Integer.parseInt(scanner.nextLine());
                            for (Album album : albumRepo) {
                                if (album.get_id() == albumIndex) {
                                    map.put("album", album);
                                    break;
                                }
                            }
                        } catch (Exception noAlbumRepo) {
                            System.out.println("No album repository");
                        }

                        HashSet<Category> categories = new HashSet<>();
                        while (true) {
                            System.out.println("Pick the ID of the Category you want: ");
                            System.out.println("You can pick one to three different categories, type 0 when it's enough");
                            try {
                                List<Object> objectRepo = controller.getAll("Category");
                                List<Category> categoryRepo = objectRepo
                                        .stream()
                                        .map(x -> (Category) x)
                                        .collect(Collectors.toList());
                                for (Category category : categoryRepo) {
                                    System.out.println(category.get_id() + ": " + category);
                                }
                                int categoryIndex = Integer.parseInt(scanner.nextLine());
                                if (categoryIndex == 0) {
                                    break;
                                }
                                for (Category category : categoryRepo) {
                                    if (category.get_id() == categoryIndex) {
                                        categories.add(category);
                                        break;
                                    }
                                }
                                if (categories.size() >= 3) {
                                    break;
                                }
                                map.put("categories", categories);
                            } catch (Exception e) {
                                System.out.println("No category repository");
                            }
                        }

                        controller.modify("Song", modifyID, map);
                        break;

                    case "Musician":
                        System.out.println("Enter the ID of the Musician you want to modify: ");
                        modifyID = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter musician name: ");
                        String name = scanner.nextLine();
                        map = new HashMap<>();
                        map.put("name", name);
                        controller.modify("Musician", modifyID, map);
                        break;

                    case "Album":
                        System.out.println("Enter the ID of the Album you want to modify: ");
                        modifyID = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter album name: ");
                        name = scanner.nextLine();
                        System.out.println("Enter year of release: ");
                        Integer yearOfRelease = Integer.parseInt(scanner.nextLine());
                        map = new HashMap<>();
                        map.put("name", name);
                        map.put("yearOfRelease", yearOfRelease);
                        controller.modify("Album", modifyID, map);
                        break;

                    case "Category":
                        System.out.println("Enter the ID of the Category you want to modify: ");
                        modifyID = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter category name: ");
                        name = scanner.nextLine();
                        System.out.println("Enter category description: ");
                        String description = scanner.nextLine();
                        map = new HashMap<>();
                        map.put("name", name);
                        map.put("description", description);
                        controller.modify("Category", modifyID, map);
                        break;

                    default:
                        break;
                }
            } else if ("delete".equals(command)) {
                switch (subCommand) {
                    case "Song":
                        System.out.println("Enter the number of the Song that you want to delete");
                        int deleteId = Integer.parseInt(scanner.nextLine());
                        controller.delete("Song", deleteId);
                        break;

                    case "Musician":
                        System.out.println("Enter the number of the Musician that you want to delete");
                        deleteId = Integer.parseInt(scanner.nextLine());
                        controller.delete("Musician", deleteId);
                        break;

                    case "Album":
                        System.out.println("Enter the number of the Album that you want to delete");
                        deleteId = Integer.parseInt(scanner.nextLine());
                        controller.delete("Album", deleteId);
                        break;

                    case "Category":
                        System.out.println("Enter the number of the Category that you want to delete");
                        deleteId = Integer.parseInt(scanner.nextLine());
                        controller.delete("Category", deleteId);
                        break;

                    default:
                        break;
                }
            } else if ("list".equals(command)) {
                switch (subCommand) {
                    case "Song":
                        try {
                            List<Object> objectRepo = controller.getAll("Song");
                            List<Song> songRepo = objectRepo
                                    .stream()
                                    .map(x -> (Song) x)
                                    .collect(Collectors.toList());
                            for (Song song : songRepo) {
                                System.out.println(song.get_id() + ": " + song);
                            }
                        } catch (Exception failedToListSongs) {
                            System.out.println("Failed to list songs");
                        }
                        break;

                    case "Musician":
                        try {
                            List<Object> objectRepo = controller.getAll("Musician");
                            List<Musician> musicianRepo = objectRepo
                                    .stream()
                                    .map(x -> (Musician) x)
                                    .collect(Collectors.toList());
                            for (Musician musician : musicianRepo) {
                                System.out.println(musician.get_id() + ": " + musician);
                            }
                        } catch (Exception failedToListMusicians) {
                            System.out.println("Failed to list musicians");
                        }
                        break;

                    case "Album":
                        try {
                            List<Object> objectRepo = controller.getAll("Album");
                            List<Album> albumRepo = objectRepo
                                    .stream()
                                    .map(x -> (Album) x)
                                    .collect(Collectors.toList());
                            for (Album album : albumRepo) {
                                System.out.println(album.get_id() + ": " + album);
                            }
                        } catch (Exception failedToListAlbums) {
                            System.out.println("Failed to list albums");
                        }
                        break;

                    case "Category":
                        try {
                            List<Object> objectRepo = controller.getAll("Category");
                            List<Category> categoryRepo = objectRepo
                                    .stream()
                                    .map(x -> (Category) x)
                                    .collect(Collectors.toList());
                            for (Category category : categoryRepo) {
                                System.out.println(category.get_id() + ": " + category);
                            }
                        } catch (Exception failedToListCategories) {
                            System.out.println("Failed to list categories");
                        }
                        break;

                    default:
                        break;
                }
            } else if ("search".equals(command)) {
                switch (subCommand) {
                    case "Song":
                        System.out.println("Name of the song you want to find: ");
                        String searchName = scanner.nextLine();
                        List<Song> foundSongs = controller.searchBySongName(searchName);
                        for (Song song : foundSongs) {
                            System.out.println(song.get_id() + ": " + song);
                        }
                        break;

                    case "Musician":
                        System.out.println("Name of the musician you want to find: ");
                        searchName = scanner.nextLine();
                        List<Musician> foundMusicians = controller.searchByMusicianName(searchName);
                        for (Musician musician : foundMusicians) {
                            System.out.println(musician.get_id() + ": " + musician);
                        }
                        break;

                    case "Album":
                        System.out.println("Name of the album you want to find: ");
                        searchName = scanner.nextLine();
                        List<Album> foundAlbums = controller.searchByAlbumName(searchName);
                        for (Album album : foundAlbums) {
                            System.out.println(album.get_id() + ": " + album);
                        }
                        break;

                    case "Category":
                        System.out.println("Name of the category you want to find: ");
                        searchName = scanner.nextLine();
                        List<Category> foundCategories = controller.searchByCategoryName(searchName);
                        for (Category category : foundCategories) {
                            System.out.println(category.get_id() + ": " + category);
                        }
                        break;

                    default:
                        break;
                }
            } else if ("end".equals(command)) {
                if ("program".equals(subCommand)) {
                    break;
                }
            }
        }
    }
}
