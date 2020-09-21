//package budget;

import java.io.*;
import java.util.*;

class Purchase {
    private final Double price;
    private final String name;
    private final Type type;

    enum Type {
        Food,
        Clothes,
        Entertainment,
        Other
    }
    public Purchase(Type type, String name, Double price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

}

public class Main {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    final static File filePurchases = new File("G:\\purchases.txt");
    static ArrayList<String> arrayFromFile = new ArrayList<>();
    static LinkedList<Purchase> list = new LinkedList<>();
    static double balance = 0.0;
    static String name;

    public static void main(String[] args) throws IOException {
        boolean b1 = false;
        showChooseAction();
//------------------------------------------------------------
//                        Main menu - 8 items
//------------------------------------------------------------
        while (!b1) {
            int if1 = choice(bufferedReader.readLine().trim());
            if (if1 == 1) {
                System.out.println();
                showIncome();
                double in = Double.parseDouble(bufferedReader.readLine());
                balance += in;
                showIncomeAdd();
                System.out.println();
                showChooseAction();

            } else if (if1 == 2) {
                System.out.println();
                showChooseType();
                Purchase.Type type;
//------------------------------------------------------------
//            Choose type in Add purchases - 5 items
//------------------------------------------------------------
                boolean b2 = false;
                while (!b2) {
                    int if2 = choice(bufferedReader.readLine().trim());
                    if (if2 == 1) {
                        type = Purchase.Type.Food;
                        addPurchase(type);
                    }
                    else if (if2 == 2) {
                        type = Purchase.Type.Clothes;
                        addPurchase(type);
                    }
                    else if (if2 == 3) {
                        type = Purchase.Type.Entertainment;
                        addPurchase(type);
                    }
                    else if (if2 == 4) {
                        type = Purchase.Type.Other;
                        addPurchase(type);
                    }
                    else if (if2 == 5) {
                        b2 = true;
                    }
                }
                showChooseAction();
            } else if (if1 == 3) {
                showChooseTypeAll();
                Purchase.Type type;
                boolean b3 = false;
//------------------------------------------------------------------------
//                Choose type purchases in Show list - 6 items
//------------------------------------------------------------------------
                while (!b3) {
                    int if3 = choice(bufferedReader.readLine().trim());
                    if (if3 == 1) {
                        type = Purchase.Type.Food;
                       showType(type);
                        System.out.println("Total: $" + sumByType(type));
                        showChooseTypeAll();
                    } else if (if3 == 2) {
                        type = Purchase.Type.Clothes;
                        showType(type);
                        System.out.println("Total: $" + sumByType(type));
                        showChooseTypeAll();
                    } else if (if3 == 3) {
                        type = Purchase.Type.Entertainment;
                        showType(type);
                        System.out.println("Total: $" + sumByType(type));
                        showChooseTypeAll();
                    } else if (if3 == 4) {
                        type = Purchase.Type.Other;
                        showType(type);
                        System.out.println("Total: $" + sumByType(type));
                        showChooseTypeAll();
                    } else if (if3 == 5) {
                        System.out.println();
                        System.out.println("All: ");
                        if (list.size() == 0) showEmpty();
                        else {double sum = 0;
                            for (Purchase items : list) {
                                System.out.println(items.getName() + " $" + String.format("%.2f",items.getPrice()));
                                sum += items.getPrice();
                            }
                            System.out.println("Total: $" + sum);}
                        showChooseTypeAll();
                    } else if (if3 == 6) {
                        b3 = true;
                    }
                }
                showChooseAction();
            }
            else if (if1 == 4) {
                if (balance < 0) {
                    balance = 0;
                }
                System.out.println("Balance: $" + balance);
                showChooseAction();
            } else if (if1 == 5) {
                try (PrintWriter print = new PrintWriter(filePurchases)) {
                    for (Purchase s : list) {
                        print.println(s.getType() + "_Y_" + s.getName() + "_Y_" + s.getPrice());
                    }
                    print.println(balance);
                    showSaved();
                }
                catch (IOException e) {
                    System.out.println("Cannot write to file: " + filePurchases.getPath());
                }
                showChooseAction();
            } else if (if1 == 6) {
                try (Scanner scanner = new Scanner(filePurchases)) {
                    while (scanner.hasNextLine()) {
                        String s = scanner.nextLine();
                        arrayFromFile.add(s);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("No file found: " + filePurchases.getPath());
                }
                balance = Double.parseDouble(arrayFromFile.get(arrayFromFile.size() - 1));
                arrayFromFile.remove(arrayFromFile.size() - 1);
                Purchase.Type ft = Purchase.Type.Food;
                for (String items:arrayFromFile) {
                    String[] f = items.split("_Y_");
                    switch (f[0]) {
                        case "Food":
                            ft = Purchase.Type.Food;
                            break;
                        case "Clothes":
                            ft = Purchase.Type.Clothes;
                            break;
                        case "Entertainment":
                            ft = Purchase.Type.Entertainment;
                            break;
                        case "Other":
                            ft = Purchase.Type.Other;
                            break;
                    }
                    list.add(new Purchase(ft, f[1], Double.parseDouble(f[2])));
                }
                showLoaded();
                showChooseAction();
            } else if (if1 == 7) {
               showChooseSort();
                boolean b4 = false;
//-----------------------------------------------------------------------
//                Menu Analyze (Sort) - 4 items
//-----------------------------------------------------------------------
                while (!b4) {
                    int if4 = choice(bufferedReader.readLine().trim());
                    if (if4 == 1) {
                        if (list.size() == 0) {
                            showEmpty();
                        }
                        else  {
                            System.out.println();
                            System.out.println("All:");
                            double sum = 0.0;
                            LinkedList<Purchase> orderList = list;
                            orderList.sort(Comparator.comparing(Purchase::getPrice).reversed());

                            for (Purchase items : orderList) {
                                System.out.println(items.getName() + " $" + String.format("%.2f", items.getPrice()));
                                sum += items.getPrice();
                            }
                            System.out.println("Total: $" + String.format("%.2f",sum));
                        }
                    showChooseSort();
                    }
                    else if (if4 == 2) {
                        double sum = 0.0;
                        System.out.println();
                        LinkedHashMap<Purchase.Type, Double> unSortedMap = new LinkedHashMap<>();
                        LinkedHashMap<Purchase.Type, Double> reverseSortedMap = new LinkedHashMap<>();

                        System.out.println("Types:");
                        for (Purchase.Type type: Purchase.Type.values()) {
                            unSortedMap.put(type, sumByType(type));
                            sum += sumByType(type);
                        }
                        unSortedMap.entrySet()
                                .stream()
                                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
                        for (var entry : reverseSortedMap.entrySet()) {
                            System.out.println(entry.getKey() + " $" + String.format("%.2f",entry.getValue()));
                        }
                        System.out.println("Total sum: $" + String.format("%.2f",sum));
                        showChooseSort();
                    }
                    else if (if4 == 3) {
                        showChooseType();
                        Purchase.Type type;
//---------------------------------------------------------------------------
//                    Choose type in Analyze - 4 items
//---------------------------------------------------------------------------
                        boolean b5 = false;
                        while (!b5) {
                            int if5 = choice(bufferedReader.readLine().trim());
                            System.out.println();
                            switch (if5) {
                                case 1: {
                                    type = Purchase.Type.Food;
                                    if (sumByType(type) == 0) {
                                        showEmpty();
                                    }
                                    else {
                                        showType(type);
                                        System.out.println("Total: $" + String.format("%.2f", sumByType(type)));
                                    }
                                    break;
                                } case 2: {
                                    type = Purchase.Type.Clothes;
                                    if (sumByType(type) == 0) {
                                        showEmpty();
                                    }
                                    else {
                                        showType(type);
                                        System.out.println("Total: $" + String.format("%.2f", sumByType(type)));
                                    }
                                    break;
                                } case 3: {
                                    type = Purchase.Type.Entertainment;
                                    if (sumByType(type) == 0) {
                                        showEmpty();
                                    }
                                    else {
                                        showType(type);
                                        System.out.println("Total: $" + String.format("%.2f", sumByType(type)));
                                    }
                                    break;
                                } case 4: {
                                    type = Purchase.Type.Other;
                                    if (sumByType(type) == 0) {
                                        showEmpty();
                                    }
                                    else {
                                        showType(type);
                                        System.out.println("Total: $" + String.format("%.2f", sumByType(type)));
                                    }
                                    break;
                                } case 5: {
                                    b5 = true;
                                    break;
                                }
                            }
                            showChooseType();
                        }
                            showChooseSort();
                        }
                    else if (if4 == 4) {
                            b4 = true;
                        }
                }
                showChooseAction();
            } else if (if1 == 0) {
                showBye();
                b1 = true;
            }
        }
    }
    private static int choice (String input) {
        return Integer.parseInt(input);
    }
    
    private static void showType (Purchase.Type type) {
        System.out.println();
        System.out.println(type + ": ");
        if (list.size() == 0) {
            showEmpty();
        } else {
                for (Purchase items : list) {
                    if (items.getType() == type) {
                        System.out.println(items.getName() + " $" + String.format("%.2f", items.getPrice()));
                    }
                }
        }
    }
    private static double sumByType (Purchase.Type type) {
        double sum = 0;
            for (Purchase items : list) {
                if (items.getType() == type) {
                    sum += items.getPrice();
                }
            }
        return  sum;
    }

    private static void addPurchase (Purchase.Type type) throws IOException {
        showAddName();
        name = bufferedReader.readLine();
        showAddPrice();
        double price = Double.parseDouble(bufferedReader.readLine());
        balance -= price;
        list.add(new Purchase(type, name, price));
        showAdded();
        showChooseType();
    }
    private static void showChooseAction() {
        System.out.println();
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
        System.out.println();
    }

    private static void showChooseSort() {
        System.out.println();
        System.out.println("How do you want to sort?");
        System.out.println("1) Sort all purchases");
        System.out.println("2) Sort by type");
        System.out.println("3) Sort certain type");
        System.out.println("4) Back");
        System.out.println();
    }

    private static void showChooseTypeAll() {
        System.out.println();
        System.out.println("Choose the type of purchases");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
        System.out.println();
    }
    private static void showChooseType() {
        System.out.println();
        System.out.println("Choose the type of purchases");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
        System.out.println();
    }

    private static void showLoaded() {
        System.out.println();
        System.out.println("Purchases were loaded!");
    }

    private static void showSaved() {
        System.out.println();
        System.out.println("Purchases were saved!");
    }


    private static void showAddName() {
        System.out.println();
        System.out.println("Enter purchase name:");
    }

    private static void showAddPrice() {
        System.out.println("Enter its price:");
    }

    private static void showAdded() {
        System.out.println("Purchase was added!");
    }

    private static void showEmpty() {
        System.out.println("Purchase list is empty!");
    }

    private static void showBye () {
        System.out.println();
        System.out.println("Bye!");
    }

    private static void showIncome() {
        System.out.println("Enter income:");
    }

    private static void showIncomeAdd () {
        System.out.println("Income was added!");
    }
}
