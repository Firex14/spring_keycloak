package com.yaba.springkeycloak.repository;

import com.yaba.springkeycloak.entities.Book;
import com.yaba.springkeycloak.entities.BookLoan;
import com.yaba.springkeycloak.entities.Reader;
import com.yaba.springkeycloak.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookLoanRepository extends JpaRepository<BookLoan, UUID> {

    boolean existsByBookAndReaderAndStatus(Book book, Reader reader, LoanStatus loanStatus);
}
