package com.bank.service.controller;

import com.bank.service.dto.ResponseDTO;
import com.bank.service.dto.TransactionDTO;
import com.bank.service.service.schema.specification.ITransactionService;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

/**
 * Transaction specific Endpoints
 * */
@CrossOrigin
@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private ITransactionService transactionService;

    @PostMapping(path = "/accounts/{senderAccountNumber}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> addBranch (final @RequestBody @NotNull TransactionDTO transactionDTO,
                                                  final @PathVariable @NotNull String senderAccountNumber) {


        TransactionDTO transaction = transactionService.addNewTransaction(transactionDTO, UUID.fromString(senderAccountNumber));
        ResponseDTO responseDTO = new ResponseDTO(Arrays.asList(transaction), null);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

}
