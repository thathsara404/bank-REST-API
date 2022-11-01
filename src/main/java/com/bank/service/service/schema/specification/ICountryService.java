package com.bank.service.service.schema.specification;

import com.bank.service.dto.CountryDTO;

/**
 * Represent the specification which includes country related business logic operations.
 * */
public interface ICountryService {

    /**
     * Save new country
     * @param country Country
     * @return CountryDTO
     * */
    public abstract CountryDTO addNewCountry(CountryDTO country);

}
