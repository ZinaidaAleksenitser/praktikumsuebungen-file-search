import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Scanner;

/*2.2.d) Prüfe bei der Eingabe fehlerhafte Eingaben (ohne Exception Handling) und frage nach einer erneuten validen Eingabe.
Passe entsprechend deine Testklasse an.
*/

public class DateisucheOhneExceptionTest {

    @Test
    void testGueltigerVerzechnisName(){
        Scanner scanner = new Scanner("C:\\Temp");
        File name = DateisucheOhneException.verzeichnisNameEingeben(scanner);
        //System.out.println(name.getPath());
        assertEquals("C:\\Temp", name.getPath());
    }

    @Test
    void testUngueltigerVerzeichnisName(){
        Scanner scanner = new Scanner("abc\n" +
                        "xyz\n" +
                        "C:\\Temp");
        File name = DateisucheOhneException.verzeichnisNameEingeben(scanner);
        assertEquals("C:\\Temp", name.getPath());
    }

    @Test
    void testGueltigerDateiName(){
        Scanner scanner = new Scanner("text.pdf");
        String name = DateisucheOhneException.dateiNameEingeben(scanner);
        assertEquals("text.pdf", name);
    }

    @Test
    void testUngueltigerDateiname(){
        Scanner scanner = new Scanner("ttt\n" + "text.pdf");
        String name = DateisucheOhneException.dateiNameEingeben(scanner);
        assertEquals("text.pdf", name);
    }
}
