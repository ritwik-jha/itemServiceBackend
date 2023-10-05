package com.reselr.productManagement.controller;

import com.reselr.productManagement.entity.Item;
import com.reselr.productManagement.entity.ResponseMessage;
import com.reselr.productManagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/save")
    public ResponseMessage saveItemFunction(@RequestBody Item item){
        return itemService.saveItem(item);
    }

    @GetMapping("/")
    public List<Item> allItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/byuser/{id}")
    public List<Item> itemsPostedByUser(@PathVariable("id") Long id){
        return itemService.getItemsPostedByUser(id);
    }

    @GetMapping("/category/{cat}")
    public List<Item> filterByCategory(@PathVariable("cat") String cat){
        return itemService.getItemsByCategory(cat);
    }

    @PutMapping("/update/{id}")
    public ResponseMessage updateItem(@PathVariable("id") Long id, @RequestBody Item item){
        return itemService.updateItem(id, item);
    }

    @GetMapping("/addreview/{id}/{value}")
    public ResponseMessage addReview(@PathVariable("id") Long id, @PathVariable("value") Long value){
        return itemService.addReview(id, value);
    }
}
