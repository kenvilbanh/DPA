package com.fa.DPA.converter;


import com.fa.DPA.dto.SubcategoryDTO;

import com.fa.DPA.model.SubCategory;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryConverter {

    public SubCategory toEntity(SubcategoryDTO dto){
        SubCategory entity = new SubCategory();
        entity.setName(dto.getName());
        entity.setStatus(dto.isStatus());
        return entity;
    }

    public SubcategoryDTO toDto(SubCategory entity){
        SubcategoryDTO dto = new SubcategoryDTO();
        if(entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setName(entity.getName());
        dto.setStatus(entity.isStatus());
        dto.setCategory(entity.getCategory().toString());
        return dto;
    }
}
