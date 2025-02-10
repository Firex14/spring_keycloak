package com.yaba.springkeycloak.service.cmd.impl;

import com.yaba.springkeycloak.exchange.request.book.BookCreationRequest;
import com.yaba.springkeycloak.exchange.request.book.BookUpdateRequest;
import com.yaba.springkeycloak.exchange.response.BookResponse;
import com.yaba.springkeycloak.entities.Book;
import com.yaba.springkeycloak.entities.Category;
import com.yaba.springkeycloak.exceptions.ApiRequestException;
import com.yaba.springkeycloak.exceptions.ExceptionCode;
import com.yaba.springkeycloak.exceptions.ExceptionLevel;
import com.yaba.springkeycloak.mapper.book.BookCreateMapper;
import com.yaba.springkeycloak.mapper.book.BookMapper;
import com.yaba.springkeycloak.mapper.book.BookUpdateMapper;
import com.yaba.springkeycloak.repository.BookRepository;
import com.yaba.springkeycloak.repository.CategoryRepository;
import com.yaba.springkeycloak.service.cmd.BookCmdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookCmdServiceImpl implements BookCmdService {
    private final Logger log = LoggerFactory.getLogger(BookCmdServiceImpl.class);


    private final BookRepository repository;
    private final CategoryRepository categoryRepository;
    private final BookMapper responseMapper;
    private final BookCreateMapper createMapper;
    private final BookUpdateMapper bookUpdateMapper;

    public BookCmdServiceImpl(BookRepository repository, CategoryRepository categoryRepository, BookMapper mapper, BookCreateMapper createMapper, BookUpdateMapper bookUpdateMapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.responseMapper = mapper;
        this.createMapper = createMapper;
        this.bookUpdateMapper = bookUpdateMapper;
    }

    @Override
    public BookResponse save(BookCreationRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->  new ApiRequestException(
                ExceptionCode.CATEGORY_NOT_FOUND.getMessage(),
                ExceptionCode.CATEGORY_NOT_FOUND.getValue(),
                ExceptionLevel.ERROR,
                HttpStatus.NOT_FOUND

        ));
        if (isExist(request.getAuthor(), request.getTitle())){
            throw new ApiRequestException(
                    ExceptionCode.BOOK_ALREADY_EXISTS.getMessage(),
                    ExceptionCode.BOOK_ALREADY_EXISTS.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.CONFLICT
            );
        }

        Book newBook = createMapper.toEntity(request);
        newBook.setCategory(category);

        return responseMapper.toDto(repository.save(newBook));
    }

    @Override
    public BookResponse update(BookUpdateRequest updateRequest) {
        if (updateRequest.getId() == null) {
            throw new ApiRequestException(
                    ExceptionCode.NULL_VALUE_OF_ID.getMessage(),
                    ExceptionCode.NULL_VALUE_OF_ID.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.BAD_REQUEST
            );
        }

        Category category;
        if (updateRequest.getCategoryId() != null) {
             category = categoryRepository.findById(updateRequest.getCategoryId())
                    .orElseThrow(() -> new ApiRequestException(
                            ExceptionCode.CATEGORY_NOT_FOUND.getMessage(),
                            ExceptionCode.CATEGORY_NOT_FOUND.getValue(),
                            ExceptionLevel.ERROR,
                            HttpStatus.NOT_FOUND
                    ));
        } else {
            category = null;
        }

        return repository.findById(updateRequest.getId()).map(
                existingBook -> {
                    if (isExistExceptCurrent(updateRequest.getAuthor(), updateRequest.getTitle(), updateRequest.getId())) {
                        throw new ApiRequestException(
                                ExceptionCode.BOOK_ALREADY_EXISTS.getMessage(),
                                ExceptionCode.BOOK_ALREADY_EXISTS.getValue(),
                                ExceptionLevel.ERROR,
                                HttpStatus.CONFLICT
                        );
                    }
                    bookUpdateMapper.partialUpdate(existingBook, updateRequest);
                    existingBook.setCategory(category);
                    return responseMapper.toDto(repository.save(existingBook));
                }
                )
                .orElseThrow(() -> new ApiRequestException(
                        ExceptionCode.BOOK_NOT_FOUND.getMessage(),
                        ExceptionCode.BOOK_NOT_FOUND.getValue(),
                        ExceptionLevel.ERROR,
                        HttpStatus.NOT_FOUND
                ));

    }

    private boolean isExist(String author, String title) {
        return repository.existsByAuthorIgnoreCaseAndTitleIgnoreCase(author, title);
    }

    private boolean isExistExceptCurrent(String author, String title, UUID bookId) {
        return repository.existsByAuthorIgnoreCaseAndTitleIgnoreCaseAndIdNot(author, title, bookId);
    }

}
