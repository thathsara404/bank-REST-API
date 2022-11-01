package com.bank.service.service.schema;

import com.bank.service.consts.ErrorConst;
import com.bank.service.dto.BranchDTO;
import com.bank.service.entity.schema.Branch;
import com.bank.service.entity.schema.Country;
import com.bank.service.enums.CountryName;
import com.bank.service.exception.UnExpectedErrorOccurredException;
import com.bank.service.repository.schema.specification.IBranchRepository;
import com.bank.service.repository.schema.specification.ICountryRepository;
import com.bank.service.service.schema.specification.IBranchService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Implementation of CountryService
 * */
@Service
@AllArgsConstructor
public class BranchServiceImpl implements IBranchService {

    private IBranchRepository branchRepository;

    private ICountryRepository countryRepository;

    /**
     * Save new branch and return safely copied BranchDTO
     * @param branch BranchDTO
     * @return BranchDTO
     * */
    @Override
    public BranchDTO addNewBranch(final BranchDTO branch, final CountryName countryName) {
        BranchDTO branchSavedDTO = null;
        try {
            Branch branchInsert = new Branch();
            branchInsert.setBranchName(branch.getBranchName());
            Country country = countryRepository.getByCountryName(countryName);
            branchInsert.setCountry(country);
            Branch branchSaved = branchRepository.save(branchInsert);
            branchSavedDTO = new BranchDTO(branchSaved);
        } catch (DataIntegrityViolationException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new UnExpectedErrorOccurredException(ErrorConst.UNEXPECTED_ERROR_OCCURRED_MESSAGE,
                    exception.getCause());
        }
        return branchSavedDTO;
    }


}
