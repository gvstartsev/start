package sorting;

public interface Sortable {

    void sort();

    void read();

    void output();

    void close();

    void toFile(String outputFileName);

    void fromFile(String inputFileName);
}