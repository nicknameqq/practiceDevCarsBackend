package com.practice.practiceDevBackend.repository;

import com.practice.practiceDevBackend.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByUserIdAndCarId(Long userId, Long carId);
    List<Favorite> findAllByUserId(Long userId);
    boolean existsByUserIdAndCarId(Long userId, Long carId);
}
