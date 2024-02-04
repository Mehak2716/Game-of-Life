package org.example;

import org.example.gameoflife.Game;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows");
        int numRows = scanner.nextInt();

        System.out.println("Enter the number of columns");
        int numColumns = scanner.nextInt();

        System.out.println("Enter the seed percent");
        int seedPercent = scanner.nextInt();

        Game game = new Game(numRows,numColumns,seedPercent);
        game.start();
    }
}