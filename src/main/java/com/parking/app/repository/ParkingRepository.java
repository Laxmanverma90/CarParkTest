package com.parking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.app.entity.ParkingSpot;

public interface ParkingRepository extends JpaRepository<ParkingSpot, Integer> {

	public List<ParkingSpot> findByStatus(String Status);
}
