package com.fa.DPA.repos;

import com.fa.DPA.model.AbstracModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository extends JpaRepository<AbstracModel, Long> {
}
