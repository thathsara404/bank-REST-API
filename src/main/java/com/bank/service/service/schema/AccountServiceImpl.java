package com.bank.service.service.schema;

import com.bank.service.consts.ErrorConst;
import com.bank.service.dto.BankAccountDTO;
import com.bank.service.entity.schema.Account;
import com.bank.service.entity.schema.Branch;
import com.bank.service.entity.schema.User;
import com.bank.service.exception.UnExpectedErrorOccurredException;
import com.bank.service.repository.schema.specification.IAccountRepository;
import com.bank.service.repository.schema.specification.IBranchRepository;
import com.bank.service.repository.schema.specification.IUserRepository;
import com.bank.service.service.schema.specification.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of IAccountService
 * */
@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private IAccountRepository accountRepository;

    private IBranchRepository branchRepository;

    private IUserRepository userRepository;

    /**
     * Save new account and return safely copied AccountDTO
     * @param account BankAccountDTO
     * @param branchNumber UUID
     * @param userId UUID
     * @return BankAccountDTO
     * */
    @Override
    public BankAccountDTO addNewAccount(final BankAccountDTO account,
                                        final UUID branchNumber, final UUID userId) {
        BankAccountDTO accountDTO = null;
        try {
            Account accountInsert = new Account();
            accountInsert.setAccountType(account.getAccountType());
            accountInsert.setAccountBalance(account.getAccountBalance());

            Branch branch = branchRepository.getByBranchNumber(branchNumber);
            accountInsert.setBranch(branch);

            User user = userRepository.getByUserId(userId);
            accountInsert.setUser(user);

            Account accountInserted = accountRepository.save(accountInsert);
            accountDTO = new BankAccountDTO(accountInserted);

        } catch (DataIntegrityViolationException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new UnExpectedErrorOccurredException(ErrorConst.UNEXPECTED_ERROR_OCCURRED_MESSAGE,
                    exception.getCause());
        }
        return accountDTO;
    }


}
