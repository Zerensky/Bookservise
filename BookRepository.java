public interface BookRepository {
    Book findById(Long id);
    List<Book> findAll();
    void save(Book book);
    void deleteById(Long id);
}

