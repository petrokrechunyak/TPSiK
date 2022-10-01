package subject.lab_2;

import subject.library.MyUtils;
import subject.library.Program;

import java.util.Arrays;
import java.util.Random;

public class Lab2Ex1 {
    public static void main(String[] args) {
        Program program = () -> {
            int n = MyUtils.getNumFromKeyboard("Введіть довжину масиву: ");
            int[] arr = generate(n);
            System.out.println("Масив: " + Arrays.toString(arr));
            try {
                System.out.println(findMaxOdd(arr));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        };

        MyUtils.menu(program);
    }

    public static int[] generate(int n) {
        return new Random().ints(-100, 100).limit(n).toArray();
    }

    public static int findMaxOdd(int[] arr) {
        int[] odds = Arrays.stream(arr).filter(x -> x % 2 != 0).toArray();
        if(odds.length == 0)
            throw new IllegalArgumentException("В масиві немає непарних значень!");
        return Arrays.stream(odds).max().getAsInt();
    }
}
