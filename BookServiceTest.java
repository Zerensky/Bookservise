import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookById() {
        // Given
        Book book = new Book(1L, "Title1", "Author1");
        when(bookRepository.findById(1L)).thenReturn(book);

        // When
        Book foundBook = bookService.getBookById(1L);

        // Then
        assertNotNull(foundBook);
        assertEquals("Title1", foundBook.getTitle());
        assertEquals("Author1", foundBook.getAuthor());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllBooks() {
        // Given
        Book book1 = new Book(1L, "Title1", "Author1");
        Book book2 = new Book(2L, "Title2", "Author2");
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);

        // When
        List<Book> foundBooks = bookService.getAllBooks();

        // Then
        assertNotNull(foundBooks);
        assertEquals(2, foundBooks.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testAddBook() {
        // Given
        Book book = new Book(1L, "Title1", "Author1");

        // When
        bookService.addBook(book);

        // Then
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testRemoveBook() {
        // Given
        Long bookId = 1L;

        // When
        bookService.removeBook(bookId);

        // Then
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}

