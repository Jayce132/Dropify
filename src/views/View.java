package views;

import controllers.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class View {
    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller = Controller.getInstance();

    public View() {

    }

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
                    System.out.println("Enter musician: ");
                    try {
                       List<Object> musicianRepo = controller.getAll("Musician");
                    } catch (Exception e) {
                        System.out.println("YOOOOO");
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
