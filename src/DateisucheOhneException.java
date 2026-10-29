import java.awt.Desktop;
import java.io.IOException;
import java.lang.String;
import java.util.Scanner;
import java.io.File;
import java.util.logging.Logger;

/*2.2.d) Prüfe bei der Eingabe fehlerhafte Eingaben (ohne Exception Handling) und frage nach einer erneuten validen Eingabe.
Passe entsprechend deine Testklasse an.
*/


public class DateisucheOhneException {
    private static final Logger logger = Logger.getLogger(Dateisuche.class.getName());

    void main() {

        System.setProperty("java.util.logging.SimpleFormatter.format", "%1StT [%4$s] %5$s%n");
        logger.info("Programm gestartet (ohne Exception).");
        Scanner scanner = new Scanner(System.in);
        String dateiName = dateiNameEingeben(scanner);
        File verzeichnisName = verzeichnisNameEingeben(scanner);
        boolean result = dateiSuche(dateiName, verzeichnisName);
        if (!result) System.out.println("Die Datei wurde nicht gefunden");

    }


    static boolean dateiSuche(String dateiName, File verzeichnisName) {
        logger.info("Die Dateisuche wurde gestartet");
        long startZeit = System.nanoTime();
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
                long endZeit = System.nanoTime();
                logger.info("Datei ist gefunden: " + datei.getAbsolutePath());
                logger.info("Suchdauer: " + ((endZeit - startZeit) / 1000) + " ms");
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

    static String dateiNameEingeben(Scanner scanner) {
        boolean dateiNameEingabeFortsetzen;
        String dateiName;
        do {
            dateiNameEingabeFortsetzen = true;
            System.out.println("Dateiname eingben: ");
            dateiName = scanner.nextLine();
            logger.info("Dateiname wurde eingegeben");
            if (!dateiName.matches("[-_. A-Za-z0-9]+\\.[A-Za-z0-9]+")) {
                System.out.println("Bitte giben Sie einen gültigen Dateinamen ein.");
                dateiNameEingabeFortsetzen = false;
            }
        } while (!dateiNameEingabeFortsetzen);
        return dateiName;
    }

    static File verzeichnisNameEingeben(Scanner scanner) {
        boolean verzechnisNameEingabeFortsetzen;
        String verzeichnisName;
        do {
            verzechnisNameEingabeFortsetzen = true;
            System.out.println("Verzeichnisname eingeben: ");
            verzeichnisName = scanner.nextLine();
            logger.info("Verzeichnisname wurde eingegeben");
            if (!verzeichnisName.matches("^[a-zA-Z]:\\\\(?:[^\\\\/:*?\"<>|\\r\\n]+\\\\)*[^\\\\/:*?\"<>|\\r\\n]*$")) {
                System.out.println("Bitte giben Sie einen gültigen Verzeichnis ein.");
                verzechnisNameEingabeFortsetzen = false;
            }
        } while (!verzechnisNameEingabeFortsetzen);
        return new File(verzeichnisName);
    }
}


