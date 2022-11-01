package com.bank.service.controller;

import com.bank.service.enums.CountryName;
import com.sun.istack.NotNull;
import com.bank.service.dto.BranchDTO;
import com.bank.service.dto.ResponseDTO;
import com.bank.service.service.schema.specification.IBranchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Branch specific Endpoints
 * */
@RestController
@CrossOrigin
@RequestMapping("/branches")
@AllArgsConstructor
public class BranchController {

    private IBranchService branchService;

    @PostMapping(path = "/countries/{countryName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> addBranch (final @RequestBody @NotNull BranchDTO branchDTO,
                                                         final @PathVariable @NotNull String countryName) {

        BranchDTO branch = branchService.addNewBranch(branchDTO, CountryName.valueOf(countryName));
        ResponseDTO responseDTO = new ResponseDTO(Arrays.asList(branch), null);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

}
