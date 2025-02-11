package com.yaba.springkeycloak.service.cmd.impl;

import com.yaba.springkeycloak.entities.Book;
import com.yaba.springkeycloak.entities.BookLoan;
import com.yaba.springkeycloak.entities.Reader;
import com.yaba.springkeycloak.enums.LoanStatus;
import com.yaba.springkeycloak.exceptions.ApiRequestException;
import com.yaba.springkeycloak.exceptions.ExceptionCode;
import com.yaba.springkeycloak.exceptions.ExceptionLevel;
import com.yaba.springkeycloak.exchange.request.bookLoan.BookLoanCreationRequest;
import com.yaba.springkeycloak.repository.BookLoanRepository;
import com.yaba.springkeycloak.repository.BookRepository;
import com.yaba.springkeycloak.repository.ReaderRepository;
import com.yaba.springkeycloak.service.cmd.BookLoanCmdService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class BookLoanCmdServiceImpl implements BookLoanCmdService {
    private static final int MAX_BORROW_LIMIT = 3;

    private final BookLoanRepository repository;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    public BookLoanCmdServiceImpl(BookLoanRepository repository, ReaderRepository readerRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void loanBook(BookLoanCreationRequest creationRequest) {
        Reader reader = getReaderIfExists(creationRequest.getReaderId());
        Book book = getBookIfExists(creationRequest.getBookId());

        if (!reader.canBorrowMoreBooks(MAX_BORROW_LIMIT)) {
            throw new ApiRequestException(
                    ExceptionCode.TOO_MANY_BORROWED_BOOKS.getMessage(),
                    ExceptionCode.TOO_MANY_BORROWED_BOOKS.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.FORBIDDEN
            );
        }
        BookLoan loan = new BookLoan();

        loan.setBook(book);
        loan.setReader(reader);
        loan.setStatus(LoanStatus.BORROWED);

        BookLoan savedLoan = repository.save(loan);
        reader.addBookLoan(savedLoan);

    }

    @Override
    public void returnBook(UUID bookLoanId) {
        repository.findById(bookLoanId).map(
                bookLoan -> {
                    bookLoan.setStatus(LoanStatus.RETURNED);

                    return repository.save(bookLoan);
                }
        ).orElseThrow(
                () -> new ApiRequestException(
                        ExceptionCode.LOAN_NOT_FOUND.getMessage(),
                        ExceptionCode.LOAN_NOT_FOUND.getValue(),
                        ExceptionLevel.ERROR,
                        HttpStatus.NOT_FOUND
                )
        );
    }

    private Book getBookIfExists(UUID bookId){
       return bookRepository.findById(bookId).orElseThrow(
                () -> new ApiRequestException(
                        ExceptionCode.BOOK_NOT_FOUND.getMessage(),
                        ExceptionCode.BOOK_NOT_FOUND.getValue(),
                        ExceptionLevel.ERROR,
                        HttpStatus.NOT_FOUND
                )
        );

    }

    private Reader getReaderIfExists(UUID readerId){
       return readerRepository.findById(readerId).orElseThrow(
                () -> new ApiRequestException(
                        ExceptionCode.READER_NOT_FOUND.getMessage(),
                        ExceptionCode.READER_NOT_FOUND.getValue(),
                        ExceptionLevel.ERROR,
                        HttpStatus.NOT_FOUND
                )
        );
    }

}
