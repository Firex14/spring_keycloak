package com.yaba.springkeycloak.entities;

import com.yaba.springkeycloak.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "book_loans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookLoan{

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @CreationTimestamp
    private ZonedDateTime borrowedDate;

    private ZonedDateTime returnedDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;


}
