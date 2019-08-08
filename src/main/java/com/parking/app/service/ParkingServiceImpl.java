package com.parking.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.app.entity.EmployeeParking;
import com.parking.app.entity.ParkingSpot;
import com.parking.app.entity.Reset;
import com.parking.app.repository.ParkingRepository;
import com.parking.app.repository.ResetRepository;

@Service
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	private ParkingRepository parkingRepository;
	
	
	
	@Autowired
	private ResetRepository resetRepository;
	
	@Override
	public ParkingSpot release(int spotId) {

		ParkingSpot parkingSpot=new ParkingSpot();
		Optional<ParkingSpot> bookedSpot=parkingRepository.findById(spotId);
		if(bookedSpot.isPresent())
		{
			parkingSpot=bookedSpot.get();
			
			parkingSpot.setStatus("AVAILABLE");
			parkingRepository.save(parkingSpot);
			
		}

		
		return parkingSpot;
	}

	@Override
	public String reset() {

		

		List<Reset> reset=resetRepository.findAll();
		for (Reset reset2 : reset) {
			ParkingSpot slot=new ParkingSpot();
			slot.setParkingId(reset2.getParkingId());
			slot.setReserved(reset2.getReserved());
			slot.setStatus(reset2.getStatus());
			slot.setEmpId(reset2.getEmpId());
			parkingRepository.save(slot);
		}
		
		return "ok";
	}

	@Override
	public List<ParkingSpot> viewSpot() {
		String status="AVAILABLE";
		
		List<ParkingSpot> spotAvailable=parkingRepository.findByStatus(status);
		return spotAvailable;
	}

	@Override
	public ParkingSpot book(EmployeeParking empParking, int spotId) {

		ParkingSpot parkingSpotStore=new ParkingSpot();
		Optional<ParkingSpot> parkingSpotRepo=parkingRepository.findById(spotId);
		if(parkingSpotRepo.isPresent())
		{
			
			parkingSpotStore=parkingSpotRepo.get();
			if(parkingSpotStore.getStatus().equalsIgnoreCase("AVAILABLE"))
			{
				parkingSpotStore.setEmpId(empParking.getEmpId());
				parkingSpotStore.setStatus("BOOKED");
				parkingRepository.save(parkingSpotStore);
			}
		}
		return parkingSpotStore;
	}

}
