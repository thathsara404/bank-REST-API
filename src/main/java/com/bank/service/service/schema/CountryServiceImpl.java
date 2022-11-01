package com.bank.service.service.schema;

import com.bank.service.consts.ErrorConst;
import com.bank.service.dto.CountryDTO;
import com.bank.service.entity.schema.Country;
import com.bank.service.repository.schema.specification.ICountryRepository;
import com.bank.service.exception.UnExpectedErrorOccurredException;
import com.bank.service.service.schema.specification.ICountryService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Implementation of CountryService
 * */
@Service
@AllArgsConstructor
public class CountryServiceImpl implements ICountryService {

    private ICountryRepository repository;

    /**
     * Save new country and return safely copied CountryDTO
     * @param country CountryDTO
     * @return CountryDTO
     * */
    @Override
    public CountryDTO addNewCountry(final CountryDTO country) {
        CountryDTO countrySavedDTO = null;
        try {
            Country countryInsert = new Country();
            countryInsert.setCountryName(country.getCountryName());
            countryInsert.setCurrency(country.getCurrency());
            Country countrySaved = repository.save(countryInsert);

            countrySavedDTO = new CountryDTO(countrySaved);
        } catch (DataIntegrityViolationException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new UnExpectedErrorOccurredException(ErrorConst.UNEXPECTED_ERROR_OCCURRED_MESSAGE,
                    exception.getCause());
        }
        return countrySavedDTO;
    }


}
