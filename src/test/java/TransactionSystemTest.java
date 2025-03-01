import org.example.TransactionSystem;
import org.junit.jupiter.api.*;

public class TransactionSystemTest {

    private static TransactionSystem ts;

    @BeforeAll
    static void setup(){
        TransactionSystem.openConnection();
        ts = new TransactionSystem(500000);
    }

    @BeforeEach
    void setupMethod(){
        ts.resetBalance(500000);
    }

    @Test
    void testDeposit(){
        ts.deposit(100000);

        Assertions.assertEquals(600000, ts.getBalance());
    }

    @Test
    void testWithdraw(){
        ts.withdraw(100000);

        Assertions.assertEquals(400000, ts.getBalance());
    }

    @AfterEach
    void cleanupMethod(){
        //
    }

    @AfterAll
    static void cleanup(){
        ts = null;
        TransactionSystem.closeConnection();
    }
}
