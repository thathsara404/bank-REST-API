package com.bank.service.controller;

import com.bank.service.consts.ErrorConst;
import com.bank.service.exception.ResourceNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Error controller to handle invalid resource path requests
 * */
@CrossOrigin
@RestController
public class IndexController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        throw new ResourceNotFoundException(ErrorConst.RESOURCE_PATH_NOT_FOUND_ERROR_MESSAGE, null);
    }

}
