package subject.lab_3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lab3ControllerTest {

    @Test
    void widthByDotes() {
        double x1 = 1;
        double y1 = 2;
        double x2 = 3;
        double y2 = 3;
        assertEquals(2.237, Lab3Controller.widthByDotes(x1, y1, x2, y2));
    }

    @Test
    void perimeter() {
        double A = 2;
        double B = 4;
        double C = 5;
        assertEquals(11, Lab3Controller.perimeter(A, B, C));
    }

    @Test
    void square() {
        double A = 3;
        double B = 4;
        double C = 5;
        assertEquals(6, Lab3Controller.square(A, B, C));
    }

    @Test
    void classmate_null() {
        assertEquals(null, Lab3Controller.classmate("something"));
    }

    @Test
    void classmate_present() {
        assertEquals("Михайло", Lab3Controller.classmate("Софроній"));
    }
}