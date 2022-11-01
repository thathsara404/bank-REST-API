package com.bank.service.service.schema;

import com.bank.service.consts.ErrorConst;
import com.bank.service.entity.schema.Account;
import com.bank.service.entity.schema.Transaction;
import com.bank.service.exception.UnExpectedErrorOccurredException;
import com.bank.service.repository.schema.specification.IAccountRepository;
import com.bank.service.service.message.specification.ITransactionMessageService;
import com.bank.service.service.schema.specification.ITransactionService;
import com.bank.service.dto.TransactionDTO;
import com.bank.service.exception.CanNotProceedTransactionException;
import com.bank.service.repository.schema.specification.ITransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * Implementation of IUserService
 * */
@Service
@AllArgsConstructor
public class TransactionDataServiceImpl implements ITransactionService {

    private IAccountRepository accountRepository;

    private ITransactionRepository transactionRepository;

    private ITransactionMessageService transactionMessageService;

    /**
     * Save new user and return safely copied TransactionDTO
     * @param accountNumber UUID
     * @return TransactionDTO
     * */
    @Override
    public TransactionDTO addNewTransaction(final TransactionDTO transaction, UUID accountNumber) {
        TransactionDTO transactionDTO = null;
        try {

            Transaction transactionInsert = new Transaction();
            transactionInsert.setTransactionAmount(transaction.getTransactionAmount());
            transactionInsert.setTransactionDescription(transaction.getTransactionDescription());
            transactionInsert.setBeneficiaryAccountNumber(transaction.getBeneficiaryAccountNumber());
            Account account = accountRepository.getByAccountNumber(accountNumber);
            transactionInsert.setAccount(account); // this will set accountId

            if (account.getAccountBalance().compareTo(transaction.getTransactionAmount()) > 0) {
                Transaction transactionInserted = transactionRepository.save(transactionInsert);
                transactionDTO = new TransactionDTO(transactionInserted);
                transactionMessageService.buildAndPushTransactionPlacementMessage(
                        transactionInserted.getTransactionNumber().toString(),
                        transactionInserted.getTransactionDateTime());
            } else {
                throw new CanNotProceedTransactionException(ErrorConst.TRANSACTION_ERROR_LOW_BALANCE_MESSAGE, null);
            }

        } catch (DataIntegrityViolationException exception) {
            throw exception;
        } catch (CanNotProceedTransactionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new UnExpectedErrorOccurredException(ErrorConst.UNEXPECTED_ERROR_OCCURRED_MESSAGE,
                    exception.getCause());
        }
        return transactionDTO;
    }


}
