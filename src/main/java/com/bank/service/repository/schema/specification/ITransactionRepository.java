package com.bank.service.repository.schema.specification;

import com.bank.service.entity.schema.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represent the specification which includes transaction related operations.
 * */
@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

}
