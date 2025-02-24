import org.example.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentTest {
    @Test
    void testIsDoingMBKM_True() {
        Student student = new Student("Budi", 6, true);
        Assertions.assertTrue(student.isDoingMBKM(), "Mahasiswa bisa mengikuti MBKM");
    }

    @Test
    void testIsDoingMBKM_False_Inactive() {
        Student student = new Student("Ayu", 6, false);
        Assertions.assertFalse(student.isDoingMBKM(), "Mahasiswa tidak aktif tidak bisa MBKM");
    }

    @Test
    void testIsDoingMBKM_False_LowSemester() {
        Student student = new Student("Rudi", 4, true);
        Assertions.assertFalse(student.isDoingMBKM(), "Mahasiswa semester rendah tidak bisa MBKM");
    }



    @Test
    void testStudentProperties() {
        Student student = new Student("Siti", 7, true);
        Assertions.assertAll(
                () -> Assertions.assertEquals("Siti", student.getName(), "Nama sesuai"),
                () -> Assertions.assertEquals(7, student.getSemester(), "Semester sesuai"),
                () -> Assertions.assertTrue(student.isActive(), "Mahasiswa harus aktif")
        );
    }
}
