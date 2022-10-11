package com.appLabIT.onlineLibrary.repository;

import com.appLabIT.onlineLibrary.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Integer> {
    List<Rent> findByUserId(Integer userId);
}
