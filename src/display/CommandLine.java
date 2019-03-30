package display;

import logic.Board;

import java.awt.*;
import java.util.Scanner;
import java.util.function.BooleanSupplier;

public class CommandLine {
    static String startPiecesLocaations = "rnbqkbnrpppppppp################################PPPPPPPPRNBKQBNR1";
    static Board board;
    int y = 0, x = 0;
    static char[][] table;
    public static void main(String[] args) {
        table = new char[board.BOARD_SIZE][board.BOARD_SIZE];
        board = new Board(startPiecesLocaations);
        boolean exit = false, coloredTurn = false;
        putStrInArray();
        printTable();
        while(!exit) {
            coloredTurn = !coloredTurn;
            System.out.println("\nIt is" + coloredTurn + " turn\n");
            Scanner scanner = new Scanner(System.in);
            System.out.println("if you want to exit enter -1 or Enter startX: ");
            int startX = scanner.nextInt();
            System.out.println("Enter startY: ");
            int startY = scanner.nextInt();
            System.out.println("Enter endX: ");
            int endX = scanner.nextInt();
            System.out.println("Enter endY: ");
            int endY = scanner.nextInt();
            if (startX == -1) {
                exit = true;
            } else {
                Point p1 = new Point(startX, startY);
                Point p2 = new Point(endX, endY);
                char[][] holder = board.manageMove(p1, p2);
                if(holder != null)
                {
                    table = holder;
                }
                printTable();
            }
        }
    }
    static void putStrInArray()
    {
        for (int i = 0; i < Board.BOARD_SIZE; i++)
        {
            for (int j = 0; j < Board.BOARD_SIZE; j++)
            {
                table[i][j] = startPiecesLocaations.charAt(i * board.BOARD_SIZE + j);
            }
        }
    }
    static void printTable()
    {
        for (int i = 0; i < Board.BOARD_SIZE; i++)
        {
            System.out.println();
            for (int j = 0; j < Board.BOARD_SIZE; j++)
            {
                System.out.print(", " + table[i][j]);
            }
        }
    }
}
