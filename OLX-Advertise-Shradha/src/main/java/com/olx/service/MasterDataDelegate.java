package com.olx.service;

import com.olx.dto.Category;
import com.olx.dto.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;

interface MasterDataDelegate {
    
    ResponseEntity<List<Category>> getCategories();

    ResponseEntity<Category> getCategoryById(int categoryId);

    ResponseEntity<List<Status>> getStatus();

    ResponseEntity<Status> getStatusById(int statusId);
    
    
    /////
    public Boolean validateCategoryId(int categoryId);
    public Boolean validateStatusId(int statusId);

    public ResponseEntity<String> getCategoryNameById(int categoryId);

    public ResponseEntity<String> getStatusNameById(int statusId);
    
}
