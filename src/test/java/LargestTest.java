import org.example.Largest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LargestTest {
    @Test
    void testLargestWithPositiveNumbers() {
        int[] numbers = {1, 4, 2, 8, 24};
        Assertions.assertEquals(24, Largest.largest(numbers), "Nilai terbesar harusnya 24");
    }
}
