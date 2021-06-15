package com.fa.DPA.repos;

import com.fa.DPA.model.CustomerAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends BaseRepository<CustomerAccount>{
    @Override
    List findAll();

    @Override
    List findAll(Sort sort);

    @Override
    Optional findById(Long aLong);

    @Override
    List<CustomerAccount> findAllById(Iterable<Long> iterable);

    @Override
    Page findAll(Pageable pageable);


}
