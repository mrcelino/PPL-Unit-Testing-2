import org.example.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class WalletTest {
    private Wallet wallet;

    @BeforeEach
    public void setUp() {
        wallet = new Wallet();
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
        assertEquals(3, cards.size(), "Jumlah kartu harusnya 3");
    }


    @Test
    public void testRemoveCard() {
        wallet.addCard("KTM");
        boolean result = wallet.removeCard("KTM");
        assertTrue(result, "Pengambilan kartu yang ada harus berhasil");
        assertFalse(wallet.getCards().contains("KTM"), "Kartu harusnya tidak ada setelah dihapus");
        assertEquals(0, wallet.getCards().size(), "Jumlah kartu harusnya 0 setelah dihapus");

        // Test menghapus kartu yang tidak ada
        result = wallet.removeCard("Kartu Debit");
        assertFalse(result, "Pengambilan kartu yang tidak ada harus gagal");
    }

    @Test
    public void testAddMoneyValid() {
        boolean resultMoney = wallet.addMoney(10000, true); // Uang lembaran
        boolean resultCoin = wallet.addMoney(500, false);  // Koin

        assertTrue(resultMoney, "Uang lembaran harus valid");
        assertTrue(resultCoin, "Koin harus valid");
        assertTrue(wallet.getMoney().contains(10000), "Uang lembaran harus ada");
        assertTrue(wallet.getCoins().contains(500), "Koin harus ada");
        assertEquals(1, wallet.getMoney().size(), "Jumlah uang harusnya 1");
        assertEquals(1, wallet.getCoins().size(), "Jumlah koin harusnya 1");
    }

    @Test
    public void testAddMoneyInvalid() {
        boolean resultMoney = wallet.addMoney(3000, true); // Uang lembaran tidak valid
        boolean resultCoin = wallet.addMoney(200, false);  // Koin tidak valid

        assertFalse(resultMoney, "Penambahan uang lembaran yang tidak valid harus gagal");
        assertFalse(resultCoin, "Penambahan koin yang tidak valid harus gagal");
        assertEquals(0, wallet.getMoney().size(), "Jumlah uang harus tetap kosong");
        assertEquals(0, wallet.getCoins().size(), "Jumlah koin harus tetap kosong");
    }

    @Test
    public void testWithdrawMoney() {
        wallet.addMoney(20000, true); // Uang lembaran
        wallet.addMoney(1000, false); // Koin

        boolean resultMoney = wallet.withdrawMoney(20000);
        boolean resultCoin = wallet.withdrawMoney(1000);
        boolean resultInvalid = wallet.withdrawMoney(5000);

        assertTrue(resultMoney, "Penarikan uang lembaran harus berhasil");
        assertTrue(resultCoin, "Penarikan koin harus berhasil");
        assertFalse(resultInvalid, "Penarikan jumlah yang tidak ada harus gagal");
        assertFalse(wallet.getMoney().contains(20000), "Uang lembaran harus hilang setelah ditarik");
        assertFalse(wallet.getCoins().contains(1000), "Koin harus hilang setelah ditarik");
    }

    @Test
    public void testGetTotalMoney() {
        wallet.addMoney(10000, true); // Uang lembaran
        wallet.addMoney(5000, true);  // Uang lembaran
        wallet.addMoney(1000, false); // Koin

        int total = wallet.getTotalMoney();
        assertEquals(16000, total, "Total uang harus sesuai dengan jumlah uang lembar dan koin");

        // Test dompet kosong
        Wallet emptyWallet = new Wallet();
        assertEquals(0, emptyWallet.getTotalMoney(), "Total uang dompet kosong harus 0");
    }

    @Test
    public void testInitialState() {
        assertNull(wallet.getOwner(), "Owner awal harus null");
        assertEquals(0, wallet.getCards().size(), "Daftar kartu awal harus kosong");
        assertEquals(0, wallet.getMoney().size(), "Daftar uang awal harus kosong");
        assertEquals(0, wallet.getCoins().size(), "Daftar koin awal harus kosong");
        assertTrue(wallet.getCards().isEmpty(), "Daftar kartu harus kosong");
        assertTrue(wallet.getMoney().isEmpty(), "Daftar uang harus kosong");
        assertTrue(wallet.getCoins().isEmpty(), "Daftar koin harus kosong");
    }
}