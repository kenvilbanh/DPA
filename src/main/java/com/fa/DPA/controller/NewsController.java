package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.News;
import com.fa.DPA.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/news")
public class NewsController {
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService){
        this.newsService=newsService;
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getNewsPage(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE)int page
    ){
        Map<String, Object> response = new HashMap<>();
        List<News> newsList;
        Pageable pageable = PageRequest.of(page,Constant.DEFAULT_PAGE_SIZE);
        try {
            Page<News> newsPaging = newsService.getAllNewsPaging(pageable);
            //return list news
            newsList = newsPaging.getContent();
            response.put("newsList",newsList);
            response.put("currentPage",newsPaging.getNumber());
            response.put("totalPage",newsPaging.getTotalPages());
            response.put("totalItem",newsPaging.getTotalElements());
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable("id") long id){
        Optional<News> findNews = newsService.getNewsById(id);
        if(findNews.isPresent()){
            return new ResponseEntity<>(findNews.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //create News
    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news){
        try {
            News _news=newsService.saveNews(news);
            return new ResponseEntity<>(_news,HttpStatus.OK);
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable("id") long id,@RequestBody News news){
        Optional<News> newsData= newsService.getNewsById(id);
        if(newsData.isPresent()){
            News _news= newsData.get();
            _news.setTitle(news.getTitle());
            _news.setContent(news.getContent());
            _news.setDescription(news.getDescription());
            _news.setIsEnable(news.getIsEnable());
            return new ResponseEntity<>(newsService.saveNews(_news),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
