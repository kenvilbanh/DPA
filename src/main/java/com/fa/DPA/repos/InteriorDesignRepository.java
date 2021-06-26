package com.fa.DPA.repos;

import com.fa.DPA.model.CustomerContact;
import com.fa.DPA.model.InteriorDesign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InteriorDesignRepository extends BaseRepository<InteriorDesign> {
    /**
     * @param pageable
     * @return
     */
    @Override
    Page<InteriorDesign> findAll(Pageable pageable);

    /**
     * @param s
     * @return
     */
    @Override
    <S extends InteriorDesign> S save(S s);


    @Override
    void deleteById(Long aLong);

    /**
     * @return
     */
    @Override
    long count();




}
