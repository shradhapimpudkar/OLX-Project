package com.olx.actuator;

import com.olx.dto.Advertise;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Endpoint(id = "advertisestats")
public class AdvertiseStateActuator {

    final List<Advertise> advertises = new ArrayList<>();

    @PostConstruct
    public void initialize() {
    	
		
		/*
		 * advertises.add(new Advertise( 1, "Mobile phone", 5, 2, "Open", "Sofa", 50000,
		 * "Mobile phone advertise", LocalDateTime.now(), LocalDateTime.now(),
		 * "Meghana", "Shradha Pimpudkar" ));
		 * 
		 * advertises.add(new Advertise( 2, "Laptop", 6, 10, 60000, "Laptop advertise",
		 * LocalDateTime.now(), LocalDateTime.now(), "Jugnu", "Shradha Pimpudkar" ));
		 * 
		 * advertises.add(new Advertise( 3, "Garments", 7, 11, 70000,
		 * "Mobile phone advertise", LocalDateTime.now(), LocalDateTime.now(),
		 * "Kaivalya", "Shradha Pimpudkar" ));
		 */

        
    }

    @ReadOperation
    public List<Advertise> getAdvertises(@Selector String status) {
        if (status.isEmpty()) {
            return advertises;
        } else {
            return getFilteredList(status);
        }
    }

    private List<Advertise> getFilteredList(String status) {
        List<Advertise> filteredList = new ArrayList<>();
        for (Advertise advertise : advertises) {
            if (String.valueOf(advertise.getStatusId()).equalsIgnoreCase(status)) {
                filteredList.add(advertise);
            }
        }
        return filteredList;
    }
}