package com.bank.service.repository.schema.specification;

import com.bank.service.entity.schema.Country;
import com.bank.service.enums.CountryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represent the specification which includes country related operations.
 * */
@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {

    public abstract Country getByCountryName(final CountryName countryName);

}
