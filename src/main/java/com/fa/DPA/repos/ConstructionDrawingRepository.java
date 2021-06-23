package com.fa.DPA.repos;

import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.model.Discount;

import java.util.Optional;

public interface ConstructionDrawingRepository extends BaseRepository<ConstructionDrawing> {
    Optional<ConstructionDrawing> findOneByCode (String code);
}
