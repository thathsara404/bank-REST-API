package com.bank.service.dto;

import com.bank.service.entity.schema.Account;
import com.bank.service.entity.schema.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;

    private UUID userId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String tele;

    private Set<Account> accounts;

    public UserDTO(User user) {
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setUserId(user.getUserId());
        this.setPassword(user.getPassword());
        this.setId(user.getId());
        this.setTele(user.getTele());
        this.setUsername(user.getUsername());
    }

}
