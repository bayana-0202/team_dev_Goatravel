package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BathType;

public interface BathTypeRepository extends JpaRepository<BathType, Integer> {

}