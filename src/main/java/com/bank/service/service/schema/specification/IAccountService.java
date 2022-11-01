package com.bank.service.service.schema.specification;

import com.bank.service.dto.BankAccountDTO;

import java.util.UUID;

/**
 * Represent the specification which includes bank account related business logic operations.
 * */
public interface IAccountService {

    /**
     * Save new bank account
     * @param accountDTO BankAccountDTO
     * @param branchNumber UUID
     * @param userId UUID
     * @return BankAccountDTO
     * */
    public abstract BankAccountDTO addNewAccount(BankAccountDTO accountDTO,
                                                 UUID branchNumber,
                                                 UUID userId);


}
