package com.bank.service.dto;

import com.bank.service.entity.schema.Account;
import com.bank.service.entity.schema.Transaction;
import com.sun.istack.NotNull;
import com.bank.service.enums.TransactionDirection;
import com.bank.service.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDTO {

    private Long id;

    private UUID transactionNumber;

    private Account account;

    @NotNull
    private UUID beneficiaryAccountNumber;

    private LocalDateTime transactionDateTime;

    @NotNull
    private BigDecimal transactionAmount;

    @NotNull
    private String transactionDescription;

    private TransactionDirection transactionDirection;

    private TransactionStatus transactionStatus;

    public TransactionDTO(Transaction transaction) {
        this.setId(transaction.getId());
        this.setAccount(transaction.getAccount());
        this.setTransactionDescription(transaction.getTransactionDescription());
        this.setBeneficiaryAccountNumber(transaction.getBeneficiaryAccountNumber());
        this.setTransactionAmount(transaction.getTransactionAmount());
        this.setTransactionNumber(transaction.getTransactionNumber());
        this.setTransactionDateTime(transaction.getTransactionDateTime());
        this.setTransactionDirection(transaction.getDirection());
        this.setTransactionStatus(transaction.getTransactionStatus());
    }

}
