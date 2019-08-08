package com.parking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.app.entity.EmployeeParking;

public interface EmployeeRepository extends JpaRepository<EmployeeParking, Integer> {

}
