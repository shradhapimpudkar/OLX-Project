package com.olx.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.entity.CategoryEntity;

import com.olx.exceptions.InvalidCategoryDataException;
import com.olx.exceptions.InvalidCategoryIdException;
import com.olx.exceptions.InvalidStatusIdException;
import com.olx.repo.CategoryRepository;
import com.olx.repo.StatusRepository;
import com.olx.utility.CategoryDtoEntityConverter;
import com.olx.utility.StatusDtoEntityConverter;



@Service
public class MasterDataServiceImpl implements MasterDataService{
	
	@Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoriesRepository;

    @Autowired
    StatusRepository statusRepository;
	
	    
	/*@Override
	public List<Category> getAllCategories() {
//		List<Category> categories =new ArrayList<Category>();
//		categories.add(new Category(1,"Furniture"));
//		categories.add(new Category(2,"Real Estate"));
//
//		return categories;
		
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		List<Category> categoriesDtoList = new ArrayList<Category>();
		TypeMap<CategoryEntity,Category> typeMap = this.modelMapper.typeMap(CategoryEntity.class, Category.class);
		typeMap.addMappings((mapper) -> {
		      	mapper.map(source->source.getDesc(),Category::setCategory);
		      	
		});
		for(CategoryEntity categoryEntity : categoryEntities) {
			
			Category categoryDto = this.modelMapper.map(categoryEntity,Category.class);
			categoriesDtoList.add(categoryDto);
		}
		
		return categoriesDtoList;
	}*/

	/*@Override
	public List<Status> getCategoryStatus() {
		List<Status> status =new ArrayList<Status>();
		status.add(new Status(1,"Open"));
		status.add(new Status(2,"Closed"));
		return status;
	}*/

    
    @Override
    public List<Status> getStatus() {
       
    	return StatusDtoEntityConverter.convertListEntityToDto(modelMapper, statusRepository.findAll());
    }

    @Override
    public Status getStatusById(int id) {
        if (statusRepository.findById(id).isPresent()) {
            return StatusDtoEntityConverter.convertEntityToDto(modelMapper, statusRepository.getById(id));
        } else {
            throw new InvalidStatusIdException(id);
        }
    }

    @Override
    public List<Category> getAllCategories() {
    	

    	List<Category> categories =CategoryDtoEntityConverter.convertListEntityToDto(modelMapper, categoriesRepository.findAll());
        return categories;
    }

    @Override
    public Category getCategoryById(int id) {
        if (categoriesRepository.findById(id).isPresent()) {
            return CategoryDtoEntityConverter.convertEntityToDto(modelMapper, categoriesRepository.getById(id));
        } else {
            throw new InvalidCategoryIdException(id);
        }
    }

	
	
	 // Create masterdata category api
    public ResponseEntity<Category> createCategory(Category category,
                                      String token) {
        if (category == null) {
            throw new InvalidCategoryDataException("Invalid Masterdata Category Exception");
        }

        
        CategoryEntity entity = modelMapper.map(category,CategoryEntity.class);
       
     entity =   categoriesRepository.save(entity);
     
     Category categoryNew = modelMapper.map(entity, Category.class);
     
        
        return new ResponseEntity<Category>(categoryNew,HttpStatus.OK);
    }
    
    
    ////
    @Override
    public ResponseEntity<Boolean> validateCategoryId(int categoryId) {

    List<Category> list = getAllCategories();

    for(Category category : list) {

    if(category.getId() == categoryId) {
    return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
    }

    return new ResponseEntity<Boolean>(false,HttpStatus.OK);
    }



    @Override
    public ResponseEntity<Boolean> validateStatusId(int statusId) {

    List<Status> list = getStatus();

    for(Status status : list) {

    if(status.getId() == statusId) {
    return new ResponseEntity<Boolean>(true,HttpStatus.OK);

    }
    }

    return new ResponseEntity<Boolean>(false,HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> getCategoryNameById(int categoryId) {


    List<Category> categoryEntities =getAllCategories();

    for(Category category : categoryEntities) {

    if(category.getId() == categoryId) {

    return new ResponseEntity<String>(category.getCategory(),HttpStatus.OK);
    }

    }

    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<String> getStatusNameById(int statusId) {


    List<Status> statusList =getStatus();

    for(Status status : statusList) {

    if(status.getId() == statusId) {

    return new ResponseEntity<String>(status.getStatus(),HttpStatus.OK);
    }

    }

    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }
    
}