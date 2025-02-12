package com.yaba.springkeycloak.exchange.response;

import com.yaba.springkeycloak.entities.BookLoan;
import com.yaba.springkeycloak.enums.ReaderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReaderResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private ReaderStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private List<BookLoanResponse> borrowedBooks;

}
