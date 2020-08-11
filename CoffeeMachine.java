package machine;

import java.util.Scanner;

public class CoffeeMachine {
    enum Coffee {
        ESPRESSO (250, 0, 16, 4, 1),
        LATTE (350, 75, 20, 7, 1),
        CAPPUCCINO (200, 100, 12, 6, 1);

        int water, milk, beans, money, cups;

        Coffee(int water, int milk, int beans, int money, int cups) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.money = money;
            this.cups = cups;
        }
    }

    private static void printStates(int water, int milk, int beans, int money, int cups) {
        System.out.println ("The coffee machine has:");
        System.out.println (water + " of water");
        System.out.println (milk + " of milk");
        System.out.println (beans + " of coffee beans");
        System.out.println (cups + " of disposable cups");
        System.out.println (money + " of money");
        System.out.println ();
    }

    private static void chooseString(){
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    private static void noCoffee(){
        System.out.println("I have enough resources, making you a coffee!");
        System.out.println();
    }
    private static void noWater(){
        System.out.println("Sorry, not enough water!");
        System.out.println();
    }
    private static void noMilk(){
        System.out.println("Sorry, not enough milk!");
        System.out.println();
    }
    private static void noBeans(){
        System.out.println("Sorry, not enough coffee beans!");
        System.out.println();
    }
    private static void noCups(){
        System.out.println("Sorry, not enough disposable cups!");
        System.out.println();
    }

    private static void whatBuy(){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    }

    public static void main(String[] args) {
        int water = 400;
        int milk = 540;
        int beans = 120;
        int money = 550;
        int cups = 9;

        chooseString();
        Scanner scanner = new Scanner(System.in);
        String choose = scanner.next();

        while (!choose.equals("exit")) {
            switch (choose) {
                case "buy":
                    whatBuy();
                    String buy = scanner.next();
                    switch (buy) {
                        case "1":
                            if (water >= Coffee.ESPRESSO.water && beans >= Coffee.ESPRESSO.beans && cups >= Coffee.ESPRESSO.cups) {
                                noCoffee();
                                water -= Coffee.ESPRESSO.water;
                                beans -= Coffee.ESPRESSO.beans;
                                money += Coffee.ESPRESSO.money;
                                cups -= Coffee.ESPRESSO.cups;
                            }
                            else if (water < Coffee.ESPRESSO.water) noWater();
                            else if (beans < Coffee.ESPRESSO.beans) noBeans();
                            else if (cups < Coffee.ESPRESSO.cups) noCups();
                            chooseString();
                            choose = scanner.next();
                            break;
                        case "2":
                            if (water >= Coffee.LATTE.water && milk >= Coffee.LATTE.milk && beans >= Coffee.LATTE.beans && cups >= Coffee.LATTE.cups) {
                                noCoffee();
                                water -= Coffee.LATTE.water;
                                milk -= Coffee.LATTE.milk;
                                beans -= Coffee.LATTE.beans;
                                money += Coffee.LATTE.money;
                                cups -= Coffee.LATTE.cups;}
                            else if (water < Coffee.LATTE.water) noWater();
                            else if (milk < Coffee.LATTE.milk) noMilk();
                            else if (beans < Coffee.LATTE.beans) noBeans();
                            else if (cups < Coffee.LATTE.cups) noCups();

                            chooseString();
                            choose = scanner.next();
                            break;
                        case "3":
                            if (water >= Coffee.CAPPUCCINO.water && milk >= Coffee.CAPPUCCINO.milk && beans >= Coffee.CAPPUCCINO.beans && cups >= Coffee.CAPPUCCINO.cups) {
                                noCoffee();
                                water -= Coffee.CAPPUCCINO.water;
                                milk -= Coffee.CAPPUCCINO.milk;
                                beans -= Coffee.CAPPUCCINO.beans;
                                money += Coffee.CAPPUCCINO.money;
                                cups -= Coffee.CAPPUCCINO.cups;}
                            else if (water < Coffee.CAPPUCCINO.water) noWater();
                            else if (milk < Coffee.CAPPUCCINO.milk) noMilk();
                            else if (beans < Coffee.CAPPUCCINO.beans) noBeans();
                            else if (cups < Coffee.CAPPUCCINO.cups)  noCups();
                            chooseString();
                            choose = scanner.next();
                            break;
                        case "back":
                            chooseString();
                            choose = scanner.next();
                            break;
                    }
                    break;
                case "fill":
                    System.out.println("Write how many ml of water do you want to add: ");
                    water += scanner.nextInt();
                    System.out.println("Write how many ml of milk do you want to add: ");
                    milk += scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add: ");
                    beans += scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add: ");
                    cups += scanner.nextInt();
                    chooseString();
                    choose = scanner.next();
                    break;
                case "take":
                    System.out.println("I gave you $" + money);
                    money = 0;
                    chooseString();
                    choose = scanner.next();
                    break;
                case "remaining":
                    printStates(water, milk, beans, money, cups);
                    chooseString();
                    choose = scanner.next();
                    break;
            }
        }
    }

}
