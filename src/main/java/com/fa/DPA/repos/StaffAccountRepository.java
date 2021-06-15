package com.fa.DPA.repos;

import com.fa.DPA.model.StaffAccount;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffAccountRepository extends BaseRepository<StaffAccount>{
    @Override
    List<StaffAccount> findAll();

    @Override
    List<StaffAccount> findAll(Sort sort);

    @Override
    List<StaffAccount> findAllById(Iterable<Long> iterable);

    @Override
    Optional<StaffAccount> findById(Long aLong);


}
