package com.fs104.basicspringapplication.controller;

import com.fs104.basicspringapplication.model.Author;
import com.fs104.basicspringapplication.model.Book;
import com.fs104.basicspringapplication.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/Author")
public class AuthorController {
    private final AuthorRepository repository;
    @Autowired
    public AuthorController(AuthorRepository repository){
        this.repository = repository;
    }
    @GetMapping("/list")
    public String listOfAuthors(Model model){
        model.addAttribute("authors", repository.findAll());
        return "Author/list";
    }

    @GetMapping("/create")
    public String createPage(Model model){
        model.addAttribute("author", new Author());
        return "Author/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("author") Author author){
        repository.save(author);
        return "redirect:/Author/list";
    }
    @GetMapping("/update/{id}")
    public String updatePage(@PathVariable("id") Long id, Model model){
        Optional<Author> foundAuthor = repository.findById(id);
        if (foundAuthor.isPresent()) {
            Author author = foundAuthor.get();
            model.addAttribute("author", author);
        }
        return "Author/update";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("author") Author newAuthor){
        Optional<Author> foundAuthor = repository.findById(id);
        if (foundAuthor.isPresent()) {
            Author existingAuthor = foundAuthor.get();
            existingAuthor.setName(newAuthor.getName());
            existingAuthor.setBookList(newAuthor.getBookList());
            repository.save(existingAuthor);
        }
        return "redirect:/Author/list";
    }
    @GetMapping("/delete")
    public String deletePage(Model model){
        model.addAttribute("author", new Author());
        return "Author/delete";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/Author/list";
    }}
