import org.example.Book;
import org.example.LibraryHelper;
import org.example.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(MockitoExtension.class)
public class LibraryHelperTest {

    @Mock
    public LibraryService service;
    @InjectMocks
    public LibraryHelper helper;

    @Test
    public void testCountBooks(){
        LibraryService service = Mockito.mock(LibraryService.class);
        LibraryHelper helper = new LibraryHelper(service);

        List<Book> books = new ArrayList<>();
        books.add(new Book("1","OOP", "Affriza"));
        books.add(new Book("2","OOP", "Affriza"));

        Mockito.when(service.getAllBooks()).thenReturn(books);
        Assertions.assertEquals(2,helper.countBooks());
    }

    @Test
    public void testSaveEmptyBooks(){
        List<Book> books = new ArrayList<>();

        LibraryService service = Mockito.mock(LibraryService.class);
        LibraryHelper helper = new LibraryHelper(service);

        helper.saveBook(books);
        Mockito.verify(service, Mockito.never()).storeData(any());
    }

    @Test
    public void testSaveNotEmptyBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book("1","OOP", "Affriza"));
        books.add(new Book("2","OOP", "Govan"));

        LibraryService service = Mockito.mock(LibraryService.class);
        LibraryHelper helper = new LibraryHelper(service);
        
        helper.saveBook(books);
        Mockito.verify(service).storeData(books);
    }


    @Test
    public void testSaveTwice() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("OOP", "1", "Marcel"));

        helper.saveBook(books);
        helper.saveBook(books);
        // Variasi: verify() dengan times(n)
        Mockito.verify(service, Mockito.times(2)).storeData(books);
    }

    @Test
    public void testNoExtraInteractions() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("OOP", "1", "Affriza"));

        helper.saveBook(books);

        Mockito.verify(service).storeData(books);
        // Variasi: verifyNoMoreInteractions()
        Mockito.verifyNoMoreInteractions(service);
    }

    @Test
    public void testSaveWithLimit() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("OOP", "1", "Affriza"));

        helper.saveBook(books);

        // Variasi : atMost(1)
        Mockito.verify(service, Mockito.atMost(1)).storeData(books);
    }


    @Test
    public void testSaveOnlyInteraction() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("OOP", "1", "Marcel"));

        helper.saveBook(books);

        // Variasi : only()
        Mockito.verify(service, Mockito.only()).storeData(books);
    }

    @Test
    public void testSaveWithDoAnswer() {
        List<Book> books = List.of(new Book("OOP", "1", "Marcel"));

        // Stubbing: doAnswer().when()
        Mockito.doAnswer(invocation -> {
            List<Book> savedBooks = invocation.getArgument(0);
            System.out.println("Saving book: " + savedBooks.get(0).getTitle() 
                                + " by " + savedBooks.get(0).getAuthor());
            return null;
        }).when(service).storeData(books);

        helper.saveBook(books);
    }
}
