package subject.lab_1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class Lab1Ex1Test {

    @Test
    void generateTest() {
        int[] arr = Lab1Ex1.generate(9);
        int expected = 9;
        int actual = arr.length;

        assertEquals(expected, actual);
    }

    @Test
    void countNegativesTest() {
        int[] arr = {-1, 10, 0, -5, 2};
        int expected = 2;
        int actual = Lab1Ex1.countNegatives(arr);

        assertEquals(expected, actual);
    }

    @Test
    void sumAfterNegativeTest() {
        int[] arr = {-1, 10, 0, -5, 2};
        int expected = 7;
        int actual = Lab1Ex1.sumAfterNegative(arr);

        assertEquals(expected, actual);
    }

    @Test
    void sumAfterNegativeTestZeroNegatives() {
        int[] arr = {20, 10, 0, 4, 2};
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;
        Executable actual = () -> Lab1Ex1.sumAfterNegative(arr);

        assertThrows(expected, actual);
    }

}