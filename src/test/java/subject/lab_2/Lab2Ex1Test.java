package subject.lab_2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import subject.lab_1.Lab1Ex1;

import static org.junit.jupiter.api.Assertions.*;

class Lab2Ex1Test {

    @Test
    void findMaxOddTest() {
        int[] arr = {-1, 10, 0, -5, 2};
        int expected = -1;
        int actual = Lab2Ex1.findMaxOdd(arr);

        assertEquals(expected, actual);
    }

    @Test
    void sumAfterNegativeTestZeroNegatives() {
        int[] arr = {20, 10, 0, 4, 2};
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;
        Executable actual = () -> Lab2Ex1.findMaxOdd(arr);

        assertThrows(expected, actual);
    }
}