package com.reselr.productManagement.controller;

import com.reselr.productManagement.entity.Item;
import com.reselr.productManagement.entity.ResponseMessage;
import com.reselr.productManagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/item/save")
    public ResponseMessage saveItemFunction(@RequestBody Item item){
        return itemService.saveItem(item);
    }

    @GetMapping("/item/")
    public List<Item> allItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/item/byuser/{id}")
    public List<Item> itemsPostedByUser(@PathVariable("id") Long id){
        return itemService.getItemsPostedByUser(id);
    }

    @GetMapping("/item/category/{cat}")
    public List<Item> filterByCategory(@PathVariable("cat") String cat){
        return itemService.getItemsByCategory(cat);
    }

    @PutMapping("/item/update/{id}")
    public ResponseMessage updateItem(@PathVariable("id") Long id, @RequestBody Item item){
        return itemService.updateItem(id, item);
    }

    @GetMapping("/item/addreview/{id}/{value}")
    public ResponseMessage addReview(@PathVariable("id") Long id, @PathVariable("value") Long value){
        return itemService.addReview(id, value);
    }
}
