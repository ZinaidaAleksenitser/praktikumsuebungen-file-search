package SearchWithThread;

import java.io.File;
import java.util.Scanner;

/*Schreibe ein Programm, das die gleiche Funktionalität wie das Programm aus 2.2) bietet, jedoch zur Suche mehrere Threads verwendet.
Lasse auch hier Dein Programm die Zeit messen und vergleiche die Zeiten miteinander.
Versuche herauszufinden, welche Anzahl an Threads für die Suche ideal ist.

 */

public class SearchWithThread {

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        String fileName = fileNameValidation(scanner);
        String directoryPath = directoryPathValidation(scanner);
        File directory = new File(directoryPath);
        SearchService searchService = new SearchService(fileName, directory);
        Thread thread = new Thread(searchService);
        long startTime = System.nanoTime();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.nanoTime();
        System.out.println("Suchdauer: " + ((endTime - startTime) / 1000000) + " ms");
        scanner.close();
    }

    public static String fileNameValidation(Scanner scanner) {
        String fileName;
        boolean valid;
        do{
            System.out.println("Please enter a file name");
            fileName = scanner.nextLine();
            valid = fileName.matches("[-_. A-Za-z0-9]+\\.[A-Za-z0-9]+");
            if(!valid){
                System.out.println("Invalid file name. Please try again.");
            }
        }
        while (!valid);
        return fileName;
    }

    public static String directoryPathValidation(Scanner scanner){
        String directoryPath;
        boolean valid;
        do{
            System.out.println("Please enter the directory path:");
            directoryPath = scanner.nextLine();
            valid = directoryPath.matches("^[a-zA-Z]:\\\\(?:[^\\\\/:*?\"<>|\\r\\n]+\\\\)*[^\\\\/:*?\"<>|\\r\\n]*$");
            if(!valid){
                System.out.println("Invalid directory path. Please try again.");
            }
        }
        while (!valid);
        return directoryPath;
    }
}
