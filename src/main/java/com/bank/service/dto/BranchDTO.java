package com.bank.service.dto;

import com.bank.service.entity.schema.Branch;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchDTO implements ISuccessResponse{

    private Long id;

    private UUID branchNumber;

    @NotNull
    private String branchName;

    @NotNull
    private CountryDTO country;

    private Set<BankAccountDTO> accounts;

    public BranchDTO(Branch branch) {
        this.setId(branch.getId());
        this.setBranchNumber(branch.getBranchNumber());
        this.setBranchName(branch.getBranchName());
        this.setCountry(new CountryDTO(branch.getCountry()));
    }

}
