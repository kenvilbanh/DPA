package com.fa.DPA.repos;

import com.fa.DPA.model.StaffAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffAccountRepository extends BaseRepository<StaffAccount>{
    /**
     *
     * @param aLong
     * @return
     */
    @Override
    Optional<StaffAccount> findById(Long aLong);

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    Page<StaffAccount> findAll(Pageable pageable);

    /**
     *
     * @param s
     * @return
     */
    boolean existsStaffAccountById(StaffAccount s);
}
