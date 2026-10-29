import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.io.File;
import java.time.Duration;


/*2.2.e) Recherchiere im Netz nach durchschnittlichen Zeiten für die Suche.
Schreibe basierende auf dieser Recherche eine Testklassen, die prüft, ob dein Programm innerhalb der akzeptierten Zeit liegt.

 */

public class DateisucheZeitTest {
    @Test
    void zeitTest(){
        assertTimeout(Duration.ofMillis(200), ()->{
            File ordner = new File ("C:\\Users\\zaleksenitser\\Desktop");
            Dateisuche.dateiSuche("test2.txt", ordner);
        });
    }
}

