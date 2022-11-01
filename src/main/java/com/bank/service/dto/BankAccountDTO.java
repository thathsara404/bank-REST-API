package com.bank.service.dto;

import com.bank.service.entity.schema.Account;
import com.bank.service.entity.schema.Branch;
import com.bank.service.entity.schema.User;
import com.bank.service.enums.AccountType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankAccountDTO {

    private Long id;

    private UUID accountNumber;

    @NotNull
    private AccountType accountType;

    @NotNull
    private Branch branch;

    @NotNull
    private User user;

    @NotNull
    private BigDecimal accountBalance;

    public BankAccountDTO(Account account) {
        this.setId(account.getId());
        this.setAccountType(account.getAccountType());
        this.setAccountNumber(account.getAccountNumber());
        this.setAccountBalance(account.getAccountBalance());
        this.setBranch(account.getBranch());
        this.setUser(account.getUser());
    }

}
