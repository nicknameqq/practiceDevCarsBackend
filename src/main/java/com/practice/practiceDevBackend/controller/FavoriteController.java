package com.practice.practiceDevBackend.controller;

import com.practice.practiceDevBackend.dto.favorite.FavoriteResponse;
import com.practice.practiceDevBackend.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{carId}")
    public ResponseEntity<FavoriteResponse> addFavorite(
    @PathVariable Long carId,
    Authentication authentication
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(favoriteService.addFavorite(carId, authentication));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteResponse>> getFavorites(
            Authentication authentication
    ){
        return ResponseEntity.ok(favoriteService.getFavorites(authentication));
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> removeFavorite(
            @PathVariable Long carId,
            Authentication authentication
    ){
        favoriteService.removeFavorite(carId, authentication);
        return ResponseEntity.noContent().build();
    }

}
