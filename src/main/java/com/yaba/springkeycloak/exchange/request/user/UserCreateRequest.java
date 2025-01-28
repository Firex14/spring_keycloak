package com.yaba.springkeycloak.exchange.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserCreateRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String username;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    private String roles;
}
