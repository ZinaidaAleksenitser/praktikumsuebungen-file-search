package SearchWithThread;

import java.io.File;

public class SearchService implements Runnable{

    private final String dateiName;
    private final File verzeichnis;

    public SearchService(String dateiName, File verzeichnis) {
        this.dateiName = dateiName;
        this.verzeichnis = verzeichnis;
    }
    @Override
    public void run() {
        sucheOption(dateiName, verzeichnis);
    }

    private void sucheOption(String dateiName, File directory){
        File[] files = directory.listFiles();

        if (files == null) {
            System.out.println("Verzeichnis kann nicht gelesen werden:" + directory.getAbsolutePath());
            return;
        }
        for(File datei: files){
            if(datei.isDirectory()){
                Thread nextThread = new Thread(new SearchService(dateiName, datei));
                nextThread.start();
            }
            else if (datei.getName().equalsIgnoreCase(dateiName)){
                System.out.println("Datei ist gefunden: " + datei.getAbsolutePath());
            }
        }

    }
}
