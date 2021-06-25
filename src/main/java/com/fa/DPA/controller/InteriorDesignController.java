package com.fa.DPA.controller;


import com.fa.DPA.constant.Constant;
import com.fa.DPA.controller.output.InteriorOutput;
import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.service.InteriorDesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class InteriorDesignController {
    @Autowired
    private InteriorDesignService interiorDesignService;

    HttpHeaders httpHeaders = new HttpHeaders();

    @PostMapping(value = "/interior")
    public ResponseEntity<InteriorDesign> createInterior(@RequestBody InteriorDesign interiorDesign){
        InteriorDesign interiorDesignReturn = interiorDesignService.save(interiorDesign);
        if(interiorDesignReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        try {
            httpHeaders.setLocation(new URI("/home"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }



    @PutMapping(value = "/interior/{id}")
    public ResponseEntity<InteriorDesign> updateInterior(@RequestBody InteriorDesign interiorDesign, @PathVariable("id") long id){
        interiorDesign.setId(id);
        interiorDesign = interiorDesignService.save(interiorDesign);
        if(interiorDesign == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        try{
            httpHeaders.setLocation(new URI("/discount"));

        }  catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }



    @DeleteMapping(value = "/interior")
    public ResponseEntity<Object> delete(@RequestBody long[] id ){
        try {
            interiorDesignService.delete(id);
        } catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            httpHeaders.setLocation(new URI("/discount"));

        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }



    /*@GetMapping(value = "/interior")
    public ResponseEntity<Map<String, Object> > getInteriorPaging(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) int page){
        Map<String, Object> response = new HashMap<>();
        List<InteriorDesign> interiorDesignList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try{
            Page<InteriorDesign> interiorDesignPage = interiorDesignService.getALLInteriorPaging(pageable);
            interiorDesignList = interiorDesignPage.getContent();
            response.put("interiorList", interiorDesignList);
            response.put("currentPage", interiorDesignPage.getNumber());
            response.put("totalPage", interiorDesignPage.getTotalPages());
            response.put("totalItem", interiorDesignPage.getTotalElements());

        } catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/
    @GetMapping(value = "/interior")
    public InteriorOutput getAllByPaging(@RequestParam("page") int page){

            InteriorOutput result = new InteriorOutput();
            result.setPage(page);
            Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
            result.setListResut(interiorDesignService.findAll(pageable));
            result.setTotalPage((int) Math.ceil((double) (interiorDesignService.totalItems()) / Constant.DEFAULT_PAGE_SIZE));
            return result;


    }

}
