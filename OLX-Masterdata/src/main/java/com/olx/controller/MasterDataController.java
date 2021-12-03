package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.service.LoginMasterDataDelegate;
import com.olx.service.MasterDataService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/olx/masterdata")
public class MasterDataController {

    @Autowired
    MasterDataService masterDataService;
    
    @Autowired
    LoginMasterDataDelegate loginDelegate;

    
    //#Query to create masterdata category
    @ApiOperation(value = "Add a new masterdata category")
    @PostMapping(value = "/category",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Category> addAdvertisement(@RequestBody Category category,
                                                      @RequestHeader("Authorization") String authToken) {
        if (isValidToken(authToken)) {
            return masterDataService.createCategory(category, authToken);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
    
    private boolean isValidToken(String authToken) {
        ResponseEntity<Boolean> validTokenResponse = loginDelegate.validateToken(authToken);
        return validTokenResponse.hasBody() && Boolean.TRUE.equals(validTokenResponse.getBody());
    }


	@ApiOperation(value = "Returns all categories")
    @GetMapping(value="/category",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(masterDataService.getAllCategories(), HttpStatus.OK);
    }

	//#Query 10
    @ApiOperation(value = "Returns a category by id")
    @GetMapping(value = "/category/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
        return new ResponseEntity<>(masterDataService.getCategoryById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all status")
    @GetMapping(value = "/status",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Status>> getStatus() {
        return new ResponseEntity<>(masterDataService.getStatus(), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns a status by id")
    @GetMapping(value = "/status/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Status> getStatusById(@PathVariable("id") int id) {
        return new ResponseEntity<>(masterDataService.getStatusById(id), HttpStatus.OK);
    }
    
    
    
    /////
    @ApiOperation("Validate Category id")
    @GetMapping(value = "/category/validate{categoryId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> validateCategoryId(@RequestParam("categoryId") int catgeoryId){
    return masterDataService.validateCategoryId(catgeoryId);
    }


    @ApiOperation("Validate Status id")
    @GetMapping(value = "/status/validate{statusId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> validateStatusId(@RequestParam("statusId") int statusId){
    return masterDataService.validateStatusId(statusId);
    }


    ///////////
    @ApiOperation("Get Category Name")
    @GetMapping(value = "/category/name{categoryId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCategoryNameById(@RequestParam("categoryId") int catgeoryId){
    return masterDataService.getCategoryNameById(catgeoryId);
    }

    @ApiOperation("Get Status Name")
    @GetMapping(value = "/status/name{statusId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getStatusNameById(@RequestParam("statusId") int statusId){
    return masterDataService.getStatusNameById(statusId);
    }
}
