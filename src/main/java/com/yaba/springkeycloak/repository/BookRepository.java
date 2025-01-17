package com.yaba.springkeycloak.repository;

import com.yaba.springkeycloak.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    boolean existsByAuthorIgnoreCaseAndTitleIgnoreCase(String author, String title);
    boolean existsByAuthorIgnoreCaseAndTitleIgnoreCaseAndIdNot(String author, String title, UUID id);
}
