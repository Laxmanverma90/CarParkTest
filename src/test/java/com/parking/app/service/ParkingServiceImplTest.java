package com.parking.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.parking.app.entity.EmployeeParking;
import com.parking.app.entity.ParkingSpot;
import com.parking.app.entity.Reset;
import com.parking.app.repository.ParkingRepository;
import com.parking.app.repository.ResetRepository;

@RunWith(MockitoJUnitRunner.class)
public class ParkingServiceImplTest {

	
	@Mock
	ResetRepository resetRepository;
	
	@Mock
	ParkingRepository parkingRepository;
	
	@InjectMocks
	ParkingServiceImpl parkingServiceImpl;
	
	
	
	
	@Test
	public void testRelease() {
		
		ParkingSpot parkingSpot=new ParkingSpot();
		parkingSpot.setEmpId(1);
		parkingSpot.setParkingId(1);
		parkingSpot.setReserved("YES");
		parkingSpot.setStatus("BOOKED");
    	Optional<ParkingSpot> optionalParking=Optional.of(parkingSpot);
      when(parkingRepository.findById(Mockito.anyInt())).thenReturn(optionalParking);
      ParkingSpot expected =parkingServiceImpl.release(1);
      assertEquals("AVAILABLE", expected.getStatus());
	}

	@Test
	public void testReset() {
		List<Reset> restList=new ArrayList<>();
		Reset reset=new Reset();
		reset.setEmpId(1);
		reset.setParkingId(1);
		reset.setReserved("YES");
		reset.setStatus("BOOKED");
		restList.add(reset);
		
		List<ParkingSpot> ParkingSpotList=new ArrayList<>();
		ParkingSpot parkingSpot=new ParkingSpot();
		parkingSpot.setEmpId(1);
		parkingSpot.setParkingId(1);
		parkingSpot.setReserved("YES");
		parkingSpot.setStatus("BOOKED");
		ParkingSpotList.add(parkingSpot);
		when(resetRepository.findAll()).thenReturn(restList);
		
		String statusReset=parkingServiceImpl.reset();
		assertEquals("ok", statusReset);
	}

	@Test
	public void testViewSpot() {
		List<ParkingSpot> ParkingSpotList=new ArrayList<>();
		ParkingSpot parkingSpot1=new ParkingSpot();
		ParkingSpot parkingSpot2=new ParkingSpot();
		parkingSpot1.setEmpId(1);
		parkingSpot1.setParkingId(1);
		parkingSpot1.setReserved("YES");
		parkingSpot1.setStatus("BOOKED");
		parkingSpot2.setEmpId(1);
		parkingSpot2.setParkingId(1);
		parkingSpot2.setReserved("YES");
		parkingSpot2.setStatus("BOOKED");
		ParkingSpotList.add(parkingSpot1);
		ParkingSpotList.add(parkingSpot2);
		when(parkingRepository.findByStatus("AVAILABLE")).thenReturn(ParkingSpotList);
		List<ParkingSpot> listViewSpot=parkingServiceImpl.viewSpot();
		assertEquals(2, listViewSpot.size());
		
	}

	@Test
	public void testBook() {
		EmployeeParking empParking=new EmployeeParking();
		empParking.setDesignation("SE");
		empParking.setEmpId(1);
		empParking.setExperience(15);
		empParking.setName("name");
		ParkingSpot parkingSpot=new ParkingSpot();
		parkingSpot.setEmpId(1);
		parkingSpot.setParkingId(1);
		parkingSpot.setReserved("YES");
		parkingSpot.setStatus("AVAILABLE");
		Optional<ParkingSpot> optionalParking=Optional.of(parkingSpot);
		when(parkingRepository.findById(Mockito.anyInt())).thenReturn(optionalParking);
		ParkingSpot ParkingSpotActual=parkingServiceImpl.book(empParking, 1);
		assertEquals("BOOKED", ParkingSpotActual.getStatus());
	}

}
