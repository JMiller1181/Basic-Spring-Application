package com.fs104.basicspringapplication.repository;

import com.fs104.basicspringapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
