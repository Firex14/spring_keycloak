package com.yaba.springkeycloak.entities;

import com.yaba.springkeycloak.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
@Entity
@Table(name = "book_loans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookLoan extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private ZonedDateTime borrowedDate;

    private ZonedDateTime returnedDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status = LoanStatus.BORROWED;
}
