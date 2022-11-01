package com.bank.service.controller;

import com.bank.service.dto.BankAccountDTO;
import com.bank.service.dto.ResponseDTO;
import com.sun.istack.NotNull;
import com.bank.service.service.schema.specification.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

/**
 * Account specific Endpoints
 * */
@CrossOrigin
@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private IAccountService accountService;

    @PostMapping(path = "/users/{userId}/branches/{branchId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> addBranch (final @RequestBody @NotNull BankAccountDTO accountDTO,
                                                  final @PathVariable @NotNull UUID userId,
                                                  final @PathVariable @NotNull UUID branchId) {

        BankAccountDTO account = accountService.addNewAccount(accountDTO, branchId, userId);
        ResponseDTO responseDTO = new ResponseDTO(Arrays.asList(account), null);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

}
