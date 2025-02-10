package com.yaba.springkeycloak.exchange.response;

import com.yaba.springkeycloak.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookLoanResponse {
    private UUID id;
    private UUID readerId;
    private UUID bookId;
    private LoanStatus status;
    private ZonedDateTime borrowedDate;
    private ZonedDateTime returnedDate;
}
