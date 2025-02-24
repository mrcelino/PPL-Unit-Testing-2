import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MethodTest {

    @Test
    void testAssertNull() {
        String str = null;
        Assertions.assertNull(str, "Object should be null");
    }

    @Test
    void testAssertNotNull() {
        String str = "Hello";
        Assertions.assertNotNull(str, "Object should not be null");
    }

    @Test
    void testAssertTrue() {
        boolean isActive = 10 > 5;
        Assertions.assertTrue(isActive, "Condition should be true");
    }

    @Test
    void testAssertFalse() {
        boolean isInactive = 5 > 10;
        Assertions.assertFalse(isInactive, "Condition should be false");
    }

    @Test
    void testAssertSame() {
        String str1 = "JUnit";
        String str2 = "JUnit";
        Assertions.assertSame(str1, str2, "Objects should be the same reference");
    }

    @Test
    void testAssertNotSame() {
        String str1 = new String("JUnit");
        String str2 = new String("JUnit");
        Assertions.assertNotSame(str1, str2, "Objects should not be the same reference");
    }
}
