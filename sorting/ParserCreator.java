package sorting;

public class ParserCreator {
    public static Sortable create(String data, String sort){
        Sortable sorter;
        switch (data) {
            case "long":
                sorter = new NumberParser(sort);
                break;
            case "line":
                sorter = new LineParser(sort);
                break;
            case "word":
                sorter = new WordParser(sort);
                break;
            default:
                sorter = null;
                break;
        }
        return sorter;
    }
}