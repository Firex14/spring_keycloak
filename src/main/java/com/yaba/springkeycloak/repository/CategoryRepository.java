package com.yaba.springkeycloak.repository;

import com.yaba.springkeycloak.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Book, UUID> {
}
