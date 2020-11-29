import java.util.Scanner;

public class Cinema {
    private static int profit(int rows, int seatsInRow) {
        int total;
        if (rows * seatsInRow > 60) {
            int frontHalf = Math.round(rows / 2);
            total = frontHalf * seatsInRow * 10 + (rows - frontHalf) * seatsInRow * 8;
        } else {
            total = rows * seatsInRow * 10;
        }
        return total;
    }

    public static void main(String[] args) {
        final String menu = "\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = sc.nextInt();
        char[][] cinema = initCinema(rows, seatsInRow);
        System.out.println(menu);
        int choose;
        boolean exit = false;
        while (!exit) {
            choose = sc.nextInt();
            if (choose == 0) {
                exit = true;
            } else if (choose == 1) {
                showFactCinema(cinema);
                System.out.println(menu);
            } else if (choose == 2) {
                boolean input = false;
                int row = 0;
                int seat = 0;
                while (!input) {
                    System.out.println("\nEnter a row number:");
                    row = sc.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    seat = sc.nextInt();
                    if ((row > rows || row < 1) || (seat > seatsInRow || seat < 1)) {
                        System.out.println("\nWrong input!");
                    } else if (cinema[row][seat] == 'B') {
                        System.out.println("\nThat ticket has already been purchased!");
                    } else {
                        input = true;
                    }
                }
                buyTicket(row, seat, cinema);
                System.out.println(menu);
            } else if (choose == 3) {
                showStat(cinema);
                System.out.println(menu);
            }
        }
    }

    private static void buyTicket(int row, int seat, char[][] cinema) {
        cinema[row][seat] = 'B';
        if ((cinema.length - 1) * (cinema[0].length - 1) > 60) {
            if (Math.round((cinema.length - 1) / 2) < row) {
                System.out.println("\nTicket price: $8");
            } else {
                System.out.println("\nTicket price: $10");
            }

        } else {
            System.out.println("\nTicket price: $10");
        }
    }


    private static void showStat(char[][] cinema) {
        double per = 100 * (double) countB(cinema) / ((cinema.length - 1) * (cinema[0].length - 1));
        String percent = String.format("%.2f", per) + "%";
        System.out.println("Number of purchased tickets: " + countB(cinema));
        System.out.println("Percentage: " + percent);
        System.out.println("Current income: $" + income(cinema));
        System.out.println("Total income: $" + profit(cinema.length - 1, cinema[0].length - 1));
    }

    private static int income(char[][] cinema) {
        int income = 0;
        if ((cinema.length - 1) * (cinema[0].length - 1) > 60) {
            for (int i = 1; i < cinema.length; i++) {
                for (int j = 1; j < cinema[0].length; j++) {
                    if (cinema[i][j] == 'B') {
                        if ((Math.round((double) (cinema.length - 1) / 2) > i)) {
                            income += 10;
                        } else {
                            income += 8;
                        }
                    }
                }
            }
        } else {
            income = countB(cinema) * 10;
        }
        return income;
    }

    private static int countB(char[][] cinema) {
        int count = 0;
        for (int i = 1; i < cinema.length; i++) {
            for (int j = 1; j < cinema[0].length; j++) {
                if (cinema[i][j] == 'B') {
                    count++;
                }
            }
        }
        return count;
    }

    private static void showFactCinema(char[][] cinema) {
        StringBuffer firstString = new StringBuffer();
        firstString.append("  ");
        for (int i = 1; i < cinema[0].length; i++) {
            firstString.append(i).append(" ");
        }
        System.out.println("Cinema:");
        System.out.println(firstString);
        for (int i = 1; i < cinema.length; i++) {
            System.out.print(i + " ");
            for (int j = 1; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static char[][] initCinema(int rows, int seatsInRow) {
        char[][] cinema = new char[rows + 1][seatsInRow + 1];
        cinema[0][0] = ' ';
        for (int i = 1; i <= seatsInRow; i++) {
            cinema[0][i] = '_';
        }
        for (int i = 1; i <= rows; i++) {
            cinema[i][0] = '|';
        }
        for (int i = 1; i < cinema.length; i++) {
            for (int j = 1; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
            }
        }
        return cinema;
    }

}
