package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.exchange.request.bookLoan.BookLoanCreationRequest;

import java.util.UUID;

public interface BookLoanCmdService {
    void loanBook(BookLoanCreationRequest creationRequest);
    void returnBook(UUID bookLoanId);
}
