package com.example.bookreviewsystem.service;
import com.example.bookreviewsystem.entity.Book;

import com.example.bookreviewsystem.exception.ResourceNotFoundException;
import com.example.bookreviewsystem.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    //Create or update book
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    //Get list of all books
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    //Get book by ID
    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book Not found with Id: " + id));
    }

    //Delete Book
    public void deleteBook(Long id){
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    //Search Books
    public List<Book> searchBooks(String title, String author, String genre){
        if(title != null) {return bookRepository.findByTitleContainingIgnoreCase(title);}
        if(author != null) {return bookRepository.findByAuthorContainingIgnoreCase(author);}
        if(genre != null) {return bookRepository.findByGenreContainingIgnoreCase(genre);}
        return getAllBooks();
    }






}
