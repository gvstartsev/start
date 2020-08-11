package tictactoe;
import java.util.Scanner;
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char[][] ticTacToe = new char[3][3];
    private static int coordinateX, coordinateY;

    public static void main(String[] args) {
        fillTable();
        printTable();
        while (true) {
            getCoordinates('X');
            if (checkWin('X', ticTacToe)) {
                printTable();
                System.out.println("X wins");
                break;}
            printTable();
            getCoordinates('O');
            if (checkWin('O', ticTacToe)) {
                printTable();
                System.out.println("O wins");
                break;
            }
            printTable();
        }
    }

    private static void fillTable(){

        String cells = "         ";
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToe[i][j] = cells.charAt(k);
                k++;
            }
        }
    }

    private static void getCoordinates(char cross){
        boolean answer = true;
        do {
            System.out.println("Enter the coordinates:");
            try {
                coordinateX = scanner.nextInt();
                coordinateY = scanner.nextInt();

            } catch (Exception err) {
                System.out.println("You should enter numbers!");
            }
            if (coordinateX > 3 || coordinateX < 1 || coordinateY > 3 || coordinateY < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (ticTacToe[3 - coordinateY][coordinateX - 1] == 'X' ||
                    ticTacToe[3 - coordinateY][coordinateX - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                ticTacToe[3 - coordinateY][coordinateX - 1] = cross;
                answer = false;
                System.out.println();

            }

        } while (answer);
    }

    private static void printTable(){
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(ticTacToe[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static boolean checkWin(char player, char[][] board) {
        return (board[0][0] == player && board[0][1] == player && board[0][2] == player)
                || (board[1][0] == player && board[1][1] == player && board[1][2] == player)
                || (board[2][0] == player && board[2][1] == player && board[2][2] == player)
                || (board[0][0] == player && board[1][0] == player && board[2][0] == player)
                || (board[0][1] == player && board[1][1] == player && board[2][1] == player)
                || (board[0][2] == player && board[1][2] == player && board[2][2] == player)
                || (board[0][0] == player && board[1][1] == player && board[2][2] == player)
                || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }
}
