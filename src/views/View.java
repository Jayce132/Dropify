package views;

import controllers.Controller;
import domain.Album;
import domain.Category;
import domain.Musician;

import java.util.*;


public class View {
    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller = Controller.getInstance();

    public void start() {
        while (true) {
            System.out.println("Enter a command: ");
            String command = scanner.nextLine();
            if ("add".equals(command)) {
                System.out.println("What to add to the database?");
                String toAdd = scanner.nextLine();
                if ("Song".equals(toAdd)) {
                    System.out.println("Enter song title: ");
                    String title = scanner.nextLine();
                    Map<String, Object> map = new HashMap<>();
                    map.put("title", title);

                    System.out.println("Pick the number of the Musician you want: ");
                    try {
                        int number = 1;
                        List<Object> musicianRepo = controller.getAll("Musician");
                        for (Object musician : musicianRepo) {
                            System.out.println(number + ": " + musician);
                            number++;
                        }
                        int musicianIndex = Integer.parseInt(scanner.nextLine());
                        Musician musician = (Musician) musicianRepo.get(musicianIndex - 1);
                        map.put("musician", musician);
                    } catch (Exception e) {
                        System.out.println("No musician repository");
                    }

                    System.out.println("Pick the number of the Album you want: ");
                    try {
                        int number = 1;
                        List<Object> albumRepo = controller.getAll("Album");
                        for (Object album : albumRepo) {
                            System.out.println(number + ": " + album);
                            number++;
                        }
                        int albumIndex = Integer.parseInt(scanner.nextLine());
                        Album album = (Album) albumRepo.get(albumIndex - 1);
                        map.put("album", album);
                    } catch (Exception e) {
                        System.out.println("No album repository");
                    }

                    while (true) {
                        System.out.println("Pick the number of the Category you want: ");
                        System.out.println("You can pick one to three different categories, type 0 when it's enough");
                        try {
                            int number = 1;
                            List<Object> categoryRepo = controller.getAll("Category");
                            for (Object category : categoryRepo) {
                                System.out.println(number + ": " + category);
                                number++;
                            }
                            int categoryIndex = Integer.parseInt(scanner.nextLine());
                            if (categoryIndex == 0) {
                                break;
                            }
                            Category category = (Category) categoryRepo.get(categoryIndex - 1);
                            HashSet<Category> categories = new HashSet<>();
                            categories.add(category);
                            if (categories.size() >= 3) {
                                break;
                            }
                            map.put("categories", categories);
                        } catch (Exception e) {
                            System.out.println("No category repository");
                        }
                        controller.add("Song", map);
                    }
                        try {
                            System.out.println(controller.getAll("Song"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                } else if ("Musician".equals(toAdd)) {
                    System.out.println("Enter musician name: ");
                    String name = scanner.nextLine();
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    controller.add("Musician", map);
                } else if ("Album".equals(toAdd)) {
                    System.out.println("Enter album name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter year of release: ");
                    Integer yearOfRelease = Integer.parseInt(scanner.nextLine());
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("yearOfRelease", yearOfRelease);
                    controller.add("Album", map);
                } else if ("Category".equals(toAdd)) {
                    System.out.println("Enter category name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter category description: ");
                    String description = scanner.nextLine();
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("description", description);
                    controller.add("Category", map);
                }
            }
            if ("end".equals(command)) {
                break;
            }
        }
    }
}
