package com.bank.service.service.schema.specification;

import com.bank.service.dto.UserDTO;

/**
 * Represent the specification which includes user related business logic operations.
 * */
public interface IUserService {

    /**
     * Save new user
     * @param user UserDTO
     * @return UserDTO
     * */
    public abstract UserDTO addNewUser(UserDTO user);

}
