package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.favorite.FavoriteResponse;
import com.practice.practiceDevBackend.entity.Car;
import com.practice.practiceDevBackend.entity.Favorite;
import com.practice.practiceDevBackend.entity.User;
import com.practice.practiceDevBackend.exception.CarNotFoundException;
import com.practice.practiceDevBackend.exception.FavoriteNotFoundException;
import com.practice.practiceDevBackend.repository.CarRepository;
import com.practice.practiceDevBackend.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {


    private final FavoriteRepository favoriteRepository;
    private final CarRepository carRepository;

    public FavoriteResponse addFavorite(
            Long carId,
            Authentication authentication
    ){
        User user = (User) authentication.getPrincipal();

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car not found."));

        Favorite favorite = favoriteRepository
                .findByUserIdAndCarId(user.getId(), carId)
                .orElseGet(() -> {
                    Favorite newFavorite = new Favorite();
                    newFavorite.setUser(user);
                    newFavorite.setCar(car);
                    return favoriteRepository.save(newFavorite);
                });
        return toResponse(favorite);
    }


    public List<FavoriteResponse> getFavorites(
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        return favoriteRepository.findAllByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void removeFavorite(
            Long carId,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        Favorite favorite = favoriteRepository
                .findByUserIdAndCarId(user.getId(), carId)
                .orElseThrow(() ->
                        new FavoriteNotFoundException("Favorite not found.")
                );

        favoriteRepository.delete(favorite);
    }

    private FavoriteResponse toResponse(Favorite favorite) {
        FavoriteResponse response = new FavoriteResponse();

        response.setId(favorite.getId());
        response.setCarId(favorite.getCar().getId());

        return response;
    }
}
