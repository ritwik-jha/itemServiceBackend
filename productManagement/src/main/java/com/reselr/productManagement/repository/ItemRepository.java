package com.reselr.productManagement.repository;

import com.reselr.productManagement.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByUserId(Long id);

    public List<Item> findByItemCategory(String category);
}
