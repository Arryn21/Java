import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {

    private MathUtils mathUtils;

    @BeforeEach
    void setUp() {
        mathUtils = new MathUtils();
    }

    @Test
    void testAdd() {
        assertEquals(5, mathUtils.add(2, 3));
        assertEquals(-1, mathUtils.add(-2, 1));
    }

    @Test
    void testSubtract() {
        assertEquals(1, mathUtils.subtract(3, 2));
        assertEquals(-3, mathUtils.subtract(-2, 1));
    }

    @Test
    void testMultiply() {
        assertEquals(6, mathUtils.multiply(2, 3));
        assertEquals(0, mathUtils.multiply(0, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, mathUtils.divide(6, 3));
        assertEquals(-1.0, mathUtils.divide(6, 0)); // Division by zero
    }

    @AfterEach
    void tearDown() {
        mathUtils = null;
    }
}
