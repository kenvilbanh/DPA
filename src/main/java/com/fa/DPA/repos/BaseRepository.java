package com.fa.DPA.repos;

import com.fa.DPA.model.AbstractModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends AbstractModel> extends JpaRepository<T, Long>{
}
