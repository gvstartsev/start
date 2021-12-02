package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LineParser extends AbstractDataParser<String> implements Sortable {
    Scanner sc = new Scanner(System.in);
    Scanner scan = null;

    public LineParser(String typeOfSort) {
        super(typeOfSort);
        dataType = "lines";
    }
    @Override
    public void close() {
        sc.close();
        scan.close();
    }

    @Override
    public void read() {
        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
    }

    @Override
    public void fromFile(String inputFileName) {

        try {
            scan = new Scanner(new File(inputFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            list.add(sc.nextLine());
        }
    }
}