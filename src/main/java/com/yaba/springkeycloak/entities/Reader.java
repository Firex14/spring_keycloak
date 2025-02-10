package com.yaba.springkeycloak.entities;

import com.yaba.springkeycloak.enums.LoanStatus;
import com.yaba.springkeycloak.enums.ReaderStatus;
import com.yaba.springkeycloak.validation.groups.Create;
import com.yaba.springkeycloak.validation.groups.Update;
import com.yaba.springkeycloak.validation.messages.ExceptionMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "readers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reader extends BaseModel{

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.REQUIRED_FIELD, groups = { Create.class, Update.class })
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.REQUIRED_FIELD, groups = { Create.class, Update.class })
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.REQUIRED_FIELD, groups = { Create.class, Update.class })
    private String email;

    @Enumerated(EnumType.STRING)
    private ReaderStatus status;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookLoan> borrowedBooks;

    public boolean canBorrowMoreBooks(int maxBorrowLimit) {
        long activeLoans = borrowedBooks.stream()
                .filter(loan -> loan.getStatus() == LoanStatus.BORROWED)
                .count();
        return activeLoans < maxBorrowLimit;
    }
}
