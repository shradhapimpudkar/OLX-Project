package com.olx.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertise;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InvalidAdvertiseDataException;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.exception.InvalidCategoryIdException;
import com.olx.repository.AdvertiseRepository;
import com.olx.repository.SearchCriteriaSpecification;
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

     /*Advertise service apis for version V1*/
	
    // 7
	@Override
	public Advertise addAdvertisement(Advertise advertise, String username) {
	  if (advertise == null) {
	      throw new InvalidAdvertiseDataException(ExceptionConstants.INVALID_CREATE_ADVERTISE_DATA);
	  }
	
	  //is category id and status id is valid
	if(  masterDataDelegate.validateCategoryId(advertise.getCategoryId())
			== masterDataDelegate.validateStatusId(advertise.getStatusId()))
	{
	  advertise.setUsername(username);
	  advertise.setCreatedDate(LocalDateTime.now());
	  advertise.setModifiedDate(LocalDateTime.now());
	  advertise.setStatusId(1);
	
	  return AdvertiseConverterUtil.convertEntityToDto(
	          modelMapper,
	          advertiseRepository.save(AdvertiseConverterUtil.convertDtoToEntity(modelMapper, advertise))
	  );
	}else {
		 throw new InvalidCategoryIdException(ExceptionConstants.MISMATCH_CATEGORY_ID);
	}
	}
	
	// 8
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
	  oldEntity.setModifiedDate(LocalDateTime.now());
	  oldEntity.setStatusId(Math.max(oldEntity.getStatusId(), advertise.getStatusId()));
	
	  return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseRepository.save(oldEntity));
	}
	
	// 9
	@Override
	public List<Advertise> getAdvertisementByUser(String username) {
	  return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseRepository.findByUsername(username));
	}
	
	// 10
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
	
	// 11
	public boolean deleteAdvertisementById(int adId, String username) {
	
	  if (!advertiseRepository.findById(adId).isPresent()) {
	      throw new InvalidAdvertiseIdException(adId);
	  }
	  advertiseRepository.deleteByIdAndUsername(adId, username);
	  return !advertiseRepository.findById(adId).isPresent();
	}
	
	// 12
	@Override
	public List<Advertise> searchAdvertisementBySearchCriteria(String searchText, int categoryId, String postedBy, String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortBy, String sortOn, int startIndex, int records, int statusId) {
	  Sort sort;
	  if (sortBy.equalsIgnoreCase("asc")) {
	      sort = Sort.by(Sort.Direction.ASC, sortOn);
	  } else {
	      sort = Sort.by(Sort.Direction.DESC, sortOn);
	  }
	
	  Pageable pageWithFewRecords = PageRequest.of(startIndex, records, sort);
	
	  SearchCriteriaSpecification spec1 = new SearchCriteriaSpecification(
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
	
	// 13
	@Override
	public List<Advertise> searchAdvertisementBySearchText(String searchText) {
	  return AdvertiseConverterUtil.convertEntityToDto(
	          modelMapper, advertiseRepository.findByTitleOrDescriptionContainingIgnoreCase(searchText, searchText)
	  );
	}
	
	// 14
	@Override
	public Advertise getAdvertisementById(int adId) {
	  if (!advertiseRepository.findById(adId).isPresent()) {
	      throw new InvalidAdvertiseIdException(adId);
	  }
	  if (advertiseRepository.findById(adId).isPresent()) {
	      return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseRepository.getById(adId));
	  }
	  return new Advertise();
	}
	}