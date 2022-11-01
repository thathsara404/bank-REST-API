package com.bank.service.controller;

import com.bank.service.dto.CountryDTO;
import com.bank.service.service.schema.specification.ICountryService;
import com.sun.istack.NotNull;
import com.bank.service.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Country specific Endpoints
 * */
@RestController
@CrossOrigin
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {

    private ICountryService countryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> addCountry (@RequestBody @NotNull CountryDTO country) {

        CountryDTO savedCountryDTO = countryService.addNewCountry(country);
        ResponseDTO responseDTO = new ResponseDTO(Arrays.asList(savedCountryDTO), null);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

}
