package com.boardcamp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.RentalModel;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long> {
    @Query(value = "SELECT COUNT(*) FROM rentals WHERE game_id= :id", nativeQuery = true)
    Long countRentalsPerGame(@Param("id") Long id);
}
