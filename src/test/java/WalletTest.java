import org.example.Wallet;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class WalletTest {
    private static Wallet wallet;

    @BeforeAll
    public static void initAll() {
        wallet = new Wallet();
        System.out.println("Pengujian dimulai");
    }

    @BeforeEach
    public void setUp() {
        assertNotNull(wallet, "Wallet tidak boleh null sebelum test dimulai");
        if (!"Admin".equals(wallet.getOwner())) {
            wallet.setOwner("Admin");
        }
    }

    @Test
    public void testSetOwner() {
        wallet.setOwner("Marcel");
        assertNotNull(wallet.getOwner(), "Owner tidak boleh null");
        assertEquals("Marcel", wallet.getOwner(), "Nama owner harus sesuai");
    }

    @Test
    public void testAddCard() {
        wallet.addCard("KTM");
        wallet.addCard("SIM");
        wallet.addCard("KTP");
        List<String> cards = wallet.getCards();
        assertTrue(cards.contains("SIM"), "Kartu harus ada dalam list");
        assertFalse(cards.contains("KIS"), "Kartu tidak ada dalam list");
    }

    @Test
    public void testRemoveCard() {
        wallet.addCard("KTM");
        boolean result = wallet.removeCard("KTM");
        assertTrue(result, "Pengambilan kartu yang ada harus berhasil");
        assertFalse(wallet.getCards().contains("KTM"), "Kartu harusnya tidak ada setelah dihapus");

        result = wallet.removeCard("Kartu Debit");
        assertFalse(result, "Kartu tidak ada harus gagal");
    }

    @Test
    public void testAddMoneyValid() {
        assertTrue(wallet.addMoney(10000), "Penambahan uang kertas harus berhasil");
        assertTrue(wallet.getMoney().contains(10000), "Uang kertas harus ada");
        assertFalse(wallet.addMoney(3000), "Uang kertas tidak valid harus gagal");
    }

    @Test
    public void testAddCoinValid() {
        assertTrue(wallet.addCoin(500), "Penambahan koin harus berhasil");
        assertTrue(wallet.getCoins().contains(500), "Koin harus ada");
        assertFalse(wallet.addCoin(200), "Koin tidak valid harus gagal");
    }

    @Test
    public void testWithdrawMoney() {
        wallet.addMoney(20000);
        assertTrue(wallet.withdrawMoney(20000), "Penarikan uang kertas harus berhasil");
        assertFalse(wallet.getMoney().contains(20000), "Uang kertas harus hilang setelah ditarik");
        assertFalse(wallet.withdrawMoney(5000), "Penarikan uang kertas yang tidak ada harus gagal");
    }

    @Test
    public void testWithdrawCoin() {
        wallet.addCoin(1000);
        assertTrue(wallet.withdrawCoin(1000), "Penarikan koin harus berhasil");
        assertFalse(wallet.getCoins().contains(1000), "Koin harus hilang setelah ditarik");
        assertFalse(wallet.withdrawCoin(500), "Penarikan koin yang tidak ada harus gagal");
    }

    @Test
    public void testCalculateMoney() {
        wallet.addMoney(10000);
        wallet.addMoney(5000);
        wallet.addCoin(1000); // Koin tidak boleh memengaruhi kalkulasi uang kertas
        assertEquals(15000, wallet.calculateMoney(), "Total uang kertas harus sesuai");
    }

    @Test
    public void testCalculateCoin() {
        wallet.addCoin(500);
        wallet.addCoin(1000);
        wallet.addMoney(10000); // Uang kertas tidak boleh memengaruhi kalkulasi koin
        assertEquals(1500, wallet.calculateCoin(), "Total koin harus sesuai");
    }

    @Test
    public void testGetTotalMoney() {
        wallet.addMoney(10000);
        wallet.addMoney(5000);
        wallet.addCoin(1000);
        assertEquals(16000, wallet.getTotalMoney(), "Total uang harus sesuai");
    }

    @Test
    public void testInitialStateAfterSetup() {
        assertEquals("Admin", wallet.getOwner(), "Owner harus sesuai dengan setup");
        assertEquals(0, wallet.getCards().size(), "Daftar kartu awal harus kosong");
        assertEquals(0, wallet.getMoney().size(), "Daftar uang kertas awal harus kosong");
        assertEquals(0, wallet.getCoins().size(), "Daftar koin awal harus kosong");
    }

    @AfterEach
    public void tearDown() {
        wallet.getCards().clear();
        wallet.getMoney().clear();
        wallet.getCoins().clear();
    }

    @AfterAll
    public static void tearDownAll() {
        wallet = null;
        System.out.println("Pengujian selesai");
    }
}