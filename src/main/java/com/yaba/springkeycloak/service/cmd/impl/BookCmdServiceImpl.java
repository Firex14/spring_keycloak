package com.yaba.springkeycloak.service.cmd.impl;

import com.yaba.springkeycloak.dto.BookDto;
import com.yaba.springkeycloak.entities.Book;
import com.yaba.springkeycloak.entities.Category;
import com.yaba.springkeycloak.exceptions.ApiRequestException;
import com.yaba.springkeycloak.exceptions.ExceptionCode;
import com.yaba.springkeycloak.exceptions.ExceptionLevel;
import com.yaba.springkeycloak.mapper.BookMapper;
import com.yaba.springkeycloak.repository.BookRepository;
import com.yaba.springkeycloak.repository.CategoryRepository;
import com.yaba.springkeycloak.service.cmd.BookCmdService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookCmdServiceImpl implements BookCmdService {

    private final BookRepository repository;
    private final CategoryRepository categoryRepository;
    private final BookMapper mapper;

    public BookCmdServiceImpl(BookRepository repository, CategoryRepository categoryRepository, BookMapper mapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Category category = categoryRepository.findById(bookDto.getCategoryId())
                .orElseThrow(() ->  new ApiRequestException(
                ExceptionCode.CATEGORY_NOT_FOUND.getMessage(),
                ExceptionCode.CATEGORY_NOT_FOUND.getValue(),
                ExceptionLevel.ERROR,
                HttpStatus.NOT_FOUND

        ));
        if (isExist(bookDto.getAuthor(), bookDto.getTitle())){
            throw new ApiRequestException(
                    ExceptionCode.BOOK_ALREADY_EXISTS.getMessage(),
                    ExceptionCode.BOOK_ALREADY_EXISTS.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.CONFLICT
            );
        }

        Book newBook = mapper.toEntity(bookDto);
        newBook.setCategory(category);

        return mapper.toDto(repository.save(newBook));
    }

    @Override
    public BookDto update(BookDto bookDto) {
        if (bookDto.getId() == null) {
            throw new ApiRequestException(
                    ExceptionCode.NULL_VALUE_OF_ID.getMessage(),
                    ExceptionCode.NULL_VALUE_OF_ID.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.BAD_REQUEST
            );
        }

        Category category;
        if (bookDto.getCategoryId() != null) {
             category = categoryRepository.findById(bookDto.getCategoryId())
                    .orElseThrow(() -> new ApiRequestException(
                            ExceptionCode.CATEGORY_NOT_FOUND.getMessage(),
                            ExceptionCode.CATEGORY_NOT_FOUND.getValue(),
                            ExceptionLevel.ERROR,
                            HttpStatus.NOT_FOUND
                    ));
        } else {
            category = null;
        }

        return repository.findById(bookDto.getId()).map(
                existingBook -> {
                    if (isExistExceptCurrent(bookDto.getAuthor(), bookDto.getTitle(), bookDto.getId())) {
                        throw new ApiRequestException(
                                ExceptionCode.BOOK_ALREADY_EXISTS.getMessage(),
                                ExceptionCode.BOOK_ALREADY_EXISTS.getValue(),
                                ExceptionLevel.ERROR,
                                HttpStatus.CONFLICT
                        );
                    }
                    mapper.partialUpdate(existingBook, bookDto);
                    existingBook.setCategory(category);
                    return mapper.toDto(repository.save(existingBook));
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
