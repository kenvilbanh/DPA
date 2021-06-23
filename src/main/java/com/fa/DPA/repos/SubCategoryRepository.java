package com.fa.DPA.repos;

import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.model.SubCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SubCategoryRepository extends BaseRepository<SubCategory> {
    /**
     * @param aLong
     * @return
     */
    @Override
    Optional<SubCategory> findById(Long aLong);




}
