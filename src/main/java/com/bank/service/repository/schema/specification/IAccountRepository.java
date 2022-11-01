package com.bank.service.repository.schema.specification;

import com.bank.service.entity.schema.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Represent the specification which includes account related operations.
 * */
@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    public abstract Account getByAccountNumber(final UUID accountNumber);

}
