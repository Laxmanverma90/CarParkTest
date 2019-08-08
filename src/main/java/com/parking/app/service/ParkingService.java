package com.parking.app.service;

import java.util.List;

import com.parking.app.entity.EmployeeParking;
import com.parking.app.entity.ParkingSpot;

public interface ParkingService {
	public ParkingSpot release(int spotId);
	public String reset();
	public List<ParkingSpot> viewSpot();
	public ParkingSpot book(EmployeeParking empParking,int spotId);
}
