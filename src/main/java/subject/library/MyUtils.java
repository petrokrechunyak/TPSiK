package subject.library;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyUtils {

    public static int soutMenu(String text) {
        System.out.println(text);
        int choice = getNumFromKeyboard("");
        System.out.println("************************");
        return choice;
    }

    public static int getNumFromKeyboard(String text) {
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print(text);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Вводити можна тільки числа!");
            }
        } while (true);
    }

    public static void menu(Program taskCode) {
        boolean flag = false;
        do {
            int menu = MyUtils.soutMenu("\t\tМеню\n" +
                    "1 - Перейти до виконання програми\n" +
                    "2 - Вийти");
            switch (menu) {
                case 1:
                    // Головна програма
                    taskCode.menu();
                    break;
                case 2:
                    flag = true;
                    break;
                default:
                    continue;
            }
            if(flag)
                break;
            System.out.println("************************");
        } while (true);
    }

}
