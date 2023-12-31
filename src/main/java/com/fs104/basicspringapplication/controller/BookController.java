package com.fs104.basicspringapplication.controller;

import com.fs104.basicspringapplication.model.Author;
import com.fs104.basicspringapplication.model.Book;
import com.fs104.basicspringapplication.repository.AuthorRepository;
import com.fs104.basicspringapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequestMapping("/Book")
public class BookController {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    @Autowired
    public BookController(BookRepository repository, AuthorRepository authorRepository){
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/create")
    public String createPage(Model model){
        model.addAttribute("book", new Book());
        return "Book/create";
    }
    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") Book book) {
        Author author = book.getAuthor();
        if (author != null) {
            author = authorRepository.save(author); // Save the author if it is not yet persisted
            book.setAuthor(author);
        }
        repository.save(book);
        return "redirect:/Book/list";
    }

    @GetMapping("/list")
    public String listBooks(Model model){
        model.addAttribute("books", repository.findAll());
        return "Book/list";
    }
    @GetMapping("/update/{id}")
    public String updatePage(@PathVariable("id") Long id, Model model) {
        Optional<Book> foundBook = repository.findById(id);
        if (foundBook.isPresent()) {
            Book book = foundBook.get();
            model.addAttribute("book", book);
        }
        return "Book/update";
    }
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book) {
        Optional<Book> foundBook = repository.findById(id);
        if (foundBook.isPresent()) {
            Book existingBook = foundBook.get();
            existingBook.setTitle(book.getTitle());
            repository.save(existingBook);
        }
            return "redirect:/Book/list";
    }
    @GetMapping("/delete/{id}")
    public String deletePage(@PathVariable("id") Long id, Model model) {
        Optional<Book> foundBook = repository.findById(id);
        if (foundBook.isPresent()) {
            Book book = foundBook.get();
            model.addAttribute("book", book);
        }
        return "Book/delete";
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        repository.deleteById(id);
        return "redirect:/Book/list";
    }
}
