package subject.lab_1;

import subject.library.Program;
import subject.library.MyUtils;

import java.util.Arrays;
import java.util.Random;

public class Lab1Ex1 {
    public static void main(String[] args) {
        Program program = () -> {
            int n = MyUtils.getNumFromKeyboard("Введіть довжину масиву: ");
            int[] arr = generate(n);
            System.out.println("Масив: " + Arrays.toString(arr));
            System.out.println("Кількість від'ємних елементів масиву: " + countNegatives(arr));
            try {
                System.out.println("Cума елементів масиву, розташованих після мінімального елемента: " + sumAfterNegative(arr));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        };

        MyUtils.menu(program);
    }

    public static int[] generate(int n) {
        return new Random().ints(-100, 100).limit(n).toArray();
    }

    public static int countNegatives(int[] arr) {
        return (int) Arrays.stream(arr).filter(x -> x < 0).count();
    }

    public static int sumAfterNegative(int[] arr) {
        boolean[] add = {false};
        int[] sum = {0};
        Arrays.stream(arr).forEach(x -> {
            sum[0] += add[0] ? x : 0;
            add[0] = x < 0 || add[0];
        });
        if(!add[0])
            throw new IllegalArgumentException("В масиві немає від'ємних чисел!");
        return sum[0];
    }
}
