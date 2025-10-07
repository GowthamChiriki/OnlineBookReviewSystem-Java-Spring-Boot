package com.example.bookreviewsystem.service;

import com.example.bookreviewsystem.entity.Book;
import com.example.bookreviewsystem.exception.ResourceNotFoundException;
import com.example.bookreviewsystem.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // Create a new book (ID should NOT be provided)
    public Book createBook(Book book) {
        book.setId(null); // Ensure ID is null for auto-generation
        return bookRepository.save(book);
    }

    // Get all books or search by title/author/genre
    public List<Book> getAllBooks(String title, String author, String genre) {
        if (title != null) return bookRepository.findByTitleContainingIgnoreCase(title);
        if (author != null) return bookRepository.findByAuthorContainingIgnoreCase(author);
        if (genre != null) return bookRepository.findByGenreContainingIgnoreCase(genre);
        return bookRepository.findAll();
    }

    // Get book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    // Update book safely
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book existingBook = getBookById(id);

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setGenre(bookDetails.getGenre());
        existingBook.setPublicationYear(bookDetails.getPublicationYear());

        return bookRepository.save(existingBook);
    }

    // Delete book safely
    @Transactional
    public void deleteBook(Long id) {
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
    }
}
