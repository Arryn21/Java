import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MathUtilsTest {

    private MathUtils mathUtils;

    @BeforeEach
    void setUp() {
        mathUtils = new MathUtils();
    }

    @Test
    void testAdd() {
        Assertions.assertEquals(5, mathUtils.add(2, 3));
        Assertions.assertEquals(-1, mathUtils.add(-2, 1));
    }

    @Test
    void testSubtract() {
        Assertions.assertEquals(1, mathUtils.subtract(3, 2));
        Assertions.assertEquals(-3, mathUtils.subtract(-2, 1));
    }

    @Test
    void testMultiply() {
        Assertions.assertEquals(6, mathUtils.multiply(2, 3));
        Assertions.assertEquals(0, mathUtils.multiply(0, 3));
    }

    @Test
    void testDivide() {
        Assertions.assertEquals(2.0, mathUtils.divide(6, 3));
        Assertions.assertEquals(-1.0, mathUtils.divide(6, 0)); // Division by zero
    }

    @AfterEach
    void tearDown() {
        mathUtils = null;
    }
}
