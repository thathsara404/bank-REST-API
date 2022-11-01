package com.bank.service.repository.schema.specification;

import com.bank.service.entity.schema.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Represent the specification which includes branch related operations.
 * */
@Repository
public interface IBranchRepository extends JpaRepository<Branch, Long> {

    public abstract Branch getByBranchNumber(final UUID branchNumber);

}
