package com.olx.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.Category;
import com.olx.dto.Status;

	public interface MasterDataService {
		
		List<Status> getStatus();

	    Status getStatusById(int id);

	    List<Category> getAllCategories();

	    Category getCategoryById(int id);
	    
	    ResponseEntity<Category> createCategory(Category category,String token);
	   
	    
	    ///
	    public ResponseEntity<Boolean> validateCategoryId(int categoryId);
	    public ResponseEntity<Boolean> validateStatusId(int statusId);


	    public ResponseEntity<String> getCategoryNameById(int categoryId);

	    public ResponseEntity<String> getStatusNameById(int statusId);
	}
