package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumberParser extends AbstractDataParser<Long> implements Sortable {
    Scanner sc = new Scanner(System.in);
    Scanner scan = null;

    public NumberParser(String typeOfSort) {
        super(typeOfSort);
        dataType = "numbers";
    }
    @Override
    public void close() {
        sc.close();
        scan.close();
    }

    @Override
    public void read() {
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.matches("[-+]?\\d+")) {
                list.add(Long.parseLong(input));
            } else {
                System.out.println("\"" + input + "\" isn't a long. It's skipped.");
            }
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
            String input = scan.next();
            if (input.matches("[-+]?\\d+")) {
                list.add(Long.parseLong(input));
            } else {
                System.out.println("\"" + input + "\" isn't a long. It's skipped.");
            }
        }
    }
}