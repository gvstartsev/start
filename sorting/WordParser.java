package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordParser extends AbstractDataParser<String> implements Sortable {
    Scanner sc = new Scanner(System.in);
    Scanner scan = null;

    public WordParser(String typeOfSort) {
        super(typeOfSort);
        dataType = "words";
    }
    @Override
    public void close() {
        sc.close();
        scan.close();
    }
    @Override
    public void read() {
        while (sc.hasNext()) {
            list.add(sc.next());
        }
    }

    @Override
    public void fromFile(String inputFileName) {
        try {
            scan = new Scanner(new File(inputFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            list.add(sc.next());
        }
    }
}