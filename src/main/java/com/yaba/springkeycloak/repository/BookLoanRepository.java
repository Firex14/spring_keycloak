package com.yaba.springkeycloak.repository;

import com.yaba.springkeycloak.entities.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookLoanRepository extends JpaRepository<BookLoan, UUID> {
}
