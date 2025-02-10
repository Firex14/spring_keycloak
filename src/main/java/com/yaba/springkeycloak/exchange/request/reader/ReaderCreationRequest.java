package com.yaba.springkeycloak.exchange.request.reader;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReaderCreationRequest {
    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;
}
