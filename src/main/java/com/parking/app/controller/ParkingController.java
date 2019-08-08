package com.parking.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.app.entity.EmployeeParking;
import com.parking.app.entity.ParkingSpot;
import com.parking.app.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	@Autowired
	private ParkingService parkingService;
	
	@PutMapping("/release/{spotId}")
	public ParkingSpot releaseSpot(@PathVariable int spotId)
	{
		
		
		return parkingService.release(spotId);
	}
	
   @GetMapping("/reset")
   public String reset()
   {
	   parkingService.reset();
	   return "ok";
   }
   
   @GetMapping("/showSlot")
   public List<ParkingSpot> viewParkingSpot()
   {
	   
	   return parkingService.viewSpot();
   }
   
   @PutMapping("/book/{spotId}")
   public ParkingSpot bookSpot(@RequestBody EmployeeParking empParking,@PathVariable int spotId)
   {
	  
	   return  parkingService.book(empParking, spotId);
   }
}
