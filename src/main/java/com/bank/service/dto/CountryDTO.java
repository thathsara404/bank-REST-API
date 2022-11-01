package com.bank.service.dto;

import com.bank.service.entity.schema.Country;
import com.bank.service.enums.CountryName;
import com.bank.service.enums.Currency;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryDTO implements ISuccessResponse{

    private Long id;

    @NotNull
    private CountryName countryName;

    @NotNull
    private Currency currency;

    private Set<BranchDTO> branches;

    public CountryDTO(Country country) {
        this.setId(country.getId());
        this.setCountryName(country.getCountryName());
        this.setCurrency(country.getCurrency());
    }

}
