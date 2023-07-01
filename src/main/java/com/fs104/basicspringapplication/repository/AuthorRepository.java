package com.fs104.basicspringapplication.repository;

import com.fs104.basicspringapplication.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
