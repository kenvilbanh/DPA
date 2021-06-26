package com.fa.DPA.repos;

import com.fa.DPA.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryRepository extends BaseRepository<Category> {

    /**
     * @param pageable
     * @return
     */
    @Override
    Page<Category> findAll(Pageable pageable);
}
