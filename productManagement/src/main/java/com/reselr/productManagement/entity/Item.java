package com.reselr.productManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    private Long userId;
    private String itemCategory;
    private String itemName;
    private String itemDesc;
    private String itemPrice;
    private String imageUrl;
    private Long totalRating;
    private Long noOfResponses;
}
