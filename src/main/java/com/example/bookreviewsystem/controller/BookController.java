package com.example.bookreviewsystem.controller;

import com.example.bookreviewsystem.entity.Book;
import com.example.bookreviewsystem.repository.BookRepository;
import com.example.bookreviewsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    // Create a book
    @PostMapping("/book")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // Get all books or search
    @GetMapping
    public List<Book> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre
    ) {
        return bookService.getAllBooks(title, author, genre);
    }

    // Get book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // Update book safely
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookService.updateBook(id, bookDetails);
    }

    // Delete book safely
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted successfully";
    }

    @PostMapping("/bulk")
    public List<Book> createBooks(@RequestBody List<Book> books) {
        return bookRepository.saveAll(books);
    }

}
