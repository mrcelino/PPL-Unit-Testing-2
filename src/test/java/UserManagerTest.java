
import org.example.UserManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserManagerTest {

    private static UserManager userManager;

    @BeforeAll
    static void setupAll() {
        System.out.println("-- Memulai pengujian UserManager --");
        userManager = new UserManager();
    }

    @BeforeEach
    void setup() {
        System.out.println("-- Menyiapkan pengujian --");
        userManager.addUser("Admin");
    }

    @Test
    void testAddUser() {
        System.out.println("-- Menjalankan test: addUser --");
        userManager.addUser("Marcel");
        assertEquals(2, userManager.getUserCount());
        assertTrue(userManager.userExist("Marcel"));
    }

    @Test
    void testRemoveUser() {
        System.out.println("-- Menjalankan test: removeUser --");
        userManager.removeUser("Admin");
        assertEquals(0, userManager.getUserCount());
        assertFalse(userManager.userExist("Admin"));
    }

    @Test
    void testGetUserCount() {
        System.out.println("-- Menjalankan test: getUserCount --");
        assertEquals(1, userManager.getUserCount());
        userManager.addUser("Marcel");
        assertEquals(2, userManager.getUserCount());
    }

    @Test
    void testUserExist() {
        System.out.println("-- Menjalankan test: userExist --");
        assertTrue(userManager.userExist("Admin"));
        assertFalse(userManager.userExist("Unknown"));
    }

    @Test
    void testClearUsers() {
        System.out.println("-- Menjalankan test: clearUsers --");
        userManager.addUser("Don");
        userManager.addUser("Jon");
        assertEquals(3, userManager.getUserCount());

        userManager.clearUsers();
        assertEquals(0, userManager.getUserCount());
    }

    @AfterEach
    void cleanUp() {
        System.out.println("-- Clean Up --");
        userManager.clearUsers();
    }

    @AfterAll
    static void cleanUpAll() {
        System.out.println("-- Pengujian UserManager selesai --");
        userManager = null;
    }
}