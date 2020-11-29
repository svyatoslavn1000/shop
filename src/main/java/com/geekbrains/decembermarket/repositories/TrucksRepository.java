package com.geekbrains.decembermarket.repositories;

import com.geekbrains.decembermarket.entities.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TrucksRepository extends JpaRepository<Truck, Long> {

}
