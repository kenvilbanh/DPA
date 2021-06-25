package com.fa.DPA.converter;

import com.fa.DPA.dto.InteriorDesignDTO;
import com.fa.DPA.model.InteriorDesign;

import org.springframework.stereotype.Component;



@Component
public class InteriorDesignConverter {
    public InteriorDesign toEntity(InteriorDesignDTO interiorDesignDTO){
        InteriorDesign entity = new InteriorDesign();
        entity.setTitle(interiorDesignDTO.getTitle());
        entity.setContent(interiorDesignDTO.getContent());
        entity.setDescription(interiorDesignDTO.getDescription());
        entity.setImageSource(interiorDesignDTO.getImageSource());
        entity.setCreateBy(interiorDesignDTO.getCreateBy());
        entity.setCreateDate(interiorDesignDTO.getCreateDate());

        return entity;
    }

    public InteriorDesignDTO toDto(InteriorDesign entity){
        InteriorDesignDTO dto = new InteriorDesignDTO();
        if(entity.getId()!= null){
            dto.setId(entity.getId());
        }
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setDescription(entity.getDescription());
        dto.setImageSource(entity.getImageSource());
        dto.setSubCategoryName(entity.getSubCategories().toString());
        dto.setCreateBy(entity.getCreateBy());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    public InteriorDesign toEntity(InteriorDesignDTO interiorDesignDTO, InteriorDesign entity){

        entity.setTitle(interiorDesignDTO.getTitle());
        entity.setContent(interiorDesignDTO.getContent());
        entity.setDescription(interiorDesignDTO.getDescription());
        entity.setImageSource(interiorDesignDTO.getImageSource());
        entity.setCreateBy(interiorDesignDTO.getCreateBy());
        entity.setCreateDate(interiorDesignDTO.getCreateDate());
        return entity;
    }
}
