package com.bank.service.service.schema;

import com.bank.service.consts.ErrorConst;
import com.bank.service.dto.UserDTO;
import com.bank.service.entity.schema.User;
import com.bank.service.exception.UnExpectedErrorOccurredException;
import com.bank.service.repository.schema.specification.IUserRepository;
import com.bank.service.service.schema.specification.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


/**
 * Implementation of IUserService
 * */
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;
    /**
     * Save new user and return safely copied UserDTO
     * @param user UserDTO
     * @return UserDTO
     * */
    @Override
    public UserDTO addNewUser(final UserDTO user) {
        UserDTO userDTO = null;
        try {

            User userInsert = new User();
            userInsert.setEmail(user.getEmail());
            userInsert.setUsername(user.getUsername());
            userInsert.setFirstName(user.getFirstName());
            userInsert.setLastName(user.getLastName());
            userInsert.setPassword(user.getPassword());
            userInsert.setTele(user.getTele());

            User userInserted = userRepository.save(userInsert);
            userDTO = new UserDTO(userInserted);

        } catch (DataIntegrityViolationException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new UnExpectedErrorOccurredException(ErrorConst.UNEXPECTED_ERROR_OCCURRED_MESSAGE,
                    exception.getCause());
        }
        return userDTO;
    }


}
