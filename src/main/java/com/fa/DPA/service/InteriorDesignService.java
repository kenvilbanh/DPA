package com.fa.DPA.service;

import com.fa.DPA.converter.InteriorDesignConverter;
import com.fa.DPA.dto.InteriorDesignDTO;
import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.model.SubCategory;
import com.fa.DPA.repos.InteriorDesignRepository;
import com.fa.DPA.repos.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class InteriorDesignService {
    @Autowired
    private InteriorDesignRepository interiorDesignRepository;


    @Autowired
    private InteriorDesignConverter converter;

    public InteriorDesign save(InteriorDesign interiorDesign){
        try{
            InteriorDesign interiorDesignReturn = new InteriorDesign();
            if(interiorDesign.getId()!=null){
                InteriorDesign oldInteriorDesign = interiorDesignRepository.findById(interiorDesign.getId()).orElse(new InteriorDesign());
                oldInteriorDesign.setTitle(interiorDesign.getTitle());
                oldInteriorDesign.setDescription(interiorDesign.getDescription());
                oldInteriorDesign.setContent(interiorDesign.getContent());
                oldInteriorDesign.setImageSource(interiorDesign.getImageSource());

                interiorDesignReturn = oldInteriorDesign;
            } else{
                interiorDesignReturn = interiorDesign;
            }
            interiorDesignRepository.save(interiorDesignReturn);
            return interiorDesignReturn;
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

   /*public InteriorDesignDTO saveInterior(InteriorDesignDTO interiorDesignDTO){
        InteriorDesign entity = new InteriorDesign();
        if(interiorDesignDTO.getId() != null){
            InteriorDesign oldEntity = interiorDesignRepository.findById(interiorDesignDTO.getId()).orElse(new InteriorDesign());
            entity = converter.toEntity(interiorDesignDTO, oldEntity);
        } else {
            entity = converter.toEntity(interiorDesignDTO);
        }
        List<SubCategory> subCategory = subCategoryRepository.findOneByName(interiorDesignDTO.getSubCategoryName());
        entity.setSubCategories(subCategory);
        entity  = interiorDesignRepository.save(entity);

        return converter.toDto(entity);

    }*/

    public void delete(long[] ids){
        for (long items : ids){
            interiorDesignRepository.deleteById(items);
        }
    }

    public Page<InteriorDesign> getALLInteriorPaging(Pageable pageable){
        try{
            return interiorDesignRepository.findAll(pageable);
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public List<InteriorDesignDTO> findAll(Pageable pageable){
        List<InteriorDesignDTO> result = new ArrayList<>();
        List<InteriorDesign> interiorDesigns = interiorDesignRepository.findAll(pageable).getContent();
        for (InteriorDesign items : interiorDesigns){
            InteriorDesignDTO designDTO = converter.toDto(items);
            result.add(designDTO);
        }
        return result;
    }

    public int totalItems(){
        return (int) interiorDesignRepository.count();
    }


}
