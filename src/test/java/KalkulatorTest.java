import org.example.Kalkulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KalkulatorTest {
    @Test
    void testTambah() {
        int a = 10;
        int b = 5;

        Kalkulator calc = new Kalkulator(a, b);
        Assertions.assertEquals(15, calc.tambah(), "Equal tambah");
    }

    @Test
    void testKalkulatorNotNull() {
        Kalkulator calc = new Kalkulator(10, 5);
        Assertions.assertNotNull(calc, "Objek kalkulator tidak boleh null");
    }

    @Test
    void testTambahComplete() {
        Kalkulator calc = new Kalkulator(10, 5);
        Assertions.assertAll(
                () -> Assertions.assertEquals(10, calc.a, "Nilai a sesuai"),
                () -> Assertions.assertEquals(5, calc.b, "Nilai b sesuai"),
                () -> Assertions.assertEquals(15, calc.tambah(), "Hasil tambah sesuai")
        );
    }
}
