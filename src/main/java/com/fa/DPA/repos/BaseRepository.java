package com.fa.DPA.repos;

import com.fa.DPA.model.AbstracModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface BaseRepository<T extends AbstracModel> extends JpaRepository<T, Long>{
}
