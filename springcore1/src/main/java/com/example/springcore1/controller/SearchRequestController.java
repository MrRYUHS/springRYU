package com.example.springcore1.controller;

import com.example.springcore1.dto.ItemDto;
import com.example.springcore1.model.NaverShopSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // JSON으로 응답함을 선언
public class SearchRequestController {

    private final NaverShopSearch naverShopSearch;

    @Autowired
    public SearchRequestController(NaverShopSearch naverShopSearch){
        this.naverShopSearch = naverShopSearch;
    }

    public List<ItemDto> getItems(@RequestParam String query){
        String resultString = naverShopSearch.search(query);
        return naverShopSearch.fromJSONtoItems(resultString);
    }
}
