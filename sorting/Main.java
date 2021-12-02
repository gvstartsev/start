package sorting;

import java.util.*;

public class Main {

    public static void main(final String[] args) {
        Map<String, String> params = new HashMap<>();
        int size = args.length;

        for (int i = 0; i < size; i++) {

            if (args[i].matches("-(?!dataType|sortingType|inputFile|outputFile)(\\S*)?")) {
                System.out.println("\"" + args[i] + "\"" + " isn't a valid parameter. It's skipped.");
                continue;
            }

            if ("-dataType".equals(args[i])) {
                if (size == 1 || i == size - 1 || args[i + 1].contains("-")) {
                    System.out.println("No data type defined!");
                    return;
                } else {
                    params.put(args[i], args[i + 1]);
                }
            } else if ("-sortingType".equals(args[i])) {
                if (size == 1 || i == size - 1 || args[i + 1].contains("-")) {
                    System.out.println("No sorting type defined!");
                    return;
                } else {
                    params.put(args[i], args[i + 1]);
                }
            } else if ("-inputFile".equals(args[i])) {
                if (size == 1 || i == size - 1) {
                    System.out.println("No file name!");
                    return;
                } else {
                    params.put(args[i], args[i + 1]);
                }
            } else if ("-outputFile".equals(args[i])) {
                if (size == 1 || i == size - 1) {
                    System.out.println("No file name!");
                    return;
                } else {
                    params.put(args[i], args[i + 1]);
                }
            }
        }


        String dataType = params.get("-dataType") == null ? "line" : params.get("-dataType");
        String sortType = params.get("-sortingType") == null ? "natural" : params.get("-sortingType");
        String inputFileName;
        String outputFileName;
        Sortable input = ParserCreator.create(dataType, sortType);
        try {
            inputFileName = params.get("-inputFile");
            input.fromFile(inputFileName);

        } catch (Exception ignored) {

        }
        input.read();
        input.sort();
        try {
            outputFileName = params.get("-outputFile");
            input.toFile(outputFileName);
        } catch (Exception ignored) {
            input.output();
        }
        try {
            input.close();
        }
        catch (Exception ignore) {

        }

    }
}