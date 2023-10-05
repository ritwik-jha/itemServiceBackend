package com.reselr.productManagement.service;

import com.reselr.productManagement.entity.ImageData;
import com.reselr.productManagement.entity.Item;
import com.reselr.productManagement.entity.ResponseMessage;
import com.reselr.productManagement.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    private S3Service s3Service;

    @Autowired
    ItemService(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public ResponseMessage saveItem(Item item){
        ResponseMessage res = new ResponseMessage(1, "Failed", null);
        try{
            //creating ImageData from item
            ImageData imgData = new ImageData();
            imgData.setImageUrl(item.getImageUrl());
            imgData.setImageName("uploadedImage");
            imgData.setItemId(item.getItemId());

            //uploading to s3 and getting the url
            String imgUrl = s3Service.uploadFileToS3(imgData);

            //setting the url to the item imageUrl
            item.setImageUrl(imgUrl);

            //saving to database
            Item savedItem = itemRepository.save(item);
            res.setItem(savedItem);
            res.setCode(0);
            res.setMessage("Success");
            return res;
        }
        catch (Exception e){
            res.setMessage(e.toString());
            return res;
        }

    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public List<Item> getItemsPostedByUser(Long id){
        return itemRepository.findByUserId(id);
    }

    public List<Item> getItemsByCategory(String cat){
        return itemRepository.findByItemCategory(cat);
    }

    public ResponseMessage updateItem(Long id, Item item){
        Item currItem = itemRepository.findById(id).get();
        ResponseMessage res = new ResponseMessage(1, "Failed", null);
        if(currItem != null){
            if(!item.getItemCategory().equals(currItem.getItemCategory())){
                currItem.setItemCategory(item.getItemCategory());
            }

            if(!item.getItemName().equals(currItem.getItemName())){
                currItem.setItemName(item.getItemName());
            }

            if(!item.getItemDesc().equals(currItem.getItemDesc())){
                currItem.setItemDesc(item.getItemDesc());
            }

            if(!item.getItemPrice(). equals(currItem.getItemPrice())){
                currItem.setItemPrice(item.getItemPrice());
            }
            itemRepository.save(currItem);
            res.setMessage("Success");
            res.setItem(currItem);
            res.setCode(0);
        }
        return res;
    }

    public ResponseMessage addReview(Long id, Long value){
        Item item = itemRepository.findById(id).get();
        ResponseMessage res = new ResponseMessage(1, "Failed", null);
        if(item != null){
            item.setTotalRating(item.getTotalRating()+value);
            item.setNoOfResponses(item.getNoOfResponses()+1);
            itemRepository.save(item);
            res.setCode(0);
            res.setItem(item);
            res.setMessage("Success");
        }
        return res;
    }
}
