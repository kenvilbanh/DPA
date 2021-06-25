package com.fa.DPA.repos;

import com.fa.DPA.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends BaseRepository<News>{

    @Override
    Page<News> findAll(Pageable pageable);

    @Override
    Optional<News> findById(Long id);

    @Override
    List<News> findAll(Sort sort);

    @Override
    News save(News news);

}
