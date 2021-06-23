package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.controller.output.InteriorOutput;
import com.fa.DPA.controller.output.SubcategoryOutput;
import com.fa.DPA.model.SubCategory;
import com.fa.DPA.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SubcategoryController {
    private SubcategoryService service;
    @Autowired
    public SubcategoryController(SubcategoryService service) {
        this.service = service;
    }

    HttpHeaders httpHeaders = new HttpHeaders();
    @PostMapping("/subcategory")
    public ResponseEntity<SubCategory> createSubcategory(@RequestBody SubCategory subCategory){

        SubCategory subcategoryReturn = service.saveSubcategory(subCategory);

        if(subcategoryReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        try {
            httpHeaders.setLocation(new URI("/subcategory"));

        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @PutMapping(value = "/subcategory/{id}")
    public ResponseEntity<SubCategory> updateSubcategory(@RequestBody SubCategory subCategory, @PathVariable("id") long id){
        subCategory.setId(id);
        subCategory = service.saveSubcategory(subCategory);
        System.out.println(subCategory);
        if(subCategory == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            httpHeaders.setLocation(new URI("/subcategory"));

        }  catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    /*@GetMapping(value = "/subcategory")
    public ResponseEntity<Map<String, Object>> getPage(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) int page){
        Map<String, Object> response = new HashMap<>();
        List<SubCategory> subCategoryList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try {
            Page<SubCategory> subCategoryPaging = service.getAllSubcategory(pageable);
            subCategoryList = subCategoryPaging.getContent();
            response.put("subcategoryList", subCategoryList);
            response.put("currentPage", subCategoryPaging.getNumber());
            response.put("totalPage", subCategoryPaging.getTotalPages());
            response.put("totalItem", subCategoryPaging.getTotalElements());
        } catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

    @GetMapping(value = "/subcategory")
    public SubcategoryOutput showSubcategory(@RequestParam("page") int page){

        SubcategoryOutput result = new SubcategoryOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        result.setListResut(service.findAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (service.totalItems()) / Constant.DEFAULT_PAGE_SIZE));
        return result;


    }
}
