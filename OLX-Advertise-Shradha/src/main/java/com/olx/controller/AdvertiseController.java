package com.olx.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertise;
import com.olx.service.AdvertiseService;
import com.olx.service.LoginDelegate;
import com.olx.utils.DateUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/advertise")
public class AdvertiseController {

    @Autowired
    AdvertiseService advertiseService;

    @Autowired
    LoginDelegate loginDelegate;

    //#Query 7
    @ApiOperation(value = "Add a new advertise")
    @PostMapping(value = "",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Advertise> addAdvertisement(@RequestBody Advertise advertise,
                                                      @RequestHeader("Authorization") String authToken) {
        if (isValidToken(authToken)) {
            return new ResponseEntity<>(advertiseService.addAdvertisement(advertise, getUserName(authToken)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    //#Query 8
    @ApiOperation(value = "Update a specific advertise by id")
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Advertise> updateAdvertisement(@PathVariable("id") int adId,
                                                         @RequestBody Advertise advertise,
                                                         @RequestHeader("Authorization") String authToken) {
        if (isValidToken(authToken)) {
            return new ResponseEntity<>(advertiseService.updateAdvertisement(adId, advertise), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    //#Query 9
    @ApiOperation(value = "Get all advertise posted by a user")
    @GetMapping(value = "/user/advertise",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Advertise>> getAdvertisementByUser(@RequestHeader("Authorization") String authToken) {
    	
        if (isValidToken(authToken)) {
            return new ResponseEntity<>(advertiseService.getAdvertisementByUser(getUserName(authToken)), HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    //#Query 10
    @ApiOperation(value = "Get a specific advertise by id posted by a user")
    @GetMapping(value = "/user/advertise/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Advertise> getAdvertisementOfUserById(@PathVariable("id") int adId, @RequestHeader("Authorization") String authToken) {
        if (isValidToken(authToken)) {
            return new ResponseEntity<>(advertiseService.getAdvertisementOfUserById(adId, getUserName(authToken)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    //#Query 11
    @ApiOperation(value = "delete a specific advertise by id posted by a user")
    @DeleteMapping(value = "/user/advertise/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Boolean> deleteAdvertisementById(@PathVariable("id") int adId, @RequestHeader("Authorization") String authToken) {
        if (isValidToken(authToken)) {
            return new ResponseEntity<>(advertiseService.deleteAdvertisementById(adId, getUserName(authToken)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    //#Query 12
    @ApiOperation(value = "Search for an advertise filtered by different search criteria")
    @GetMapping(value = "/search/filtercriteria",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Advertise>> searchAdvertisementBySearchCriteria(
            @RequestParam(value = "searchText", required = false) String searchText,
            @RequestParam(value = "category", defaultValue = "-1", required = false) int categoryId,
            @RequestParam(value = "postedBy", required = false) String postedBy,
            
            @RequestParam(value = "dateCondition", required = false) String dateCondition,
           
            @RequestParam(value = "onDate", required = false) String onDate,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            
//            @RequestParam("onDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
//            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
//            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,

            @RequestParam(value = "sortBy", defaultValue = "asc", required = false) String sortBy,
            @RequestParam(value = "sortOn", defaultValue = "modifiedDate", required = false) String sortOn,
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) int startIndex,
            @RequestParam(value = "records", defaultValue = "10", required = false) int records,
            @RequestParam(value = "status", defaultValue = "-1", required = false) int statusId
    ) {
        return new ResponseEntity<>(
                advertiseService.searchAdvertisementBySearchCriteria(
                        searchText,
                        categoryId,
                        postedBy,
                        dateCondition,
                        onDate == null ? null : DateUtils.convertStringToDate(onDate, DateUtils.DATE_FORMAT_YYYY_MM_DD),
                        fromDate == null ? null : DateUtils.convertStringToDate(fromDate, DateUtils.DATE_FORMAT_YYYY_MM_DD),
                        toDate == null ? null : DateUtils.convertStringToDate(toDate, DateUtils.DATE_FORMAT_YYYY_MM_DD),
                        sortBy,
                        sortOn,
                        startIndex,
                        records,
                        statusId
                ), HttpStatus.OK);
    }

    //#Query 13
    @ApiOperation(value = "Search for an advertise that contains query search text")
    @GetMapping(value = "/search",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Advertise>> searchAdvertisementBySearchText(@RequestParam("searchText") String searchText) {
        return new ResponseEntity<>(advertiseService.searchAdvertisementBySearchText(searchText), HttpStatus.OK);
    }

    //#Query 14
    @ApiOperation(value = "Get an advertise by id")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Advertise> getAdvertisementById(@PathVariable("id") int adId, @RequestHeader("Authorization") String authToken) {
        if (isValidToken(authToken)) {
        	String username = loginDelegate.getUsername(authToken).getBody();
            return new ResponseEntity<>(advertiseService.getAdvertisementById(adId,username), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    
    //Custom method for validating token
    private boolean isValidToken(String authToken) {
        ResponseEntity<Boolean> validTokenResponse = loginDelegate.validateToken(authToken);
        return validTokenResponse.hasBody() && Boolean.TRUE.equals(validTokenResponse.getBody());
    }
    
   //Custom method for getting username from token
    private String getUserName(String authToken) {
        ResponseEntity<String> userResponse = loginDelegate.getUsername(authToken);
        if (userResponse.hasBody()) {
            return userResponse.getBody();
        }
        return "";
    }
}
