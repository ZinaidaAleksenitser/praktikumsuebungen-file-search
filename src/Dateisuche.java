import java.awt.Desktop;
import java.io.IOException;
import java.lang.String;
import java.util.Scanner;
import java.io.File;
import java.util.logging.Logger;

/*
Schreibe eine Java-Kommandozeilenanwendung, in der man einen Dateinamen und ein Basisverzeichnis eingeben kann.
Ausgehen von diesen Eingaben wird eine Datei mit dem angegebenen Namen dann rekursiv in allen Unterverzeichnissen des Basisverzeichnisses gesucht.
Falls erfolgreich, wird der gefundene Dateipfad ausgegeben, andernfalls eine Meldung, dass die Suche erfolglos war.

2.2.a) Lasse Dein Programm zusätzlich die zur Suche benötigte Zeit messen.
Falls du einen Partner hast, vergleicht eure Zeiten und eure Lösungen und optimiert ggf.

2.2.b) Verwende zusätzlich Logging statt Printausgaben

2.2.c) Benutze Exception Handling für fehlerhafte Eingaben.
*/


public class Dateisuche {
    private static final Logger logger = Logger.getLogger(Dateisuche.class.getName());

    void main() {

        System.setProperty("java.util.logging.SimpleFormatter.format", "%1StT [%4$s] %5$s%n");

        logger.info("Programm gestartet.");
        Scanner scanner = new Scanner(System.in);
        boolean programmFortsetzen;
        boolean phadGueltig;
        String verzeichnisName;

//Datei
        do {
            programmFortsetzen = false;
            System.out.println("Dateiname eingben: ");

            String dateiName = scanner.nextLine();
            try {
                dateiNameValidieren(dateiName);
            } catch (IllegalArgumentException e) {
                logger.warning(e.toString());
                System.out.println("Bitte giben Sie einen gültigen Dateinamen ein.");
                programmFortsetzen = true;
                continue;
            }
//Pfad
            do {
                phadGueltig = false;
                System.out.println("Verzeichnisname eingeben: ");
                verzeichnisName = scanner.nextLine();
                logger.info("Verzeichnisname wurde eingegeben");

                try {
                    verzeichnisNameValidieren(verzeichnisName);
                } catch (IllegalArgumentException e) {
                    logger.warning(e.toString());
                    System.out.println("Bitte giben Sie einen gültigen Verzeichnis ein.");
                    phadGueltig = true;
                }
            } while (phadGueltig);

            File ordner = new File(verzeichnisName);
            long startZeit = System.nanoTime();
            boolean result = dateiSuche(dateiName, ordner);
            long endZeit = System.nanoTime();
            logger.info("Suchdauer: " + ((endZeit - startZeit) / 1000000) + " ms");
            if (!result) System.out.println("Die Datei wurde nicht gefunden");

        } while (programmFortsetzen);
    }

    static boolean dateiSuche(String dateiName, File verzeichnisName) {
        logger.info("Die Dateisuche wurde gestartet");
        File[] dateien = verzeichnisName.listFiles();
        if (dateien == null) {
            logger.warning("Der ausgewälte Orner ist leer");
            return false;
        }
        for (File datei : dateien) {
            if (datei.isDirectory()) {
                logger.info("Die rekursive Suche wurde gestartet.");
                dateiSuche(dateiName, datei);
            } else if (datei.getName().equalsIgnoreCase(dateiName)) {
                logger.info("Datei ist gefunden: " + datei.getAbsolutePath());
                try {
                    Desktop.getDesktop().open(datei.getParentFile());
                    logger.info("Dateiordner in neuem Fenster geöffnet");
                } catch (IOException e) {
                    logger.severe("Die Ordner konnte nicht geöffnet werden");
                }
                return true;
            }
        }
        return false;
    }

    public static void dateiNameValidieren(String name) {
        if (!name.matches("[-_. A-Za-z0-9]+\\.[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Ungültiger Dateiname");
        }
    }

    public static void verzeichnisNameValidieren(String name) {
        if (!name.matches("^[a-zA-Z]:\\\\(?:[^\\\\/:*?\"<>|\\r\\n]+\\\\)*[^\\\\/:*?\"<>|\\r\\n]*$")) {
            throw new IllegalArgumentException("Ungültiger Verzeichnisname");
        }
    }
}


