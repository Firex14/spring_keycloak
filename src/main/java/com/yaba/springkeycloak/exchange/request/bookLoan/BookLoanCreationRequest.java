package com.yaba.springkeycloak.exchange.request.bookLoan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookLoanCreationRequest {
    private UUID readerId;
    private UUID bookId;

}
