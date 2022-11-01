package com.bank.service.repository.schema.specification;

import com.bank.service.entity.schema.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Represent the specification which includes user related operations.
 * */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    public abstract User getByUserId(final UUID userId);

}
