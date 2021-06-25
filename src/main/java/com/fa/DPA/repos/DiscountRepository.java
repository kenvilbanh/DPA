package com.fa.DPA.repos;

import com.fa.DPA.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends BaseRepository<Discount> {
    /**
     * @param pageable
     * @return
     */
    @Override
    Page<Discount> findAll (Pageable pageable);




    /**
     * @return
     */
    @Override
    long count();

}
