package com.olx.service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertise;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InvalidAdvertiseDataException;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.exception.InvalidCategoryIdException;
import com.olx.repository.AdvertiseRepository;
import com.olx.repository.SearchFilterCriteriaSpecification;
import com.olx.utils.AdvertiseConverterUtil;
import com.olx.utils.ExceptionConstants;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

    @Autowired
    MasterDataDelegate masterDataDelegate;

    @Autowired
    AdvertiseRepository advertiseRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    LoginDelegate loginDelegate;

     /*Advertise service apis for version V1*/
	
    //#Query 7
	@Override
	public Advertise addAdvertisement(Advertise advertise, String username) {
	  if (advertise == null) {
	      throw new InvalidAdvertiseDataException(ExceptionConstants.INVALID_CREATE_ADVERTISE_DATA);
	  }
	
	//condition to check is category id and status id is valid
	if(  masterDataDelegate.validateCategoryId(advertise.getCategoryId()))
	{
		if( masterDataDelegate.validateStatusId(advertise.getStatusId())) {
	  advertise.setUsername(username);
	  advertise.setCreatedDate(LocalDate.now());
	  advertise.setModifiedDate(LocalDate.now());
	  advertise.setStatusId(2);
	
	  
	   AdvertiseEntity advertiseEntity = modelMapper.map(advertise,AdvertiseEntity.class);
	  
	   advertiseEntity =advertiseRepository.save(advertiseEntity);
	   
	   Advertise advertiseDto = modelMapper.map(advertiseEntity,Advertise.class);
	   
	   
	   
	    String categoryName = masterDataDelegate.getCategoryNameById(advertise.getCategoryId()).getBody();
		String statusName = masterDataDelegate.getStatusNameById(advertise.getStatusId()).getBody();

		advertiseDto.setCategoryType(categoryName);
		advertiseDto.setStatusType(statusName);
	   
	   return advertiseDto;
	  
	  
	  
		}else {
			throw new InvalidCategoryIdException();
		}
	}else {
		 throw new InvalidCategoryIdException(ExceptionConstants.MISMATCH_CATEGORY_ID);
	}
	}
	
	//#Query 8
	@Override
	public Advertise updateAdvertisement(int adId, Advertise advertise) {
	  if (!advertiseRepository.findById(adId).isPresent()) {
	      throw new InvalidAdvertiseIdException(adId);
	  }
	  if (advertise == null) {
	      throw new InvalidAdvertiseDataException(ExceptionConstants.INVALID_UPDATED_ADVERTISE_DATA);
	  }
	
	  AdvertiseEntity oldEntity = advertiseRepository.getById(adId);
	  oldEntity.setTitle(advertise.getTitle());
	  oldEntity.setCategoryId(advertise.getCategoryId());
	  oldEntity.setDescription(advertise.getDescription());
	  oldEntity.setPrice(advertise.getPrice());
	  oldEntity.setModifiedDate(LocalDate.now());
	  oldEntity.setStatusId(advertise.getStatusId());
	  
		String categoryName =  masterDataDelegate.getCategoryNameById(oldEntity.getCategoryId()).getBody();
		String statusName =  masterDataDelegate.getStatusNameById(oldEntity.getStatusId()).getBody();
		 
		Advertise adveriseDto = modelMapper.map(oldEntity, Advertise.class);
		adveriseDto.setCategoryType(categoryName);
		adveriseDto.setStatusType(statusName);

		return adveriseDto;
	
	}
	
	//#Query 9
	@Override
	public List<Advertise> getAdvertisementByUser(String username) {
		if(!username.isEmpty()) {

		List<AdvertiseEntity> advertiseList = advertiseRepository.findByUsername(username);

		Type listType = new TypeToken<List<Advertise>>(){}.getType();

		List<Advertise> adDetails = this.modelMapper.map(advertiseList,listType);

		for(Advertise advertise :adDetails) {
		
		String categoryName = masterDataDelegate.getCategoryNameById(advertise.getCategoryId()).getBody();
		String statusName = masterDataDelegate.getStatusNameById(advertise.getStatusId()).getBody();

		advertise.setCategoryType(categoryName);
		advertise.setStatusType(statusName);
		}
		return adDetails;
	
		}
		return null;	
			  
	}
	
	//#Query 10
	@Override
	public Advertise getAdvertisementOfUserById(int adId, String username) {
	  if (!advertiseRepository.findById(adId).isPresent()) {
	      throw new InvalidAdvertiseIdException(adId);
	  }
	  AdvertiseEntity advertise = advertiseRepository.findByIdAndUsername(adId, username);
	  if (advertise == null) {
	      return null;
	  } else {
		String categoryName =  masterDataDelegate.getCategoryNameById(advertise.getCategoryId()).getBody();
		String statusName =  masterDataDelegate.getStatusNameById(advertise.getStatusId()).getBody();
		 
		Advertise adveriseDto = modelMapper.map(advertise, Advertise.class);
		adveriseDto.setCategoryType(categoryName);
		adveriseDto.setStatusType(statusName);

		return adveriseDto;
	      
	  }
	}
	
	//#Query 11
	public boolean deleteAdvertisementById(int adId, String username) {
	
	  if (!advertiseRepository.findById(adId).isPresent()) {
	      throw new InvalidAdvertiseIdException(adId);
	  }
	  advertiseRepository.deleteByIdAndUsername(adId, username);
	  return !advertiseRepository.findById(adId).isPresent();
	}
	
	//#Query 12
	@Override
	public List<Advertise> searchAdvertisementBySearchCriteria(String searchText, int categoryId, String postedBy, String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortBy, String sortOn, int startIndex, int records, int statusId) {
	  Sort sort;
	  if (sortBy.equalsIgnoreCase("asc")) {
	      sort = Sort.by(Sort.Direction.ASC, sortOn);
	  } else {
	      sort = Sort.by(Sort.Direction.DESC, sortOn);
	  }
	
	  Pageable pageWithFewRecords = PageRequest.of(startIndex, records, sort);
	
	  SearchFilterCriteriaSpecification spec1 = new SearchFilterCriteriaSpecification(
	          searchText,
	          categoryId,
	          postedBy,
	          dateCondition,
	          onDate,
	          fromDate,
	          toDate,
	          statusId
	  );
	  Page<AdvertiseEntity> advertiseEntities = advertiseRepository.findAll(spec1, pageWithFewRecords);
	
	  return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseEntities.getContent());
	}
	
	//#Query 13
	@Override
	public List<Advertise> searchAdvertisementBySearchText(String searchText) {
		if(!searchText.isEmpty()) {
			List<AdvertiseEntity> advertiseList = advertiseRepository.findByTitleOrDescriptionContainingIgnoreCase(searchText,searchText);

			Type listType = new TypeToken<List<Advertise>>(){}.getType();

			List<Advertise> adDetails = this.modelMapper.map(advertiseList,listType);

			for(Advertise advertise :adDetails) {
			
			String categoryName = masterDataDelegate.getCategoryNameById(advertise.getCategoryId()).getBody();
			String statusName = masterDataDelegate.getStatusNameById(advertise.getStatusId()).getBody();

			advertise.setCategoryType(categoryName);
			advertise.setStatusType(statusName);
			}
			return adDetails;
		
			}
			return null;	
	}
	
	//#Query 14
	@Override
	public Advertise getAdvertisementById(int adId, String username) {
	  if (!advertiseRepository.findById(adId).isPresent()) {
	      throw new InvalidAdvertiseIdException(adId);
	  }
	  if (advertiseRepository.findById(adId).isPresent()) {
		  AdvertiseEntity advertise = advertiseRepository.findByIdAndUsername(adId, username);
		  if (advertise == null) {
		      return null;
		  } else {
			String categoryName =  masterDataDelegate.getCategoryNameById(advertise.getCategoryId()).getBody();
			String statusName =  masterDataDelegate.getStatusNameById(advertise.getStatusId()).getBody();
			 
			Advertise adveriseDto = modelMapper.map(advertise, Advertise.class);
			adveriseDto.setCategoryType(categoryName);
			adveriseDto.setStatusType(statusName);

			return adveriseDto;
		      
		  }
	  }
	  return new Advertise();
	}
	}