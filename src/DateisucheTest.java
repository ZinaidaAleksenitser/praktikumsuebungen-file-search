import org.junit.jupiter.api.Test;
import  static org.junit.jupiter.api.Assertions.*;

/*Benutze Exception Handling für fehlerhafte Eingaben.
Schreibe eine Testklasse mit 2 Testfällen jeweils für das Basisverzeichnis und den Dateinamen (gültig / ungültig)
*/

public class DateisucheTest {
    @Test
    void testGueltigerDateiname(){
        assertDoesNotThrow(()-> Dateisuche.dateiNameValidieren("test.txt"));
        //no exception is expected
    }

    @Test
    void testUngueltigerDateiname(){
        assertThrows(IllegalArgumentException.class, () -> Dateisuche.dateiNameValidieren("test"));
    }

    @Test
    void testGueltigerVerzeichnisName(){
        assertDoesNotThrow(()-> Dateisuche.verzeichnisNameValidieren("C:\\Users\\zaleksenitser\\Desktop"));
    }

    @Test
    void testUngueltigerVerzeichnisName(){
        assertThrows(IllegalArgumentException.class, ()-> Dateisuche.verzeichnisNameValidieren("test"));
    }
}
