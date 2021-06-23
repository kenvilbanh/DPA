package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.Category;
import com.fa.DPA.service.CategoryService;
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

public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    HttpHeaders httpHeaders = new HttpHeaders();

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category categoryReturn = categoryService.saveCategory(category);
        if(categoryReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            httpHeaders.setLocation(new URI("/category"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @PutMapping(value = "category/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") long id){
        category.setId(id);
        category = categoryService.saveCategory(category);
        if(category == null){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

        }
        try {
            httpHeaders.setLocation(new URI("/category"));

        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);

    }
    @GetMapping(value = "/category")
    public ResponseEntity<Map<String, Object>> getPage(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE)int page){
        Map<String, Object> response = new HashMap<>();
        List<Category> categoryList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try{
            Page<Category> categoryPage = categoryService.getAllCategory(pageable);
            categoryList = categoryPage.getContent();
            response.put("categoryList", categoryList);
            response.put("currentPage", categoryPage.getNumber());
            response.put("totalPage", categoryPage.getTotalPages());
            response.put("totalItem", categoryPage.getTotalElements());

        } catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
