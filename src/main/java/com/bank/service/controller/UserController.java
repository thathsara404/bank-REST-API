package com.bank.service.controller;

import com.bank.service.dto.ResponseDTO;
import com.bank.service.service.schema.specification.IUserService;
import com.sun.istack.NotNull;
import com.bank.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * User specific Endpoints
 * */
@CrossOrigin
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> addUser (@RequestBody @NotNull UserDTO user) {

        UserDTO userDTO = userService.addNewUser(user);
        ResponseDTO responseDTO = new ResponseDTO(Arrays.asList(userDTO), null);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

}
