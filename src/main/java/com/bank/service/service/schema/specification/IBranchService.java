package com.bank.service.service.schema.specification;

import com.bank.service.dto.BranchDTO;
import com.bank.service.enums.CountryName;

/**
 * Represent the specification which includes branch related business logic operations.
 * */
public interface IBranchService {

    /**
     * Save new branch
     * @param branch Branch
     * @return BranchDTO
     * */
    public abstract BranchDTO addNewBranch(BranchDTO branch, CountryName countryName);

}
